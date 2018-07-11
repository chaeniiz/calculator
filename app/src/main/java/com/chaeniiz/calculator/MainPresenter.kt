package com.chaeniiz.calculator

class MainPresenter(val view: MainView) {
    var answer: Long = 0
    var firstNumber: Long = 0
    var secondNumber: Long = 0
    var isFirst: Boolean = true
    var isNewNumber: Boolean = true
    var isNumberClicked: Boolean = false
    var calculateMode: CalculateMode = CalculateMode()

    var result: Long = 0

    fun onCreate() {
        view.showCalculateFragment()
    }

    fun calculate(calculateMode: CalculateMode, firstNumber: Long, secondNumber: Long): Long {
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

    fun initNumbersStatus() {
        isFirst = true
        firstNumber = 0
        secondNumber = 0
        answer = 0
        result = 0
    }

    fun onPlusClicked(secondNumber: Long) {
        when {
            !isFirst && !calculateMode.plusMode -> {
                answer = calculate(calculateMode, firstNumber, secondNumber)
                view.setAnswerViewData(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(plusMode = true)
            }
            isFirst || calculateMode.resultMode -> {
                calculateMode = CalculateMode(plusMode = true)
                setFirstNumberStatus(secondNumber)
            }
            !isFirst -> {
                calculateMode = CalculateMode(plusMode = true)
                setInputNewNumber(true)
                if (isNumberClicked) {
                    answer = calculate(CalculateMode(plusMode = true), firstNumber, secondNumber)
                    firstNumber = answer
                    isNumberClicked = false
                    view.setAnswerViewData(answer)
                }
            }
        }
    }

    fun onMinusClicked(secondNumber: Long) {
        when {
            !isFirst && !calculateMode.minusMode -> {
                answer = calculate(calculateMode, firstNumber, secondNumber)
                view.setAnswerViewData(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(minusMode = true)
            }
            isFirst || calculateMode.resultMode -> {
                calculateMode = CalculateMode(minusMode = true)
                setFirstNumberStatus(secondNumber)
            }
            !isFirst -> {
                calculateMode = CalculateMode(minusMode = true)
                setInputNewNumber(true)
                if (isNumberClicked) {
                    answer = calculate(CalculateMode(minusMode = true), firstNumber, secondNumber)
                    firstNumber = answer
                    isNumberClicked = false
                    view.setAnswerViewData(answer)
                }
            }
        }
    }

    fun onMultiplyClicked(secondNumber: Long) {
        when {
            !isFirst && !calculateMode.multiplyMode -> {
                answer = calculate(calculateMode, firstNumber, secondNumber)
                view.setAnswerViewData(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(multiplyMode = true)
            }
            isFirst || calculateMode.resultMode -> {
                calculateMode = CalculateMode(multiplyMode = true)
                setFirstNumberStatus(secondNumber)
            }
            !isFirst -> {
                calculateMode = CalculateMode(multiplyMode = true)
                setInputNewNumber(true)
                if (isNumberClicked) {
                    answer = calculate(CalculateMode(multiplyMode = true), firstNumber, secondNumber)
                    firstNumber = answer
                    isNumberClicked = false
                    view.setAnswerViewData(answer)
                }
            }
        }
    }

    fun onDivideClicked(secondNumber: Long) {
        when {
            !isFirst && !calculateMode.divideMode -> {
                answer = calculate(calculateMode, firstNumber, secondNumber)
                view.setAnswerViewData(answer)
                firstNumber = answer
                setInputNewNumber(true)
                calculateMode = CalculateMode(divideMode = true)
            }
            isFirst || calculateMode.resultMode -> {
                calculateMode = CalculateMode(divideMode = true)
                setFirstNumberStatus(secondNumber)
            }
            !isFirst -> {
                calculateMode = CalculateMode(divideMode = true)
                setInputNewNumber(true)
                if (isNumberClicked) {
                    answer = calculate(CalculateMode(divideMode = true), firstNumber, secondNumber)
                    firstNumber = answer
                    isNumberClicked = false
                    view.setAnswerViewData(answer)
                }
            }
        }
    }

    fun onResultClicked(secondNumber: Long) {
        isFirst = true
        setInputNewNumber(true)
        this.secondNumber = secondNumber

        when(calculateMode) {
            CalculateMode(plusMode = true) -> {
                answer = calculate(CalculateMode(plusMode = true), firstNumber, this.secondNumber)
                firstNumber = this.secondNumber
                view.setAnswerViewData(answer)
                setInputNewNumber(true)
                isNumberClicked = true
                calculateMode = CalculateMode(plusMode = true, resultMode = true)
            }
            CalculateMode(plusMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(plusMode = true, resultMode = true), firstNumber, secondNumber)
                view.setAnswerViewData(answer)
                setInputNewNumber(true)
                isNumberClicked = true
            }
            CalculateMode(minusMode = true) -> {
                answer = calculate(CalculateMode(minusMode = true), firstNumber, this.secondNumber)
                firstNumber = this.secondNumber
                view.setAnswerViewData(answer)
                setInputNewNumber(true)
                isNumberClicked = true
                calculateMode = CalculateMode(minusMode = true, resultMode = true)
            }
            CalculateMode(minusMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(minusMode = true, resultMode = true), firstNumber, secondNumber)
                view.setAnswerViewData(answer)
                setInputNewNumber(true)
                isNumberClicked = true
            }
            CalculateMode(multiplyMode = true) -> {
                answer = calculate(CalculateMode(multiplyMode = true), firstNumber, this.secondNumber)
                firstNumber = this.secondNumber
                view.setAnswerViewData(answer)
                setInputNewNumber(true)
                isNumberClicked = true
                calculateMode = CalculateMode(multiplyMode = true, resultMode = true)
            }
            CalculateMode(multiplyMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(multiplyMode = true, resultMode = true), firstNumber, secondNumber)
                view.setAnswerViewData(answer)
                setInputNewNumber(true)
                isNumberClicked = true
            }
            CalculateMode(divideMode = true) -> {
                answer = calculate(CalculateMode(divideMode = true), firstNumber, this.secondNumber)
                firstNumber = this.secondNumber
                view.setAnswerViewData(answer)
                setInputNewNumber(true)
                isNumberClicked = true
                calculateMode = CalculateMode(divideMode = true, resultMode = true)
            }
            CalculateMode(divideMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(divideMode = true, resultMode = true), firstNumber, this.secondNumber)
                view.setAnswerViewData(answer)
                setInputNewNumber(true)
                isNumberClicked = true
            }
        }
    }

    fun setFirstNumberStatus(firstNumber: Long) {
        this.firstNumber = firstNumber
        isFirst = false
        isNumberClicked = false
        setInputNewNumber(true)
    }

    fun setInputNewNumber(isInputNewNumber: Boolean) {
        isNewNumber = isInputNewNumber
    }

    fun onNumberClicked(number: Long) {
        isNumberClicked = true
        val stringBuilder = StringBuilder()

        when {
            isNewNumber -> {
                view.setAnswerViewData(number)
                result = number
                setInputNewNumber(false)
            }
            number.equals(0) -> {
                view.setAnswerViewData(number)
            }
            else -> {
                result = stringBuilder.append(result).append(number).toString().toLong()
                view.setAnswerViewData(result)
            }
        }
    }
}
