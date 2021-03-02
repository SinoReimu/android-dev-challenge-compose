/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.R
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.TransitionInflater
import android.view.Window
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.data.DogDataStore
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

class DogDetailActivity : AppCompatActivity() {

    companion object {
        const val DOG_TAG = "dog"

        fun startDogDetail(context: Activity, dog: Dog) {
            val intent = Intent(context, DogDetailActivity::class.java)
            intent.putExtra(DOG_TAG, dog)
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.exitTransition = Fade().setDuration(800)
        window.enterTransition = Explode().setDuration(800)
        window.setBackgroundDrawable(ColorDrawable(0xFFf2f2f2.toInt()))

        val dog: Dog? = intent.getSerializableExtra(DOG_TAG) as Dog?
        if (dog == null) {
            Toast.makeText(this, "No information to show.", Toast.LENGTH_SHORT).show()
            return
        }

        setContent {
            DetailPage(this, dog)
        }
    }
}

@Preview
@Composable
fun DetailPage(context: Context, data: Dog) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        item {
            Row {
                Column {
                    GlideImage(
                        data = data.image,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth().padding(4.dp)
                    ) {
                        Column {
                            Row(Modifier.padding(20.dp)) {
                                Text(
                                    text = data.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            }
                            Row(Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {
                                Text(
                                    text = data.desc,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
