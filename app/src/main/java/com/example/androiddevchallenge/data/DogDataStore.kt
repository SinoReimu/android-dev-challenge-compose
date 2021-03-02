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
package com.example.androiddevchallenge.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

object DogDataStore {

    lateinit var dog: List<Dog>

    fun initDogData(context: Context) {
        val content = context.assets.open("data.json").bufferedReader().use {
            it.readText()
        }
        val typeToken: TypeToken<List<Dog>> = object : TypeToken<List<Dog>>() {}
        dog = Gson().fromJson(content, typeToken.type)
    }
}

data class Dog constructor(var image: String, var name: String, var desc: String) : Serializable
