package com.singh.mathsgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    lateinit var textScore:TextView
    lateinit var textLife:TextView
    lateinit var textTime:TextView

    lateinit var textQuestion:TextView
    lateinit var editTextAnswer:EditText
    lateinit var buttonNext:Button
    lateinit var buttonOk: Button

    var correctAnswer=0
    var userScore=0
    var life=3

    private var operation: String = "+"

    lateinit var timer: CountDownTimer

    private val startTimerInMillis: Long = 10000

    var timeLeftInMillis: Long = startTimerInMillis




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title="Addition"

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextNumberAnswer)
        buttonNext = findViewById(R.id.buttonNext)
        buttonOk = findViewById(R.id.buttonOk)

        operation = intent.getStringExtra("operation") ?: "+"

        gameContinue()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()
            if (input == "") {
                Toast.makeText(
                    this,
                    "Please enter an answer or click next button",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val userAnswer = input.toInt()

                if (userAnswer == correctAnswer) {

                    userScore = userScore + 10
                    textQuestion.text = "wohoo your anser is correct"
                    textScore.text = userScore.toString()

                } else {

                    pauseTimer()


                    life = life - 1

                        textQuestion.text = "oh no! your answer is wrong"
                        textLife.text = life.toString()



                }

            }

        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
                gameContinue()
            editTextAnswer.setText("")
            if (life == 0) {
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()

            }
            else{
                gameContinue()
            }
        }

    }

    private fun gameContinue() {
        val number1 = Random.nextInt(0, 1000)
        val number2 = Random.nextInt(0, 1000)
        correctAnswer = when (operation) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            else -> number1 + number2 // Default to addition
        }
        textQuestion.text = "$number1 $operation $number2"
        startTimer()
    }



    fun startTimer(){
        timer=  object: CountDownTimer(timeLeftInMillis,1000){
            override fun onTick(millisUntilFinished : Long) {
                timeLeftInMillis=millisUntilFinished
                updateText()
            }

            override fun onFinish() {
               pauseTimer()
                resetTimer()
                updateText()

                life=life-1
                textLife.text=life.toString()
                textQuestion.text="Sorry! your time is up"
            }
        }.start()

    }

    fun updateText(){
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        textTime.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }

    fun pauseTimer(){

        timer.cancel()

    }

    fun resetTimer(){

        timeLeftInMillis=startTimerInMillis
        updateText()

    }




}