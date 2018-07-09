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
        secondNumber = etAnswer.text.toString().toLong()

        when(calculateMode) {
            CalculateMode(plusMode = true) -> {
                calculate(firstNumber, secondNumber)
                firstNumber = secondNumber
                etAnswer.setText(answer.toString())
                inputNewNumber = true
                calculateMode = CalculateMode(plusMode = true, resultMode = true)
            }
            CalculateMode(plusMode = true, resultMode = true) -> {
                calculate(firstNumber, etAnswer.text.toString().toLong())
                etAnswer.setText(answer.toString())
                inputNewNumber = true
            }
            CalculateMode(minusMode = true) -> {
                calculate(firstNumber, secondNumber)
                firstNumber = secondNumber
                etAnswer.setText(answer.toString())
                inputNewNumber = true
                calculateMode = CalculateMode(minusMode = true, resultMode = true)
            }
            CalculateMode(minusMode = true, resultMode = true) -> {
                calculate(firstNumber, etAnswer.text.toString().toLong())
                etAnswer.setText(answer.toString())
                inputNewNumber = true
            }
            CalculateMode(multiplyMode = true) -> {
                calculate(firstNumber, secondNumber)
                firstNumber = secondNumber
                etAnswer.setText(answer.toString())
                inputNewNumber = true
                calculateMode = CalculateMode(multiplyMode = true, resultMode = true)
            }
            CalculateMode(multiplyMode = true, resultMode = true) -> {
                calculate(firstNumber, etAnswer.text.toString().toLong())
                etAnswer.setText(answer.toString())
                inputNewNumber = true
            }
            CalculateMode(divideMode = true) -> {
                calculate(firstNumber, secondNumber)
                firstNumber = secondNumber
                etAnswer.setText(answer.toString())
                inputNewNumber = true
                calculateMode = CalculateMode(divideMode = true, resultMode = true)
            }
            CalculateMode(divideMode = true, resultMode = true) -> {
                calculate(firstNumber, secondNumber)
                etAnswer.setText(answer.toString())
                inputNewNumber = true
            }
        }
    }

    override fun onPlusClicked(plus: Boolean) {
        calculateMode = CalculateMode(plusMode = true)

        when {
            isFirst || calculateMode.resultMode ->
                insertFirstNumber()
            !isFirst -> {
                inputNewNumber = true
                if (isNumberClicked) {
                    calculate(firstNumber, etAnswer.text.toString().toLong())
                    firstNumber = answer
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onMinusClicked(minus: Boolean) {
        calculateMode = CalculateMode(minusMode = true)

        when {
            isFirst || calculateMode.resultMode ->
                insertFirstNumber()
            !isFirst -> {
                inputNewNumber = true
                if (isNumberClicked) {
                    calculate(firstNumber, etAnswer.text.toString().toLong())
                    firstNumber = answer
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onMultiplyClicked(multiply: Boolean) {
        calculateMode = CalculateMode(multiplyMode = true)

        when {
            isFirst || calculateMode.resultMode ->
                insertFirstNumber()
            !isFirst -> {
                inputNewNumber = true
                if (isNumberClicked) {
                    calculate(firstNumber, etAnswer.text.toString().toLong())
                    firstNumber = answer
                    isNumberClicked = false
                }
            }
        }
    }

    override fun onDivideClicked(divide: Boolean) {
        calculateMode = CalculateMode(divideMode = true)

        when {
            isFirst || calculateMode.resultMode ->
                insertFirstNumber()
            !isFirst -> {
                inputNewNumber = true
                if (isNumberClicked) {
                    calculate(firstNumber, etAnswer.text.toString().toLong())
                    firstNumber = answer
                    isNumberClicked = false
                }
            }
        }
    }

    fun insertFirstNumber() {
        firstNumber = etAnswer.text.toString().toLongOrNull() ?: 0L
        isFirst = false
        isNumberClicked = false
        inputNewNumber = true
    }

    fun calculate(firstNumber: Long, secondNumber: Long): Long {
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

        return answer
    }
}
