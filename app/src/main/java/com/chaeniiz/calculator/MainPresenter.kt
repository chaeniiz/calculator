package com.chaeniiz.calculator

import android.support.annotation.VisibleForTesting

class MainPresenter(val view: MainView) {

    var firstNumber: Long = 0
    var inputNumber: Long = 0
    var answer: Long = 0
    var isOperatorButtonClickedFirst: Boolean = true
    var isNewNumber: Boolean = true
    var isNumberClicked: Boolean = false
    var calculateMode: CalculateMode = CalculateMode()

    fun onCreate() {
        view.showCalculateFragment()
    }

    fun initNumberStatus() {
        isOperatorButtonClickedFirst = true
        firstNumber = 0
        answer = 0
        inputNumber = 0
    }

    fun onPlusClicked() {
        when {
            calculateMode.resultMode -> {
                calculateMode = CalculateMode(plusMode = true)
                setNumberToFirstNumber(answer)
            }
            !isOperatorButtonClickedFirst && !calculateMode.plusMode -> {
                answer = calculate(calculateMode, firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(plusMode = true)
            }
            isOperatorButtonClickedFirst -> {
                calculateMode = CalculateMode(plusMode = true)
                setNumberToFirstNumber(inputNumber)
                isOperatorButtonClickedFirst = false
            }
            !isOperatorButtonClickedFirst -> {
                calculateMode = CalculateMode(plusMode = true)
                setInputNewNumber(true)
                if (isNumberClicked) {
                    answer = calculate(CalculateMode(plusMode = true), firstNumber, inputNumber)
                    firstNumber = answer
                    isNumberClicked = false
                    view.setTextEtAnswer(answer)
                }
            }
        }
    }

    fun onMinusClicked() {
        when {
            calculateMode.resultMode -> {
                calculateMode = CalculateMode(minusMode = true)
                setNumberToFirstNumber(answer)
            }
            !isOperatorButtonClickedFirst && !calculateMode.minusMode -> {
                answer = calculate(calculateMode, firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(minusMode = true)
            }
            isOperatorButtonClickedFirst -> {
                calculateMode = CalculateMode(minusMode = true)
                setNumberToFirstNumber(inputNumber)
                isOperatorButtonClickedFirst = false
            }
            !isOperatorButtonClickedFirst -> {
                calculateMode = CalculateMode(minusMode = true)
                setInputNewNumber(true)
                if (isNumberClicked) {
                    answer = calculate(CalculateMode(minusMode = true), firstNumber, inputNumber)
                    firstNumber = answer
                    isNumberClicked = false
                    view.setTextEtAnswer(answer)
                }
            }
        }
    }

    fun onMultiplyClicked() {
        when {
            calculateMode.resultMode -> {
                calculateMode = CalculateMode(multiplyMode = true)
                setNumberToFirstNumber(answer)
            }
            !isOperatorButtonClickedFirst && !calculateMode.multiplyMode -> {
                answer = calculate(calculateMode, firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(multiplyMode = true)
            }
            isOperatorButtonClickedFirst -> {
                calculateMode = CalculateMode(multiplyMode = true)
                setNumberToFirstNumber(inputNumber)
                isOperatorButtonClickedFirst = false
            }
            !isOperatorButtonClickedFirst -> {
                calculateMode = CalculateMode(multiplyMode = true)
                setInputNewNumber(true)
                if (isNumberClicked) {
                    answer = calculate(CalculateMode(multiplyMode = true), firstNumber, inputNumber)
                    firstNumber = answer
                    isNumberClicked = false
                    view.setTextEtAnswer(answer)
                }
            }
        }
    }

    fun onDivideClicked() {
        when {
            calculateMode.resultMode -> {
                calculateMode = CalculateMode(divideMode = true)
                setNumberToFirstNumber(answer)
            }
            !isOperatorButtonClickedFirst && !calculateMode.divideMode -> {
                answer = calculate(calculateMode, firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(divideMode = true)
            }
            isOperatorButtonClickedFirst -> {
                calculateMode = CalculateMode(divideMode = true)
                setNumberToFirstNumber(inputNumber)
                isOperatorButtonClickedFirst = false
            }
            !isOperatorButtonClickedFirst -> {
                calculateMode = CalculateMode(divideMode = true)
                setInputNewNumber(true)
                if (isNumberClicked) {
                    answer = calculate(CalculateMode(divideMode = true), firstNumber, inputNumber)
                    firstNumber = answer
                    isNumberClicked = false
                    view.setTextEtAnswer(answer)
                }
            }
        }
    }

    fun onResultClicked() {
        setInputNewNumber(true)

        when(calculateMode) {
            CalculateMode(plusMode = true) -> {
                answer = calculate(CalculateMode(plusMode = true), firstNumber, inputNumber)
                firstNumber = inputNumber
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
                calculateMode = CalculateMode(plusMode = true, resultMode = true)
            }
            CalculateMode(plusMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(plusMode = true, resultMode = true), answer, inputNumber)
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
            }
            CalculateMode(minusMode = true) -> {
                answer = calculate(CalculateMode(minusMode = true), firstNumber, this.inputNumber)
                firstNumber = inputNumber
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
                calculateMode = CalculateMode(minusMode = true, resultMode = true)
            }
            CalculateMode(minusMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(minusMode = true, resultMode = true), inputNumber, answer)
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
            }
            CalculateMode(multiplyMode = true) -> {
                answer = calculate(CalculateMode(multiplyMode = true), firstNumber, inputNumber)
                firstNumber = inputNumber
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
                calculateMode = CalculateMode(multiplyMode = true, resultMode = true)
            }
            CalculateMode(multiplyMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(multiplyMode = true, resultMode = true), answer, inputNumber)
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
            }
            CalculateMode(divideMode = true) -> {
                answer = calculate(CalculateMode(divideMode = true), firstNumber, inputNumber)
                firstNumber = inputNumber
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
                calculateMode = CalculateMode(divideMode = true, resultMode = true)
            }
            CalculateMode(divideMode = true, resultMode = true) -> {
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

    private fun setNumberToFirstNumber(number: Long) {
        this.firstNumber = number
        isOperatorButtonClickedFirst = false
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
