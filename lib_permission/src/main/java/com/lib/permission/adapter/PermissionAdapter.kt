package com.lib.permission.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lib.permission.R
import com.lib.permission.bean.PermissionVo

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020-03-31.
 */
class PermissionAdapter(context: Context) : RootAdapter<PermissionVo>(context) {

    private var mFilterColor = 0

    private var mTextColor = 0

    override fun getExView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.adapter_item, null)
        val nameTv = view.findViewById<TextView>(R.id.tv_name)
        val iconIv = view.findViewById<ImageView>(R.id.iv_icon)

        val permissionVo = mList[position]
        nameTv.text = permissionVo.permissionName
        if (mTextColor != 0) {
            nameTv.setTextColor(mTextColor)
        }

        if (mFilterColor != 0) {
            val blue = Color.blue(mFilterColor)
            val green = Color.green(mFilterColor)
            val red = Color.red(mFilterColor)
            val cm = floatArrayOf(
                1f, 0f, 0f, 0f, red.toFloat(),
                0f, 1f, 0f, 0f, green.toFloat(),
                0f, 0f, 1f, 0f, blue.toFloat(),
                0f, 0f, 0f, 1f, 1f
            )
            val filter = ColorMatrixColorFilter(cm)
            iconIv.colorFilter = filter
        }
        iconIv.setImageResource(permissionVo.permissionIcon)

        return view
    }


    fun setFilterColor(filterColor: Int) {
        mFilterColor = filterColor
    }

    fun setTextColor(itemTextColor: Int) {
        mTextColor = itemTextColor
    }
}