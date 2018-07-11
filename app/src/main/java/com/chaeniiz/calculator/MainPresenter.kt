package com.chaeniiz.calculator

class MainPresenter(val view: MainView) {
    var answer: Long = 0
    var firstNumber: Long = 0
    var secondNumber: Long = 0
    var isFirst: Boolean = true
    var calculateMode: CalculateMode = CalculateMode()

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
    }

    fun onPlusClicked() {
        when {
            !isFirst && !calculateMode.plusMode -> {
                answer = calculate(calculateMode, firstNumber, view.getAnswerViewData())
                view.setAnswerViewData(answer)
                firstNumber = answer
                view.setInputNewNumber(true)
                calculateMode = CalculateMode(plusMode = true)
            }
            isFirst || calculateMode.resultMode -> {
                calculateMode = CalculateMode(plusMode = true)
                setFirstNumber()
            }
            !isFirst -> {
                calculateMode = CalculateMode(plusMode = true)
                view.setInputNewNumber(true)
                if (view.getNumberButtonClicked()) {
                    answer = calculate(CalculateMode(plusMode = true), firstNumber, view.getAnswerViewData())
                    firstNumber = answer
                    view.setNumberButtonClicked(false)
                    view.setAnswerViewData(answer)
                }
            }
        }
    }

    fun onMinusClicked() {
        when {
            !isFirst && !calculateMode.minusMode -> {
                answer = calculate(calculateMode, firstNumber, view.getAnswerViewData())
                view.setAnswerViewData(answer)
                firstNumber = answer
                view.setInputNewNumber(true)
                calculateMode = CalculateMode(minusMode = true)
            }
            isFirst || calculateMode.resultMode -> {
                calculateMode = CalculateMode(minusMode = true)
                setFirstNumber()
            }
            !isFirst -> {
                calculateMode = CalculateMode(minusMode = true)
                view.setInputNewNumber(true)
                if (view.getNumberButtonClicked()) {
                    answer = calculate(CalculateMode(minusMode = true), firstNumber, view.getAnswerViewData())
                    firstNumber = answer
                    view.setNumberButtonClicked(false)
                    view.setAnswerViewData(answer)
                }
            }
        }
    }

    fun onMultiplyClicked() {
        when {
            !isFirst && !calculateMode.multiplyMode -> {
                answer = calculate(calculateMode, firstNumber, view.getAnswerViewData())
                view.setAnswerViewData(answer)
                firstNumber = answer
                view.setInputNewNumber(true)
                calculateMode = CalculateMode(multiplyMode = true)
            }
            isFirst || calculateMode.resultMode -> {
                calculateMode = CalculateMode(multiplyMode = true)
                setFirstNumber()
            }
            !isFirst -> {
                calculateMode = CalculateMode(multiplyMode = true)
                view.setInputNewNumber(true)
                if (view.getNumberButtonClicked()) {
                    answer = calculate(CalculateMode(multiplyMode = true), firstNumber, view.getAnswerViewData())
                    firstNumber = answer
                    view.setNumberButtonClicked(false)
                    view.setAnswerViewData(answer)
                }
            }
        }
    }

    fun onDivideClicked() {
        when {
            !isFirst && !calculateMode.divideMode -> {
                answer = calculate(calculateMode, firstNumber, view.getAnswerViewData())
                view.setAnswerViewData(answer)
                firstNumber = answer
                view.setInputNewNumber(true)
                calculateMode = CalculateMode(divideMode = true)
            }
            isFirst || calculateMode.resultMode -> {
                calculateMode = CalculateMode(divideMode = true)
                setFirstNumber()
            }
            !isFirst -> {
                calculateMode = CalculateMode(divideMode = true)
                view.setInputNewNumber(true)
                if (view.getNumberButtonClicked()) {
                    answer = calculate(CalculateMode(divideMode = true), firstNumber, view.getAnswerViewData())
                    firstNumber = answer
                    view.setNumberButtonClicked(false)
                    view.setAnswerViewData(answer)
                }
            }
        }
    }

    fun onResultClicked() {
        isFirst = true
        view.setInputNewNumber(true)
        secondNumber = view.getAnswerViewData()

        when(calculateMode) {
            CalculateMode(plusMode = true) -> {
                answer = calculate(CalculateMode(plusMode = true), firstNumber, secondNumber)
                firstNumber = secondNumber
                view.setAnswerViewData(answer)
                view.setInputNewNumber(true)
                view.setNumberButtonClicked(true)
                calculateMode = CalculateMode(plusMode = true, resultMode = true)
            }
            CalculateMode(plusMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(plusMode = true, resultMode = true), firstNumber, view.getAnswerViewData())
                view.setAnswerViewData(answer)
                view.setInputNewNumber(true)
                view.setNumberButtonClicked(true)
            }
            CalculateMode(minusMode = true) -> {
                answer = calculate(CalculateMode(minusMode = true), firstNumber, secondNumber)
                firstNumber = secondNumber
                view.setAnswerViewData(answer)
                view.setInputNewNumber(true)
                view.setNumberButtonClicked(true)
                calculateMode = CalculateMode(minusMode = true, resultMode = true)
            }
            CalculateMode(minusMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(minusMode = true, resultMode = true), firstNumber, view.getAnswerViewData())
                view.setAnswerViewData(answer)
                view.setInputNewNumber(true)
                view.setNumberButtonClicked(true)
            }
            CalculateMode(multiplyMode = true) -> {
                answer = calculate(CalculateMode(multiplyMode = true), firstNumber, secondNumber)
                firstNumber = secondNumber
                view.setAnswerViewData(answer)
                view.setInputNewNumber(true)
                view.setNumberButtonClicked(true)
                calculateMode = CalculateMode(multiplyMode = true, resultMode = true)
            }
            CalculateMode(multiplyMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(multiplyMode = true, resultMode = true), firstNumber, view.getAnswerViewData())
                view.setAnswerViewData(answer)
                view.setInputNewNumber(true)
                view.setNumberButtonClicked(true)
            }
            CalculateMode(divideMode = true) -> {
                answer = calculate(CalculateMode(divideMode = true), firstNumber, secondNumber)
                firstNumber = secondNumber
                view.setAnswerViewData(answer)
                view.setInputNewNumber(true)
                view.setNumberButtonClicked(true)
                calculateMode = CalculateMode(divideMode = true, resultMode = true)
            }
            CalculateMode(divideMode = true, resultMode = true) -> {
                answer = calculate(CalculateMode(divideMode = true, resultMode = true), firstNumber, secondNumber)
                view.setAnswerViewData(answer)
                view.setInputNewNumber(true)
                view.setNumberButtonClicked(true)
            }
        }
    }

    fun setFirstNumber() {
        firstNumber = view.getAnswerViewData()
        isFirst = false
        view.setNumberButtonClicked(false)
        view.setInputNewNumber(true)
    }

    fun onNumberClicked(number: Long) {
        view.setNumberButtonClicked(true)
        val stringBuilder = StringBuilder()

        when {
            view.isInputNewNumber() -> {
                view.setAnswerViewData(number)
                view.setInputNewNumber(false)
            }
            view.getAnswerViewData().equals(0) -> {
                view.setAnswerViewData(number)
            }
            else -> {
                view.setAnswerViewData(stringBuilder.append(view.getAnswerViewData().toString()).append(number).toString().toLong())
            }
        }
    }
}