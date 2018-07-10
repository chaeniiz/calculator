package com.chaeniiz.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalculatorFragment.OnFragmentInteractionListener, MainView {

    var inputNewNumber: Boolean = true
    var isNumberClicked: Boolean = false

    val presenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreate()
    }

    override fun showCalculateFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_calculator, CalculatorFragment.newInstance())
                .commit()
    }

    override fun onNumberClicked(number: String) {
        isNumberClicked = true
        val stringBuilder = StringBuilder()

        if (inputNewNumber) {
            etAnswer.setText(stringBuilder.delete(0, etAnswer.text.length))
            etAnswer.setText(stringBuilder.append(etAnswer.text).append(number))
            inputNewNumber = false
        } else if (etAnswer.text.toString() == "0")
            etAnswer.setText(number)
        else
            etAnswer.setText(stringBuilder.append(etAnswer.text).append(number))
    }

    override fun onResetClicked(reset: Boolean) {
        resetAnswerView()
        presenter.resetNumbers()
    }

    override fun onResultClicked(result: Boolean) {
        inputNewNumber = true

        presenter.onResultClicked()
    }

    override fun onPlusClicked(plus: Boolean) {
        presenter.onPlusClicked()
    }

    override fun onMinusClicked(minus: Boolean) {
        presenter.onMinusClicked()
    }

    override fun onMultiplyClicked(multiply: Boolean) {
        presenter.onMultiplyClicked()
    }

    override fun onDivideClicked(divide: Boolean) {
        presenter.onDivideClicked()
    }

    fun resetAnswerView() {
        etAnswer.text = null
        inputNewNumber = true
    }

    override fun getAnswerViewData(): Long {
        return etAnswer.text.toString().toLong()
    }

    override fun setAnswerViewData(data: Long) {
        etAnswer.setText(data.toString())
    }

    override fun inputNewNumber() {
        inputNewNumber = true
    }

    override fun isNumberButtonClicked(isClicked: Boolean) {
        isNumberClicked = isClicked
    }

    override fun getNumberButtonClicked(): Boolean {
        return isNumberClicked
    }
}
