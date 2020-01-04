package com.gorillamo.pickers

import android.content.Context
import android.view.WindowManager
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.gorillamo.pickers.adapters.DoubleRowSelectAdapter
import com.gorillamo.pickers.adapters.SimplePickerAdapter
import com.gorillamo.pickers.decorations.DetectableLinearSnapHelper
import com.gorillamo.pickers.decorations.OffsetItemDecoration


fun RecyclerView.createSimplePicker(
    startingPosition:Int,
    choices: Array<String>,
    choiceCallback:((Int)->Unit)? = null
):LinearSnapHelper{

    val snapHelper = DetectableLinearSnapHelper(this,choiceCallback)
    layoutManager =  object:GridLayoutManager(context, 1){

        override fun onLayoutCompleted(state: RecyclerView.State?) {
            super.onLayoutCompleted(state)

            //Scroll to the desired position
            scrollToPosition(startingPosition)
            post{
                findViewByPosition(startingPosition)?.let {
                    val snapDistance = snapHelper.calculateDistanceToFinalSnap(layoutManager!!,it)
                    if (snapDistance!![0] != 0 || snapDistance[1] != 0) {
                        smoothScrollBy(snapDistance[0], snapDistance[1])
                    }
                }
            }
        }
    }.apply {
        orientation = GridLayoutManager.HORIZONTAL
    }
    snapHelper.attachToRecyclerView(this)

    adapter = SimplePickerAdapter(choices){view ->

        val snapDistance = snapHelper.calculateDistanceToFinalSnap(layoutManager!!, view)
        if (snapDistance!![0] != 0 || snapDistance[1] != 0) {
            this.smoothScrollBy(snapDistance[0], snapDistance[1])
        }
    }
    addItemDecoration(OffsetItemDecoration(context.getSystemService(Context.WINDOW_SERVICE) as WindowManager))




    return snapHelper
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

