package com.lib.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.lib.permission.activity.PermissionActivity
import com.lib.permission.bean.PermissionVo
import com.lib.permission.listener.OnPermissionListener

/**
 * 动态授权
 *
 * @author: 笨小孩.
 * @date  : 2020/3/29.
 */
class Permission {

    private lateinit var mContext: Context

    fun checkSinglePermission(permission: String, listener: OnPermissionListener) {
        if (!checkVersion() || checkPermission(mContext, permission)) {
            listener.onFinish()
            return
        }
        PermissionActivity.setPermissionListener(listener)
        val intent = Intent(mContext, PermissionActivity::class.java)
        val permissions = mutableListOf<PermissionVo>(PermissionVo(permission))
        var param = Param(permissions, true)
        intent.putExtra(Constant.INTENT_PARAM, param)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mContext.startActivity(intent)
    }

    /**
     * 检测是否已经授权
     * @return true = 已授权
     */
    private fun checkPermission(context: Context, permission: String): Boolean {
        val checkPermission = ContextCompat.checkSelfPermission(context, permission)
        return checkPermission == PackageManager.PERMISSION_GRANTED
    }

    /**
     * 检测版本是否需要进行动态权限判断
     * @return true = 需要
     */
    private fun checkVersion(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    companion object {

        fun builder() = Builder()

        class Builder {

            fun build(mContext: Context): Permission {
                val permission = Permission()
                permission.mContext = mContext
                return permission
            }
        }
    }
}