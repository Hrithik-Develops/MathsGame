package com.singh.mathsgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    lateinit var congrats: TextView
    lateinit var score: TextView
    lateinit var playAgain: Button
    lateinit var exit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        congrats = findViewById(R.id.textViewResult)
        score = findViewById(R.id.textView4)
        playAgain = findViewById(R.id.buttonPlayAgain)
        exit = findViewById(R.id.buttonExit)

        val sscore=intent.getIntExtra("score",0)
       score.text ="Your score is $sscore"

        playAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        exit.setOnClickListener {
            val intent=Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }

    }
}