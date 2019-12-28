package com.gorillamo.pickerstester

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.gorillamo.pickers.FrequencyPickerFragment

import kotlinx.android.synthetic.main.activity_picker_test.*

class PickerTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker_test)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            DialogTester().show(supportFragmentManager.beginTransaction(),"Show")
        }

       /* supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer,FrequencyPickerFragment.newInstance{
                resultTextView?.text = "$it"
            })
            .commit()*/



    }
}
