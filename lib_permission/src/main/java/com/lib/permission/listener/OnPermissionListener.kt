package com.lib.permission.listener

/**
 * 权限操作回调
 *
 * @author: 笨小孩.
 * @date  : 2020/3/29.
 */
interface OnPermissionListener {

    /**
     * 被拒绝
     * @param permission 权限
     */
    fun onDenied(permission: String)

    /**
     * 被授予
     * @param permission 权限
     */
    fun onGranted(permission: String)

    /**
     * 全部授权完成
     */
    fun onFinish()

    /**
     * 关闭授权
     */
    fun onClose()
}