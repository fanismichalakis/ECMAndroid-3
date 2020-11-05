package com.example.premierprojet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_button.*

class ButtonActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button.setOnClickListener {
            Log.d("TAG", "Clicked using simple listener")
        }

        button3.setOnClickListener(this)


    }

    fun onClickFromXml(view: View) {
        Log.d("TAG", "Clicked using XML method")
    }

    override fun onClick(view: View?) {
        Log.d("TAG", "Click using override method")
    }
}
