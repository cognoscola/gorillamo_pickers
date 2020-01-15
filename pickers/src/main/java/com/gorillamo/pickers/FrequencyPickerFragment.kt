package com.gorillamo.pickers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.gorillamo.ui.choosablelist.ChoosableList
import kotlinx.android.synthetic.main.fragment_frequency_picker.*

class FrequencyPickerFragment : Fragment() {

    private lateinit var submit: ((Int, Int) -> Any?)

    private var count: Int = 0
    private var range: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frequency_picker, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCount?.setItemClickcallback {
            count = it
            submit(count, range)
        }

        recyclerRange.setStartingPosition(getRangeIndex(range))
        recyclerRange.setItemClickcallback {
            range = it
            submit(count, range)
        }


        //by default our frequency is once per day
        submit(count, range)
    }

    companion object {

        @JvmStatic
        fun newInstance(count: Int, range: Int, callback: ((Int, Int) -> Unit)) =
            FrequencyPickerFragment().apply {
                this.count = count
                this.range = range
                submit = callback
            }
    }

    private fun findFrequency(amount: Float, timeSpan: Float): Float =
        amount.toFloat().div(timeSpan)

    private fun getRangeIndex(value: Int): Int {

        return when (value) {
            1 -> 0 //index 0
            2 -> 1
            3 -> 2
            7 -> 3
            30 -> 4
            365 -> 5
            else -> 0
        }

    }

}