package com.chaeniiz.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalculatorFragment.OnFragmentInteractionListener {

    var firstNumber: Long = 0
    var secondNumber: Long = 0
    var answer: Long = 0
    var isFirst: Boolean = true

    var inputNewNumber: Boolean = true
    var isNumberClicked: Boolean = false

    var calculateMode: CalculateMode = CalculateMode()

    override fun onNumberClicked(number: String) {
        isNumberClicked = true
        val stringBuilder = StringBuilder()

        if(inputNewNumber) {
            etAnswer.setText(stringBuilder.delete(0, etAnswer.text.length))
            etAnswer.setText(stringBuilder.append(etAnswer.text).append(number))
            inputNewNumber = false
        } else if (etAnswer.text.toString() == "0")
            etAnswer.setText(number)
        else
            etAnswer.setText(stringBuilder.append(etAnswer.text).append(number))
    }

    override fun onResetClicked(reset: Boolean) {
        etAnswer.text = null
        isFirst = true
        inputNewNumber = true
        firstNumber = 0
        secondNumber = 0
        answer = 0
        initCalculateMode()
    }

    override fun onResultClicked(result: Boolean) {
        isFirst = true
        inputNewNumber = true

        when {
            calculateMode.plusMode -> {
                if(!calculateMode.resultMode) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber + secondNumber
                    firstNumber = secondNumber
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                    calculateMode.resultMode = true
                } else {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = secondNumber + firstNumber
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                }
            }
            calculateMode.minusMode -> {
                if(!calculateMode.resultMode) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber - secondNumber
                    firstNumber = secondNumber
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                    calculateMode.resultMode = true
                } else {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = secondNumber - firstNumber
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                }
            }
            calculateMode.multiplyMode -> {
                if(!calculateMode.resultMode) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber * secondNumber
                    firstNumber = secondNumber
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                    calculateMode.resultMode = true
                } else {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = secondNumber * firstNumber
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                }
            }
            calculateMode.divideMode -> {
                if(!calculateMode.resultMode) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber / secondNumber
                    firstNumber = secondNumber
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                    calculateMode.resultMode = true
                } else {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = secondNumber / firstNumber
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                }
            }
        }
    }

    override fun onPlusClicked(plus: Boolean) {
        initCalculateMode()
        calculateMode.plusMode = true

        when {
            isFirst || calculateMode.resultMode -> {
                firstNumber = etAnswer.text.toString().toLong()
                isFirst = false
                calculateMode.resultMode = false
                isNumberClicked = false
                inputNewNumber = true
            }
            !isFirst -> {
                inputNewNumber = true
                if(isNumberClicked) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber  + secondNumber
                    firstNumber = answer
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onMinusClicked(minus: Boolean) {
        initCalculateMode()
        calculateMode.minusMode = true

        when {
            isFirst || calculateMode.resultMode -> {
                firstNumber = etAnswer.text.toString().toLong()
                isFirst = false
                calculateMode.resultMode = false
                isNumberClicked = false
                inputNewNumber = true
            }
            !isFirst -> {
                inputNewNumber = true
                if(isNumberClicked) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber  - secondNumber
                    firstNumber = answer
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onMultiplyClicked(multiply: Boolean) {
        initCalculateMode()
        calculateMode.multiplyMode = true

        when {
            isFirst || calculateMode.resultMode -> {
                firstNumber = etAnswer.text.toString().toLong()
                isFirst = false
                calculateMode.resultMode = false
                isNumberClicked = false
                inputNewNumber = true
            }
            !isFirst -> {
                inputNewNumber = true
                if(isNumberClicked) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber  * secondNumber
                    firstNumber = answer
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onDivideClicked(divide: Boolean) {
        initCalculateMode()
        calculateMode.divideMode = true

        when {
            isFirst || calculateMode.resultMode -> {
                firstNumber = etAnswer.text.toString().toLong()
                isFirst = false
                calculateMode.resultMode = false
                isNumberClicked = false
                inputNewNumber = true
            }
            !isFirst -> {
                inputNewNumber = true
                if(isNumberClicked) {
                    secondNumber = etAnswer.text.toString().toLong()
                    answer = firstNumber  / secondNumber
                    firstNumber = answer
                    etAnswer.setText(answer.toString())
                    inputNewNumber = true
                    isNumberClicked = false
                }
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
        calculateMode.plusMode = false
        calculateMode.minusMode = false
        calculateMode.multiplyMode = false
        calculateMode.divideMode = false
    }
}
