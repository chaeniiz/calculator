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
                resultClickedOnce(CalculateMode(plusMode = true))
                calculateMode = CalculateMode(plusMode = true, resultMode = true)
            }
            PLUS_CONTINUE.case -> {
                continueResultClick(CalculateMode(plusMode = true, resultMode = true))
            }
            MINUS_ONCE.case -> {
                resultClickedOnce(CalculateMode(minusMode = true))
                calculateMode = CalculateMode(minusMode = true, resultMode = true)
            }
            MINUS_CONTINUE.case -> {
                continueResultClick(CalculateMode(minusMode = true, resultMode = true))
            }
            MULTIPLY_ONCE.case -> {
                resultClickedOnce(CalculateMode(multiplyMode = true))
                calculateMode = CalculateMode(multiplyMode = true, resultMode = true)
            }
            MULTIPLY_CONTINUE.case -> {
                continueResultClick(CalculateMode(multiplyMode = true, resultMode = true))
            }
            DIVIDE_ONCE.case -> {
                resultClickedOnce(CalculateMode(divideMode = true))
                calculateMode = CalculateMode(divideMode = true, resultMode = true)
            }
            DIVIDE_CONTINUE.case -> {
                continueResultClick(CalculateMode(divideMode = true, resultMode = true))
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

   private fun checkCalculatorCondition(currentCalculateMode: CalculateMode) {
       when {
           this.calculateMode.resultMode -> {
               this.calculateMode = currentCalculateMode
               if(isNumberClicked) {
                   setNumberToFirstNumber(inputNumber)
               } else
                   setNumberToFirstNumber(answer)
           }
           this.calculateMode == DEFAULT.case -> {
               this.calculateMode = currentCalculateMode
               setNumberToFirstNumber(inputNumber)
           }
           this.calculateMode != currentCalculateMode -> {
               answer = calculate(this.calculateMode, firstNumber, inputNumber)
               view.setTextEtAnswer(answer)
               firstNumber = answer
               setInputNewNumber(true)
               this.calculateMode = currentCalculateMode
           }
           this.calculateMode == currentCalculateMode -> {
               this.calculateMode = currentCalculateMode
               setInputNewNumber(true)
               if (isNumberClicked) {
                   answer = calculate(currentCalculateMode, firstNumber, inputNumber)
                   firstNumber = answer
                   isNumberClicked = false
                   view.setTextEtAnswer(answer)
               }
           }
       }
       isNumberClicked = false
   }

    private fun resultClickedOnce(calculateMode: CalculateMode) {
        answer = calculate(calculateMode, firstNumber, inputNumber)
        firstNumber = inputNumber
        view.setTextEtAnswer(answer)
        setInputNewNumber(true)
    }

    private fun continueResultClick(calculateMode: CalculateMode) {
        answer = calculate(calculateMode, inputNumber, answer)
        view.setTextEtAnswer(answer)
        setInputNewNumber(true)
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
