package com.chaeniiz.calculator

import android.support.annotation.VisibleForTesting
import com.chaeniiz.calculator.CalculateModeState.*

class MainPresenter(val view: MainView) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var inputNumber: Long = 0
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var answer: Long = 0
    private var firstNumber: Long = 0
    private var isNewNumber: Boolean = true
    private var isNumberClicked: Boolean = false
    private var calculateMode: CalculateMode = CalculateMode()

    fun onCreate() {
        view.showCalculateFragment()
    }

    fun resetCalculator() {
        firstNumber = 0
        answer = 0
        inputNumber = 0
        isNewNumber = true
        isNumberClicked = false
        calculateMode = CalculateMode()
    }

    fun onPlusClicked() {
        checkCalculatorCondition(CalculateMode(plusMode = true))
        isNumberClicked = false
    }

    fun onMinusClicked() {
        checkCalculatorCondition(CalculateMode(minusMode = true))
        isNumberClicked = false
    }

    fun onMultiplyClicked() {
        checkCalculatorCondition(CalculateMode(multiplyMode = true))
        isNumberClicked = false
    }

    fun onDivideClicked() {
        checkCalculatorCondition(CalculateMode(divideMode = true))
        isNumberClicked = false
    }

    fun onResultClicked() {
        setInputNewNumber(true)
        isNumberClicked = false

        when(calculateMode) {
            PLUS_ONCE.case -> {
                answer = calculate(CalculateMode(plusMode = true), firstNumber, inputNumber)
                firstNumber = inputNumber
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
                calculateMode = CalculateMode(plusMode = true, resultMode = true)
            }
            PLUS_CONTINUE.case -> {
                answer = calculate(CalculateMode(plusMode = true, resultMode = true), answer, inputNumber)
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
            }
            MINUS_ONCE.case -> {
                answer = calculate(CalculateMode(minusMode = true), firstNumber, inputNumber)
                firstNumber = inputNumber
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
                calculateMode = CalculateMode(minusMode = true, resultMode = true)
            }
            MINUS_CONTINUE.case -> {
                answer = calculate(CalculateMode(minusMode = true, resultMode = true), inputNumber, answer)
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
            }
            MULTIPLY_ONCE.case -> {
                answer = calculate(CalculateMode(multiplyMode = true), firstNumber, inputNumber)
                firstNumber = inputNumber
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
                calculateMode = CalculateMode(multiplyMode = true, resultMode = true)
            }
            MULTIPLY_CONTINUE.case -> {
                answer = calculate(CalculateMode(multiplyMode = true, resultMode = true), answer, inputNumber)
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
            }
            DIVIDE_ONCE.case -> {
                answer = calculate(CalculateMode(divideMode = true), firstNumber, inputNumber)
                firstNumber = inputNumber
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
                calculateMode = CalculateMode(divideMode = true, resultMode = true)
            }
            DIVIDE_CONTINUE.case -> {
                answer = calculate(CalculateMode(divideMode = true, resultMode = true), inputNumber, answer)
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
            }
        }
    }

    fun onNumberClicked(number: Long) {
        isNumberClicked = true
        val stringBuilder = StringBuilder()

        when {
            isNewNumber -> {
                if(number == 0L) {
                    view.setTextEtAnswer(number)
                }
                updateEtAnswer(number)
                updateInputNumber(number)
                setInputNewNumber(false)
            }

            else -> {
                try {
                    updateInputNumber(stringBuilder.append(inputNumber).append(number).toString().toLong())
                    updateEtAnswer(inputNumber)
                } catch (e: NumberFormatException) {
                    updateEtAnswer(inputNumber)
                }
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun calculate(calculateMode: CalculateMode, firstNumber: Long, secondNumber: Long): Long {
        answer = when (calculateMode) {
            CalculateMode(plusMode = true),
            CalculateMode(plusMode = true, resultMode = true) ->
                firstNumber + secondNumber
            CalculateMode(minusMode = true) ->
                firstNumber - secondNumber
            CalculateMode(multiplyMode = true),
            CalculateMode(multiplyMode = true, resultMode = true) ->
                firstNumber * secondNumber
            CalculateMode(divideMode = true) -> {
                try {
                    firstNumber / secondNumber
                } catch (e: ArithmeticException) {
                    firstNumber
                }
            }
            CalculateMode(minusMode = true, resultMode = true) ->
                secondNumber - firstNumber
            CalculateMode(divideMode = true, resultMode = true) -> {
                try {
                    secondNumber / firstNumber
                } catch (e: ArithmeticException) {
                    secondNumber
                }
            }
            else -> answer
        }
        return answer
    }

   private fun checkCalculatorCondition(calculateMode: CalculateMode) {
       when {
           this.calculateMode.resultMode -> {
               this.calculateMode = calculateMode
               if(isNumberClicked) {
                   setNumberToFirstNumber(inputNumber)
               } else
                   setNumberToFirstNumber(answer)
           }
           this.calculateMode == CalculateMode() -> {
               this.calculateMode = calculateMode
               setNumberToFirstNumber(inputNumber)
           }
           this.calculateMode != calculateMode -> {
               answer = calculate(this.calculateMode, firstNumber, inputNumber)
               view.setTextEtAnswer(answer)
               firstNumber = answer
               setInputNewNumber(true)
               this.calculateMode = calculateMode
           }
           else -> {
               this.calculateMode = calculateMode
               setInputNewNumber(true)
               if (isNumberClicked) {
                   answer = calculate(calculateMode, firstNumber, inputNumber)
                   firstNumber = answer
                   isNumberClicked = false
                   view.setTextEtAnswer(answer)
               }
           }
       }
       isNumberClicked = false
   }

    private fun setNumberToFirstNumber(number: Long) {
        this.firstNumber = number
        isNumberClicked = false
        setInputNewNumber(true)
    }

    private fun setInputNewNumber(isInputNewNumber: Boolean) {
        isNewNumber = isInputNewNumber
    }

    private fun updateInputNumber(inputNumber: Long) {
        this.inputNumber = inputNumber
    }

    private fun updateEtAnswer(number: Long) {
        view.setTextEtAnswer(number)
    }
}
