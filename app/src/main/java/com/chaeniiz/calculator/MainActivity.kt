package com.chaeniiz.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalculatorFragment.OnFragmentInteractionListener {

    var firstNumber: Long = 0
    var secondNumber: Long = 0
    var answer: Long = 0
    var isFirst: Boolean = true
    var startCalculate: Boolean = false

    var isNumberDeleted: Boolean = false
    var isNumberClicked: Boolean = false

    var isPlusMode: Boolean = false
    var isMinusMode: Boolean = false
    var isMultiplyMode: Boolean = false
    var isDivideMode: Boolean = false
    var isResultMode: Boolean = false

    override fun onNumberClicked(number: String) {
        isNumberClicked = true
        val stringBuilder = StringBuilder()

        if(etAnswer != null) {
            if(!isFirst) {
                if(!isNumberDeleted) {
                    etAnswer.setText(stringBuilder.delete(0, etAnswer.text.length))
                    etAnswer.setText(stringBuilder.append(etAnswer.text).append(number))
                    isNumberDeleted = true
                } else
                    etAnswer.setText(stringBuilder.append(etAnswer.text).append(number))
            } else {
                if (etAnswer.text.toString() == "0")
                    etAnswer.setText(number)
                else {
                    etAnswer.setText(stringBuilder.append(etAnswer.text).append(number))
                }
            }
        }
    }

    override fun onResetClicked(reset: Boolean) {
        etAnswer.text = null
        isFirst = true
        isNumberDeleted = false
        startCalculate = false
        firstNumber = 0
        secondNumber = 0
        answer = 0
        initCalculateMode()
    }

    override fun onResultClicked(result: Boolean) {
        when {
            isPlusMode -> {
                if(!isResultMode) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber + secondNumber
                    firstNumber = secondNumber
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                    isResultMode = true
                } else {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = secondNumber + firstNumber
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                }
            }
            isMinusMode -> {
                if(!isResultMode) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber - secondNumber
                    firstNumber = secondNumber
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                    isResultMode = true
                } else {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = secondNumber - firstNumber
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                }
            }
            isMultiplyMode -> {
                if(!isResultMode) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber * secondNumber
                    firstNumber = secondNumber
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                    isResultMode = true
                } else {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = secondNumber * firstNumber
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                }
            }
            isDivideMode -> {
                if(!isResultMode) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber / secondNumber
                    firstNumber = secondNumber
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                    isResultMode = true
                } else {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = secondNumber / firstNumber
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                }
            }
        }
    }

    override fun onPlusClicked(plus: Boolean) {
        initCalculateMode()
        isPlusMode = true

        when {
            startCalculate -> {
                isNumberDeleted = false
                if(isNumberClicked) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber  + secondNumber
                    firstNumber = answer
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                    isNumberClicked = false
                }
            }
            isFirst || isResultMode -> {
                firstNumber = etAnswer.text.toString().toLong()
                isFirst = false
                isResultMode = false
                isNumberClicked = false
                startCalculate = true
            }
        }
    }

    override fun onMinusClicked(minus: Boolean) {
        initCalculateMode()
        isMinusMode = true

        when {
            startCalculate -> {
                isNumberDeleted = false
                if(isNumberClicked) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber  - secondNumber
                    firstNumber = answer
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                    isNumberClicked = false
                }
            }
            isFirst || isResultMode -> {
                firstNumber = etAnswer.text.toString().toLong()
                isFirst = false
                isResultMode = false
                isNumberClicked = false
                startCalculate = true
            }
        }
    }

    override fun onMultiplyClicked(multiply: Boolean) {
        initCalculateMode()
        isMultiplyMode = true

        when {
            startCalculate -> {
                isNumberDeleted = false
                if(isNumberClicked) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber  * secondNumber
                    firstNumber = answer
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                    isNumberClicked = false
                }
            }
            isFirst || isResultMode -> {
                firstNumber = etAnswer.text.toString().toLong()
                isFirst = false
                isResultMode = false
                isNumberClicked = false
                startCalculate = true
            }
        }
    }

    override fun onDivideClicked(divide: Boolean) {
        initCalculateMode()
        isDivideMode = true

        when {
            startCalculate -> {
                isNumberDeleted = false
                if(isNumberClicked) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber  / secondNumber
                    firstNumber = answer
                    etAnswer.setText(answer.toString())
                    isNumberDeleted = false
                    isNumberClicked = false
                }
            }
            isFirst || isResultMode -> {
                firstNumber = etAnswer.text.toString().toLong()
                isFirst = false
                isResultMode = false
                isNumberClicked = false
                startCalculate = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_calculator, CalculatorFragment.newInstance())
                .commit()
    }

    fun initCalculateMode() {
        isPlusMode = false
        isMinusMode = false
        isMultiplyMode = false
        isDivideMode = false
    }
}
