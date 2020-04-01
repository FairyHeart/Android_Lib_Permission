package com.lib.permission.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 *
 * @author: 笨小孩.
 * @date  : 2020/3/29.
 *
 * @param permissions 权限集合
 * @param showCustomDialog 是否显示自定义的对话框
 * @param type 申请权限类型，单个申请、多个申请、再次申请
 * @param reminder true = 循环提醒申请权限
 * @param titleText 对话框标题
 * @param descText 对话框描述内容
 * @param nextBtnText 对话框按钮文案
 * @param style 对话框样式
 */
@Parcelize
internal class Param(
    var permissions: MutableList<PermissionVo>,
    var showCustomDialog: Boolean = false,
    var type: Int,
    var reminder: Boolean = false,
    var titleText: String? = null,
    var descText: String? = null,
    var nextBtnText: String? = null,
    var style: Int = 0
) : Parcelable