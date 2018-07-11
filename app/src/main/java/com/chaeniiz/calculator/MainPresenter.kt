package com.chaeniiz.calculator

class MainPresenter(val view: MainView) {

    private var firstNumber: Long = 0
    private var inputNumber: Long = 0
    private var answer: Long = 0
    private var isFirstOperatorButtonClicked: Boolean = true
    private var isNewNumber: Boolean = true
    private var isNumberClicked: Boolean = false
    private var calculateMode: CalculateMode = CalculateMode()

    fun onCreate() {
        view.showCalculateFragment()
    }

    private fun calculate(calculateMode: CalculateMode, firstNumber: Long, secondNumber: Long): Long {
        when (calculateMode) {
            CalculateMode(plusMode = true),
            CalculateMode(plusMode = true, resultMode = true) ->
                answer = firstNumber + secondNumber
            CalculateMode(minusMode = true) ->
                answer = firstNumber - secondNumber
            CalculateMode(multiplyMode = true),
            CalculateMode(multiplyMode = true, resultMode = true) ->
                answer = firstNumber * secondNumber
            CalculateMode(divideMode = true) ->
                answer = firstNumber / secondNumber
            CalculateMode(minusMode = true, resultMode = true) ->
                answer = secondNumber - firstNumber
            CalculateMode(divideMode = true, resultMode = true) ->
                answer = secondNumber / firstNumber
        }
        return answer
    }

    fun initNumberStatus() {
        isFirstOperatorButtonClicked = true
        firstNumber = 0
        answer = 0
        inputNumber = 0
    }

    fun onPlusClicked(inputNumber: Long) {
        when {
            !isFirstOperatorButtonClicked && !calculateMode.plusMode -> {
                answer = calculate(calculateMode, firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(plusMode = true)
            }
            isFirstOperatorButtonClicked || calculateMode.resultMode -> {
                calculateMode = CalculateMode(plusMode = true)
                setNumberToFirstNumber(inputNumber)
            }
            !isFirstOperatorButtonClicked -> {
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

    fun onMinusClicked(inputNumber: Long) {
        when {
            !isFirstOperatorButtonClicked && !calculateMode.minusMode -> {
                answer = calculate(calculateMode, firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(minusMode = true)
            }
            isFirstOperatorButtonClicked || calculateMode.resultMode -> {
                calculateMode = CalculateMode(minusMode = true)
                setNumberToFirstNumber(inputNumber)
            }
            !isFirstOperatorButtonClicked -> {
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

    fun onMultiplyClicked(inputNumber: Long) {
        when {
            !isFirstOperatorButtonClicked && !calculateMode.multiplyMode -> {
                answer = calculate(calculateMode, firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(multiplyMode = true)
            }
            isFirstOperatorButtonClicked || calculateMode.resultMode -> {
                calculateMode = CalculateMode(multiplyMode = true)
                setNumberToFirstNumber(inputNumber)
            }
            !isFirstOperatorButtonClicked -> {
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

    fun onDivideClicked(inputNumber: Long) {
        when {
            !isFirstOperatorButtonClicked && !calculateMode.divideMode -> {
                answer = calculate(calculateMode, firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(divideMode = true)
            }
            isFirstOperatorButtonClicked || calculateMode.resultMode -> {
                calculateMode = CalculateMode(divideMode = true)
                setNumberToFirstNumber(inputNumber)
            }
            !isFirstOperatorButtonClicked -> {
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

    fun onResultClicked(inputNumber: Long) {
        isFirstOperatorButtonClicked = true
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
                answer = calculate(CalculateMode(plusMode = true, resultMode = true), firstNumber, inputNumber)
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
                answer = calculate(CalculateMode(minusMode = true, resultMode = true), firstNumber, inputNumber)
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
                answer = calculate(CalculateMode(multiplyMode = true, resultMode = true), firstNumber, inputNumber)
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
                answer = calculate(CalculateMode(divideMode = true, resultMode = true), firstNumber, inputNumber)
                view.setTextEtAnswer(answer)
                setInputNewNumber(true)
            }
        }
    }

    private fun setNumberToFirstNumber(number: Long) {
        this.firstNumber = number
        isFirstOperatorButtonClicked = false
        isNumberClicked = false
        setInputNewNumber(true)
    }

    private fun setInputNewNumber(isInputNewNumber: Boolean) {
        isNewNumber = isInputNewNumber
    }

    fun onNumberClicked(number: Long) {
        isNumberClicked = true
        val stringBuilder = StringBuilder()

        when {
            isNewNumber -> {
                view.setTextEtAnswer(number)
                inputNumber = number
                setInputNewNumber(false)
            }
            number.equals(0) -> {
                view.setTextEtAnswer(number)
            }
            else -> {
                inputNumber = stringBuilder.append(inputNumber).append(number).toString().toLong()
                view.setTextEtAnswer(inputNumber)
            }
        }
    }
}
