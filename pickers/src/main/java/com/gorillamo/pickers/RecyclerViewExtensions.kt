package com.gorillamo.pickers

import android.content.Context
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorillamo.pickers.adapters.DoubleRowSelectAdapter
import com.gorillamo.pickers.adapters.SimplePickerAdapter
import com.gorillamo.pickers.decorations.DetectableLinearSnapHelper
import com.gorillamo.pickers.decorations.OffsetItemDecoration


fun RecyclerView.createSimplePicker(
    choices: Array<String>,
    choiceCallback:((Int)->Unit)? = null
){

    val snapHelper = DetectableLinearSnapHelper(this,choiceCallback)
    layoutManager =   GridLayoutManager(context, 1).apply {
        orientation = GridLayoutManager.HORIZONTAL
        scrollToPosition(0)
    }
    snapHelper.attachToRecyclerView(this)
    adapter = SimplePickerAdapter(choices){view ->

        val snapDistance = snapHelper.calculateDistanceToFinalSnap(layoutManager!!, view)
        if (snapDistance!![0] != 0 || snapDistance[1] != 0) {
            this.smoothScrollBy(snapDistance[0], snapDistance[1])
        }
    }

    addItemDecoration(OffsetItemDecoration(context.getSystemService(Context.WINDOW_SERVICE) as WindowManager))
}

fun RecyclerView.createDoubleRowPicker(
    choices:Array<String>,
    style:Int = 0,
    choiceCallback: ((String) -> Any)? = null
){

    this.adapter = DoubleRowSelectAdapter(choices,choiceCallback,style)
    val gridLayoutManager = GridLayoutManager(context, 2)
    gridLayoutManager.orientation = GridLayoutManager.HORIZONTAL
    gridLayoutManager.scrollToPosition(4)
    this.layoutManager =  gridLayoutManager
    addItemDecoration(OffsetItemDecoration(context.getSystemService(Context.WINDOW_SERVICE) as WindowManager))

}