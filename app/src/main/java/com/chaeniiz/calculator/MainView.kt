package com.chaeniiz.calculator

interface MainView {
    fun showCalculateFragment()
    fun setAnswerViewData(data: Long)
    fun getAnswerViewData(): Long
    fun inputNewNumber()
    fun isNumberButtonClicked(isClicked: Boolean)
    fun getNumberButtonClicked(): Boolean
}