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


class FrequencyPickerFragment : Fragment() {

    private lateinit var submit:((Int, Int)->Any?)

    private var count :Int = 1
    private var range : Int = 1

    private var countIndex = 0
    private var rangeIndex = 0

    private var countSnapHelper:LinearSnapHelper? = null
    private var rangeSnapHelper:LinearSnapHelper? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frequency_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val amountArray = arrayOf("Once", "Twice","3x","4x", "5x","10x")
        val amountArray = arrayOf("Once")
        countSnapHelper = recyclerCount?.createSimplePicker(countIndex,amountArray){
            count = getAmount(amountArray[it])
            submit(count,range)
        }

        val timeArray = arrayOf("Day", "2 Days","3 Days","Week","Month","Year" )
        rangeSnapHelper = recyclerRange?.createSimplePicker(rangeIndex,timeArray){

            range = getTimeSpan(timeArray[it])
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

    private fun getAmount(amountString:String):Int{

        return when(amountString){

            "Once" -> 1
            "Twice" -> 2
            "3x" -> 3
            "4x" -> 4
            "5x" -> 5
            "10x" -> 10
            else -> 1

        }
    }

    private fun getTimeSpan(timeString:String):Int{

        return when (timeString) {
            "Day" -> 1
            "2 Days" -> 2
            "3 Days" -> 3
            "4 Days" -> 4
            "Week" -> 7
            "Month" -> 30
//            "2 Months" -> 60.0f
//            "6 Months" -> (30.0*6.0f).toFloat()
            "Year" -> 365
            else -> 1
        }
    }

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