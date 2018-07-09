package com.chaeniiz.calculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun calculateTest() {
        assertEquals(4, plus(2, 2))
        assertEquals(3, minus(6, 3))
        assertEquals(12, multiply(4, 3))
        assertEquals(2, divide(10, 5))
    }

    private fun plus(firstNum: Long, secondNum: Long) : Long {
        val activity = MainActivity()

        activity.calculateMode = CalculateMode(plusMode = true)

        return activity.calculate(firstNum, secondNum)
    }

    private fun minus(firstNum: Long, secondNum: Long) : Long {
        val activity = MainActivity()

        activity.calculateMode = CalculateMode(minusMode = true)

        return activity.calculate(firstNum, secondNum)
    }

    private fun multiply(firstNum: Long, secondNum: Long) : Long {
        val activity = MainActivity()

        activity.calculateMode = CalculateMode(multiplyMode = true)

        return activity.calculate(firstNum, secondNum)
    }

    private fun divide(firstNum: Long, secondNum: Long) : Long {
        val activity = MainActivity()

        activity.calculateMode = CalculateMode(divideMode = true)

        return activity.calculate(firstNum, secondNum)
    }
}
