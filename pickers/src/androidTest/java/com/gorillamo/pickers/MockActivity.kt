package com.gorillamo.pickers

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gorillamo.ui.choosablelist.ChoosableList

class MockActivity : AppCompatActivity(){

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        supportFragmentManager.beginTransaction()
            .add(com.gorillamo.pickers.test.R.id.pickerContainer,FrequencyPickerFragment.newInstance(MockActivity.count,MockActivity.range){ count, range ->

                MockActivity.count = count
                MockActivity.range = range

            })
            .commit()
    }

    companion object{
        var layout:Int = 0
        var range = 1
        var count = 1

    }

}