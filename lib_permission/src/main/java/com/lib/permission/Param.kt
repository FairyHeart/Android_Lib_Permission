package com.lib.permission

import android.os.Parcelable
import com.lib.permission.bean.PermissionVo
import kotlinx.android.parcel.Parcelize

/**
 *
 *
 * @author: 笨小孩.
 * @date  : 2020/3/29.
 */
@Parcelize
internal class Param(
    var permissions: MutableList<PermissionVo>,
    var showCustomDialog: Boolean = false
) : Parcelable