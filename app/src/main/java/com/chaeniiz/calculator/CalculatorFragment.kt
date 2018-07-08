package com.chaeniiz.calculator

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun onNumberClicked(number: String)
        fun onResetClicked(reset: Boolean)
        fun onPlusClicked(plus: Boolean)
        fun onMinusClicked(minus: Boolean)
        fun onMultiplyClicked(multiply: Boolean)
        fun onDivideClicked(divide: Boolean)
        fun onResultClicked(result: Boolean)
    }

    companion object {
        fun newInstance() : CalculatorFragment  = CalculatorFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_calculator, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNumberZero.setOnClickListener { onNumberPressed("0") }
        btnNumberOne.setOnClickListener { onNumberPressed("1") }
        btnNumberTwo.setOnClickListener { onNumberPressed("2") }
        btnNumberThree.setOnClickListener { onNumberPressed("3") }
        btnNumberFour.setOnClickListener { onNumberPressed("4") }
        btnNumberFive.setOnClickListener { onNumberPressed("5") }
        btnNumberSix.setOnClickListener { onNumberPressed("6") }
        btnNumberSeven.setOnClickListener { onNumberPressed("7") }
        btnNumberEight.setOnClickListener { onNumberPressed("8") }
        btnNumberNine.setOnClickListener { onNumberPressed("9") }
        btnReset.setOnClickListener { onResetPressed(true) }
        btnResult.setOnClickListener { onResultPressed(true) }
        btnPlus.setOnClickListener { onPlusPressed(true) }
        btnMinus.setOnClickListener { onMinusPressed(true) }
        btnMultiply.setOnClickListener { onMultiplyPressed(true) }
        btnDivide.setOnClickListener { onDividePressed(true) }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun onNumberPressed(number: String) {
        //(activity as? OnFragmentInteractionListener)?.onNumberClicked()
        if (mListener != null) {
            mListener!!.onNumberClicked(number)
        }
    }

    private fun onResetPressed(reset: Boolean) {
        if (mListener != null) {
            mListener!!.onResetClicked(reset)
        }
    }

    private fun onResultPressed(result: Boolean) {
        if (mListener != null) {
            mListener!!.onResultClicked(result)
        }
    }

    private fun onPlusPressed(plus: Boolean) {
        if (mListener != null)
            mListener!!.onPlusClicked(plus)
    }

    private fun onMinusPressed(minus: Boolean) {
        if (mListener != null)
            mListener!!.onMinusClicked(minus)
    }

    private fun onMultiplyPressed(multiply: Boolean) {
        if (mListener != null)
            mListener!!.onMultiplyClicked(multiply)
    }

    private fun onDividePressed(divide: Boolean) {
        if (mListener != null)
            mListener!!.onDivideClicked(divide)
    }

}
