package com.example.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var factList: List<Fact>
    private lateinit var factText: TextView
    private var currentIndex = 0
    private lateinit var currentFact: Fact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        factList = listOf(
            Fact(getString(R.string.fact1), false),
            Fact(getString(R.string.fact2), true),
            Fact(getString(R.string.fact3), false)
        )

        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnFalse = findViewById<Button>(R.id.btnFalse)
        factText = findViewById<TextView>(R.id.factText)
        val btnNext = findViewById<Button>(R.id.btnNext)

        nextFact()

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        btnNext.setOnClickListener {
            nextFact()
        }
    }

    private fun nextFact() {
        currentIndex = (currentIndex + 1) % factList.size
        currentFact = factList[currentIndex]
        factText.text = currentFact.getText()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == currentFact.isCorrect()) {
            Toast.makeText(this, getString(R.string.correct_toast), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.wrong_toast), Toast.LENGTH_SHORT).show()
        }
    }
}