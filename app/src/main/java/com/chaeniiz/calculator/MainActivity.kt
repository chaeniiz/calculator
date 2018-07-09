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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        etAnswer.text = null
        isFirst = true
        inputNewNumber = true
        firstNumber = 0
        secondNumber = 0
        answer = 0
    }

    override fun onResultClicked(result: Boolean) {
        isFirst = true
        inputNewNumber = true

        when {
            calculateMode == CalculateMode(plusMode = true) -> {
                insertSecondNumber()
                firstNumber = secondNumber
                calculateMode = CalculateMode(plusMode = true, resultMode = true)
            }
            calculateMode == CalculateMode(plusMode = true, resultMode = true) -> {
                insertSecondNumber()
            }
            calculateMode == CalculateMode(minusMode = true) -> {
                insertSecondNumber()
                firstNumber = secondNumber
                calculateMode = CalculateMode(minusMode = true, resultMode = true)
            }
            calculateMode == CalculateMode(minusMode = true, resultMode = true) -> {
                insertSecondNumber()
            }
            calculateMode == CalculateMode(multiplyMode = true) -> {
                insertSecondNumber()
                firstNumber = secondNumber
                calculateMode = CalculateMode(multiplyMode = true, resultMode = true)
            }
            calculateMode == CalculateMode(multiplyMode = true, resultMode = true) -> {
                insertSecondNumber()
            }
            calculateMode == CalculateMode(divideMode = true) -> {
                insertSecondNumber()
                firstNumber = secondNumber
                calculateMode = CalculateMode(divideMode = true, resultMode = true)
            }
            calculateMode == CalculateMode(divideMode = true, resultMode = true) -> {
                insertSecondNumber()
            }
        }
    }

    override fun onPlusClicked(plus: Boolean) {
        calculateMode = CalculateMode(plusMode = true)

        when {
            isFirst || calculateMode.resultMode -> {
                insertFirstNumber()
            }
            !isFirst -> {
                inputNewNumber = true
                if (isNumberClicked) {
                    insertSecondNumber()
                    firstNumber = answer
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onMinusClicked(minus: Boolean) {
        calculateMode = CalculateMode(minusMode = true)

        when {
            isFirst || calculateMode.resultMode -> {
                insertFirstNumber()
            }
            !isFirst -> {
                inputNewNumber = true
                if (isNumberClicked) {
                    insertSecondNumber()
                    firstNumber = answer
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onMultiplyClicked(multiply: Boolean) {
        calculateMode = CalculateMode(multiplyMode = true)

        when {
            isFirst || calculateMode.resultMode -> {
                insertFirstNumber()
            }
            !isFirst -> {
                inputNewNumber = true
                if (isNumberClicked) {
                    insertSecondNumber()
                    firstNumber = answer
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onDivideClicked(divide: Boolean) {
        calculateMode = CalculateMode(divideMode = true)

        when {
            isFirst || calculateMode.resultMode -> {
                insertFirstNumber()
            }
            !isFirst -> {
                inputNewNumber = true
                if (isNumberClicked) {
                    insertSecondNumber()
                    firstNumber = answer
                    isNumberClicked = false
                }
            }
        }
    }

    fun insertFirstNumber() {
        firstNumber = etAnswer.text.toString().toLong()
        isFirst = false
        calculateMode.resultMode = false
        isNumberClicked = false
        inputNewNumber = true
    }

    fun insertSecondNumber() {
        secondNumber = etAnswer.text.toString().toLong()
        when {
            calculateMode == CalculateMode(plusMode = true)
            || calculateMode == CalculateMode(plusMode = true, resultMode = true) ->
                answer = firstNumber + secondNumber
            calculateMode == CalculateMode(minusMode = true) ->
                answer = firstNumber - secondNumber
            calculateMode == CalculateMode(multiplyMode = true)
            || calculateMode == CalculateMode(multiplyMode = true, resultMode = true) ->
                answer = firstNumber * secondNumber
            calculateMode == CalculateMode(divideMode = true) ->
                    answer = firstNumber / secondNumber
            calculateMode == CalculateMode(minusMode = true, resultMode = true) ->
                    answer = secondNumber - firstNumber
            calculateMode == CalculateMode(divideMode = true, resultMode = true) ->
                    answer = secondNumber / firstNumber
        }
        etAnswer.setText(answer.toString())
        inputNewNumber = true
    }
}
