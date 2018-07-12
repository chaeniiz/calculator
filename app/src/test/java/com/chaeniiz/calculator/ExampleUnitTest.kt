package com.chaeniiz.calculator

import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun calculateTest() {
        assertEquals(4, calculate(CalculateMode(plusMode = true), 2, 2))
        assertEquals(3, calculate(CalculateMode(minusMode = true), 6, 3))
        assertEquals(12, calculate(CalculateMode(multiplyMode = true), 4, 3))
        assertEquals(2, calculate(CalculateMode(divideMode = true), 10, 5))
    }

    @Test
    fun resultModeTest() {
        val presenter = MainPresenter(MainActivity())

        assertEquals(4, presenter.calculate(CalculateMode(plusMode = true, resultMode = true), 2, 2))
        assertEquals(4, presenter.calculate(CalculateMode(minusMode = true, resultMode = true), 4, 8))
        assertEquals(32, presenter.calculate(CalculateMode(multiplyMode = true, resultMode = true), 4, 8))
        assertEquals(3, presenter.calculate(CalculateMode(divideMode = true, resultMode = true), 4, 12))
    }

    @Test
    fun onlyOperatorsInputedTest() {
        val presenter = MainPresenter(MainActivity())

//        activity.onPlusClicked(true)
//        activity.onMinusClicked(true)
//        activity.onMultiplyClicked(true)
//        activity.onDivideClicked(true)
//
//        assertEquals(0, activity.etAnswer.text)
    }

    @Test
    fun divideToZeroTest() {
        assertEquals(5, calculate(CalculateMode(divideMode = true), 5, 0))
    }

    @Test
    fun numberFormatExceptionTest() {
        val presenter = MainPresenter(MainActivity())

        for(i in 1..18) {
            presenter.onNumberClicked(9)
        }

 //       assertEquals(activity.etAnswer.text, presenter.onNumberClicked(9))
    }

    private fun calculate(calculateMode: CalculateMode, firstNum: Long, secondNum: Long): Long {
        val presenter = MainPresenter(MainActivity())

        return presenter.calculate(calculateMode, firstNum, secondNum)
    }
}
