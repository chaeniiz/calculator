package com.chaeniiz.calculator

interface MainView {
    fun showCalculateFragment()
    fun setAnswerViewData(data: Long)
    fun getAnswerViewData(): Long
    fun setInputNewNumber(isInputNewNumber: Boolean)
    fun setNumberButtonClicked(isClicked: Boolean)
    fun getNumberButtonClicked(): Boolean
    fun isInputNewNumber(): Boolean
    fun resetAnswerView()
}
