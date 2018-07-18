package com.chaeniiz.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalculatorFragment.OnFragmentInteractionListener, MainView {

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
        etAnswer.setText("0")
    }

    override fun onNumberClicked(number: String) {
        presenter.onNumberClicked(number.toLong())
    }

    override fun onResetClicked(reset: Boolean) {
        resetAnswerView()
        presenter.resetCalculator()
    }

    override fun onResultClicked(result: Boolean) {
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
        etAnswer.setText("0")
    }

    override fun setTextEtAnswer(data: Long) {
        etAnswer.setText(data.toString())
    }
}
