package com.chaeniiz.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalculatorFragment.OnFragmentInteractionListener, MainView {

    var isNewNumber: Boolean = true
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
        presenter.onNumberClicked(number.toLong())
    }

    override fun onResetClicked(reset: Boolean) {
        resetAnswerView()
        presenter.initNumbersStatus()
    }

    override fun onResultClicked(result: Boolean) {
        setInputNewNumber(true)
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

    override fun resetAnswerView() {
        etAnswer.text = null
        isNewNumber = true
    }

    override fun getAnswerViewData(): Long {
        return etAnswer.text.toString().toLong()
    }

    override fun setAnswerViewData(data: Long) {
        etAnswer.setText(data.toString())
    }

    override fun setInputNewNumber(isInputNewNumber: Boolean) {
        isNewNumber = isInputNewNumber
    }

    override fun isInputNewNumber(): Boolean {
        return isNewNumber
    }

    override fun setNumberButtonClicked(isClicked: Boolean) {
        isNumberClicked = isClicked
    }

    override fun getNumberButtonClicked(): Boolean {
        return isNumberClicked
    }
}
