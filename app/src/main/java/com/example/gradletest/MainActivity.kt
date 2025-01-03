package com.example.gradletest

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gradletest.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val TAG="MainActivity"

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        GlobalScope.launch (Dispatchers.IO){
            Log.d(TAG, "currently in ${Thread.currentThread().name} ")
            val answer = doNetCall()
//            let's asssume this is getting the response from netwrok call so we have used IO dispatcher
//            as well as we want to change the ui so instead of making a new corotuine we can change the context of our coroutines
//            with the use of withContext

            withContext(Dispatchers.Main){
                binding.apply {
                    tvDummy.text = "the text is :${answer}"
                }
                Log.d(TAG, "coroutine currently in ${Thread.currentThread().name} ")

            }
        }
        Log.d("MainActivity", "hello in thread ${Thread.currentThread().name}")
    }
    suspend fun doNetCall():String{
        delay(5000L)
        return "this is awesome by 1"
    }
    suspend fun doNetCall2():String{
        delay(5000L)
        return "this is awesome"
    }
}