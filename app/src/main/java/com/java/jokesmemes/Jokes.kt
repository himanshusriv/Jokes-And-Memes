package com.java.jokesmemes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_jokes.*

class Jokes : AppCompatActivity() {

    private var currentJoke : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        onLoad()
    }

    fun onShare(view : View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey, Checkout this joke, \n \n $currentJoke "
        )
        val chooser = Intent.createChooser(intent, "Share this joke using...")
        startActivity(chooser)
    }

    fun onNext(view: View) {
        onLoad()
    }


    private fun onLoad() {
        loading.visibility = View.VISIBLE
        next.isEnabled = false
        share.isEnabled = false

        val url = "https://official-joke-api.appspot.com/random_joke"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                currentJoke = response.getString("setup")
                currentJoke = currentJoke + "\n \n" + response.getString("punchline")
                joke.text = currentJoke
                loading.visibility = View.GONE
                next.isEnabled = true
                share.isEnabled = true
            },
            {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            })

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }
}

