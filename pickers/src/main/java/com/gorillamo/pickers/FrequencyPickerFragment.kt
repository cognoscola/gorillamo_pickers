package com.gorillamo.pickers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.fragment_frequency_picker.*

//TODO create tests
//TODO Create a View for the recycler views
//TODO which uses sealed classes

class FrequencyPickerFragment : Fragment() {

    private lateinit var submit:((Int, Int)->Any?)

    private var count :Int = 1
    private var range : Int = 1

    private var countIndex = 0
    private var rangeIndex = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frequency_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val amountArray = arrayOf("Once", "Twice","3x","4x", "5x","10x")
        recyclerCount?.setItemClickcallback {
            count = it
            submit(count,range)
        }

        recyclerRange.setItemClickcallback{
            range = it
            submit(count,range)
        }

        //by default our frequency is once per day
        submit(count,range)
    }

    companion object {

        @JvmStatic
        fun newInstance(count:Int,range:Int, callback:((Int,Int)->Unit)) = FrequencyPickerFragment().apply {
            this.countIndex = getCountIndex(count)
            this.rangeIndex = getRangeIndex(range)
            this.count = count
            this.range = range

            submit  = callback
        }
    }

    private fun findFrequency(amount:Float, timeSpan:Float):Float = amount.toFloat().div(timeSpan)

    //TODO MOVE TO SEPERATE SEALED CLASS OBJECT
    private fun getRangeIndex(value:Int):Int{

        return when (value) {
            1 -> 0 //index 0
            2 -> 1
            3-> 2
            7 -> 3
            30 -> 4
            365 -> 5
            else -> 0
        }

    }

    private fun getCountIndex(value:Int):Int{
        return when (value) {
            1 -> 0 //index 0
            2 -> 1
            3-> 2
            4 -> 3
            5-> 4
            10 -> 5
            else -> 0
        }
    }

}