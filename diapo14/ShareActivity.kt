package com.example.premierprojet

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        val data: Uri? = intent?.data

        // Find what to do depending on intent type
        if (intent?.type?.startsWith("image/") == true) {
            // do stuff with image data
        }else if (intent?.type == "text/plain") {
            Log.d("TAG", intent?.getStringExtra(Intent.EXTRA_TEXT).toString())
            // do stuff with the text
        }
    }
}
