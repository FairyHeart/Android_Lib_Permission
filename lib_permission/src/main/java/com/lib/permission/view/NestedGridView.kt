package com.lib.permission.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.GridView

/**
 *  专门嵌套使用的GridView，重写其onMeasure()方法使其显示所有数据不会出现滚动条
 *
 * @author: Admin.
 * @date  : 2019-08-07.
 */
class NestedGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GridView(context, attrs, defStyleAttr) {

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec =
            View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}
