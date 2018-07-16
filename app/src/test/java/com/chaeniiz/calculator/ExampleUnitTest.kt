package com.chaeniiz.calculator

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock lateinit var view: MainView

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

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
        val presenter = MainPresenter(view)

        presenter.onPlusClicked()
        presenter.onMinusClicked()
        presenter.onMultiplyClicked()
        presenter.onDivideClicked()

        verify(view, times(3)).setTextEtAnswer(0L)

        assertEquals(0, presenter.answer)
    }

    @Test
    fun divideToZeroTest() {
        assertEquals(5, calculate(CalculateMode(divideMode = true), 5, 0))
    }

    @Test
    fun numberFormatExceptionTest() {
        val presenter = MainPresenter(view)

        for(i in 1..100) {
            presenter.onNumberClicked(9)
        }

        assertEquals(999999999999999999, presenter.inputNumber)
    }

    private fun calculate(calculateMode: CalculateMode, firstNum: Long, secondNum: Long): Long {
        val presenter = MainPresenter(MainActivity())

        return presenter.calculate(calculateMode, firstNum, secondNum)
    }
}
