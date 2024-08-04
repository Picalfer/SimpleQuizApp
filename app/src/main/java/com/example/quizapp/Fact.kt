package com.example.quizapp

class Fact(private val text: String, private val correct: Boolean) {
    fun getText() = text
    fun isCorrect() = correct
}