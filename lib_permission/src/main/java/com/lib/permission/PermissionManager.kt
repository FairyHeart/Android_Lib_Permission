package com.lib.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.lib.permission.activity.PermissionActivity
import com.lib.permission.bean.Param
import com.lib.permission.bean.PermissionVo
import com.lib.permission.constant.Constant
import com.lib.permission.listener.OnPermissionListener
import com.lib.permission.utils.TransformUtils

/**
 * 动态授权
 *
 * @author: 笨小孩.
 * @date  : 2020/3/29.
 */
class PermissionManager {

    private lateinit var builder: Builder
    /**
     * 单个权限授权，默认使用自带的图片和文案
     */
    fun checkSinglePermission(permission: Permission, listener: OnPermissionListener) {
        if (!checkVersion() || checkPermission(builder.mContext, permission)) {
            listener.onFinish()
            return
        }
        val permissions = mutableListOf(
            PermissionVo(
                permission,
                TransformUtils.transformText(builder.mContext, permission),
                TransformUtils.transformIcon(builder.mContext, permission)
            )
        )
        this.startActivity(
            listener,
            permissions,
            Constant.TYPE_SINGLE
        )
    }

    /**
     * 多个权限一起授权，默认使用自带的图片和文案
     */
    fun checkMultiplePermission(
        permissions: MutableList<Permission>,
        listener: OnPermissionListener
    ) {
        if (!checkVersion()) {
            listener.onFinish()
            return
        }
        //检查权限，过滤已允许的权限
        val permissionVos = mutableListOf<PermissionVo>()
        permissions.forEach {
            if (!checkPermission(builder.mContext, it)) {
                permissionVos.add(
                    PermissionVo(
                        it,
                        TransformUtils.transformText(builder.mContext, it),
                        TransformUtils.transformIcon(builder.mContext, it)
                    )
                )
            }
        }
        if (permissionVos.isNullOrEmpty()) {
            listener.onFinish()
        } else {
            this.startActivity(
                listener,
                permissionVos,
                Constant.TYPE_MUTI
            )
        }
    }

    /**
     * 单个权限授权
     */
    fun checkPermission(permission: PermissionVo, listener: OnPermissionListener) {
        if (!checkVersion()) {
            listener.onFinish()
            return
        }
        //检查权限，过滤已允许的权限
        if (checkPermission(builder.mContext, permission.permission)) {
            listener.onFinish()
            return
        }
        val permissions = mutableListOf(permission)
        this.startActivity(
            listener,
            permissions,
            Constant.TYPE_SINGLE
        )
    }

    /**
     * 多个权限一起授权
     */
    fun checkPermissions(permissions: MutableList<PermissionVo>, listener: OnPermissionListener) {
        if (!checkVersion()) {
            listener.onFinish()
            return
        }
        //检查权限，过滤已允许的权限
        val permissionVos = mutableListOf<PermissionVo>()
        permissions.forEach {
            if (!checkPermission(builder.mContext, it.permission)) {
                permissionVos.add(it)
            }
        }
        if (permissionVos.isNullOrEmpty()) {
            listener.onFinish()
        } else {
            this.startActivity(
                listener,
                permissionVos,
                Constant.TYPE_MUTI
            )
        }
    }

    private fun startActivity(
        listener: OnPermissionListener,
        permissions: MutableList<PermissionVo>,
        type: Int
    ) {
        PermissionActivity.setPermissionListener(listener)
        val intent = Intent(builder.mContext, PermissionActivity::class.java)
        var param = Param(
            permissions, builder.showCustomDialog, type, builder.reminder,
            builder.title, builder.desc, builder.nextBtnText, builder.style
        )
        intent.putExtra(Constant.INTENT_PARAM, param)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        builder.mContext.startActivity(intent)
    }

    /**
     * 检测是否已经授权
     * @return true = 已授权
     */
    private fun checkPermission(context: Context, permission: Permission): Boolean {
        val checkPermission = ContextCompat.checkSelfPermission(context, permission.value)
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
            //是否重复提醒
            var reminder = false
                private set
            //是否显示自定义的对话框
            var showCustomDialog = false
                private set
            //自定义对话框标题
            var title: String? = null
                private set
            //自定义对话框提示内容
            var desc: String? = null
                private set
            //自定义对话框按钮
            var nextBtnText: String? = null
                private set
            //自定义对话框样式
            var style = 0
                private set

            lateinit var mContext: Context

            /**
             * 拒绝之后是否循环提醒
             */
            fun reminder(reminder: Boolean): Builder {
                this.reminder = reminder
                return this
            }

            /**
             * 是否显示自定义对话框
             */
            fun showCustomDialog(showCustomDialog: Boolean): Builder {
                this.showCustomDialog = showCustomDialog
                return this
            }

            /**
             * 自定义对话框标题
             * @param title
             */
            fun title(title: String?): Builder {
                this.title = title
                return this
            }

            /**
             *自定义对话框描述内容
             * @param desc
             */
            fun desc(desc: String?): Builder {
                this.desc = desc
                return this
            }

            /**
             * 自定义对话框按钮文案
             * @param nextBtnText
             */
            fun nextBtnText(nextBtnText: String?): Builder {
                this.nextBtnText = nextBtnText
                return this
            }

            /**
             * 自定义对话框样式
             * @param style
             */
            fun style(style: Int): Builder {
                this.style = style
                return this
            }

            fun build(mContext: Context): PermissionManager {
                this.mContext = mContext
                val permission = PermissionManager()
                permission.builder = this
                return permission
            }
        }
    }
}