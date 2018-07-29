package com.chaeniiz.calculator

enum class CalculateModeState(val case: CalculateMode) {
    DEFAULT(CalculateMode(plusMode = false, minusMode = false, multiplyMode = false, divideMode = false, resultMode = false)),
    PLUS_ONCE(CalculateMode(plusMode = true)),
    PLUS_CONTINUE(CalculateMode(plusMode = true, resultMode = true)),
    MINUS_ONCE(CalculateMode(minusMode = true)),
    MINUS_CONTINUE(CalculateMode(minusMode = true, resultMode = true)),
    MULTIPLY_ONCE(CalculateMode(multiplyMode = true)),
    MULTIPLY_CONTINUE(CalculateMode(multiplyMode = true, resultMode = true)),
    DIVIDE_ONCE(CalculateMode(divideMode = true)),
    DIVIDE_CONTINUE(CalculateMode(divideMode = true, resultMode = true))
}
