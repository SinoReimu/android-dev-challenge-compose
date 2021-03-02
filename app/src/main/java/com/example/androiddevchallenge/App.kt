package com.example.androiddevchallenge

import android.app.Application
import com.example.androiddevchallenge.data.DogDataStore

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        DogDataStore.initDogData(this)
    }

}