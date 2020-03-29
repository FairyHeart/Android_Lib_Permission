package com.lib.permission.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 权限bean
 *
 * @author: 笨小孩.
 * @date  : 2020/3/29.
 *
 * @param permission 权限
 * @param permissionName 权限显示的名字
 * @param permissionIcon 权限对应的图标
 */
@Parcelize
data class PermissionVo(
    val permission: String,
    val permissionName: String = "",
    val permissionIcon: Int = 0
) : Parcelable