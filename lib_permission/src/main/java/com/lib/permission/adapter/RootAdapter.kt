package com.lib.permission.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 *
 *
 * @author: Admin.
 * @date  : 2019-08-18.
 */
abstract class RootAdapter<T>(protected var context: Context) : BaseAdapter() {

    protected var mList: MutableList<T> = mutableListOf()

    open var list: MutableList<T>
        get() = mList
        set(list) {
            this.mList = list
            notifyDataSetChanged()
        }

    fun setCopyOnWriteArrayList(mList: MutableList<T>) {
        this.mList = mList
    }

    fun remove(t: T) {
        if (mList.contains(t)) {
            mList.remove(t)
        }
        notifyDataSetChanged()
    }

    fun appendToList(list: List<T>?) {
        if (list == null) {
            return
        }
        mList.addAll(list)
        notifyDataSetChanged()
    }

    fun appendToTopList(list: List<T>?) {
        if (list == null) {
            return
        }
        mList.addAll(0, list)
        notifyDataSetChanged()
    }

    fun clear() {
        mList.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): T? {
        return if (position > mList.size - 1) {
            null
        } else mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getExView(position, convertView, parent)
    }

    protected abstract fun getExView(position: Int, convertView: View?, parent: ViewGroup?): View

    /**
     * 添加到尾部
     *
     * @param t
     */
    fun appendBottom(t: T?) {
        if (t == null) {
            return
        }
        mList.add(t)
        notifyDataSetChanged()
    }

    /**
     * 添加到头部
     *
     * @param t
     */
    fun appendTop(t: T?) {
        if (t == null) {
            return
        }
        mList.add(0, t)
        notifyDataSetChanged()
    }
}