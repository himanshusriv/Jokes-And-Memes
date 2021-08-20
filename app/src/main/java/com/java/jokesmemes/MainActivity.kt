package com.java.jokesmemes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun jokes(view: View) {
        val intent = Intent(this, Jokes::class.java)
        startActivity(intent)
    }
    fun memes(view: View) {
        val intent = Intent(this, Memes::class.java)
        startActivity(intent)
    }
}