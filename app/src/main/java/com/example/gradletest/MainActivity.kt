package com.example.gradletest

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        BuildConfig.Base_url

        GlobalScope.launch {
            delay(1000L)
//            delay will only block the current coroutine not the whole thread as in case of sleep
            Log.d("MainActivity", "coroutines says hello in thread ${Thread.currentThread().name}")
            val donetcallvb = doNetCall()
            val donetcallvb2 = doNetCall2()
            Log.d(TAG, donetcallvb)
            Log.d(TAG, donetcallvb2)
        }
        Log.d("MainActivity", "hello in thread ${Thread.currentThread().name}")
    }
    suspend fun doNetCall():String{
        delay(10000L)
        return "this is awesome by 1"
    }
    suspend fun doNetCall2():String{
        delay(5000L)
        return "this is awesome"
    }
}