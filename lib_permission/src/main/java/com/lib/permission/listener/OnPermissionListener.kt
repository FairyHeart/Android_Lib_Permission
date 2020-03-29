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
     * @param position 权限角标
     */
    fun onDenied(permission: String, position: Int)

    /**
     * 被授予
     * @param permission 权限
     * @param position 权限角标
     */
    fun onGranted(permission: String, position: Int)

    /**
     * 全部授权完成
     */
    fun onFinish()

    /**
     * 关闭授权
     */
    fun onClose()
}