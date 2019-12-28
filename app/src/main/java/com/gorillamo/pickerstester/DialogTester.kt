package com.gorillamo.pickerstester

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.gorillamo.pickers.FrequencyPickerFragment
import kotlinx.android.synthetic.main.dialog_layout.*

class DialogTester :DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_layout,container,false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer,FrequencyPickerFragment.newInstance(1,7) { count, range ->
                title.text = "$count / $range"
            })
            .commit()
    }

}