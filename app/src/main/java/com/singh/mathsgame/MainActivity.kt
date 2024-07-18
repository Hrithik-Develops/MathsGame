package com.singh.mathsgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var addition:Button
    lateinit var subtraction:Button
    lateinit var multiplication:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addition=findViewById(R.id.buttonAdd)
        subtraction=findViewById(R.id.buttonSub)
        multiplication=findViewById(R.id.buttonMulti)

        addition.setOnClickListener{
            startGameActivity("+")
        }

        subtraction.setOnClickListener{
            startGameActivity("-")

        }

        multiplication.setOnClickListener{
            startGameActivity("*")
        }


    }
    fun startGameActivity(operation: String) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("operation", operation)
        startActivity(intent)
    }

}