package com.lib.permission.utils

import android.content.Context
import android.os.Build
import com.lib.permission.Permission
import com.lib.permission.R

/**
 * 获取权限对应的名字
 *
 * @author: GuaZi.
 * @date  : 2020-03-31.
 */
object TransformUtils {

    /**
     * 获取单个权限的名字
     *
     * @param context
     * @param permission 权限
     *
     * @return 权限名字
     */
    fun transformText(context: Context, permission: Permission): String {
        return getPermissionTextName(
            permission,
            context
        )
    }

    /**
     * 获取多个权限的名字
     *
     * @param context
     * @param permissions 权限集合
     *
     * @return 权限名字集合
     */
    fun transformTexts(context: Context, permissions: MutableList<Permission>?): MutableList<String> {
        return transformText(
            context,
            permissions
        )
    }

    /**
     * 获取单个权限组的名字
     *
     * @param context
     * @param groupPermission 权限组
     *
     * @return 权限名字
     */
    fun transformTextGroup(context: Context, groupPermission: Permission): String {
        return getPermissionTextName(
            groupPermission,
            context
        )
    }

    /**
     * 获取单个权限的名字
     *
     * @param context
     * @param groupPermissions 权限组集合
     *
     * @return 权限名字集合
     */
    fun transformTextGroups(
        context: Context,
        groupPermissions: MutableList<Permission>?
    ): MutableList<String> {
        return transformText(
            context,
            groupPermissions
        )
    }

    /**
     * 获取单个权限图片
     *
     * @param context
     * @param permission 权限
     *
     * @return 权限图片
     */
    fun transformIcon(context: Context, permission: Permission): Int {
        return getPermissionIcon(
            permission,
            context
        )
    }

    /**
     * 获取多个权限的图片
     *
     * @param context
     * @param permissions 权限集合
     *
     * @return 权限图片集合
     */
    fun transformIcons(context: Context, permissions: MutableList<Permission>?): MutableList<Int> {
        return transformIcon(
            context,
            permissions
        )
    }

    /**
     * 获取单个权限组的图片
     *
     * @param context
     * @param groupPermission 权限组
     *
     * @return 权限图片
     */
    fun transformIconGroup(context: Context, groupPermission: Permission): Int {
        return getPermissionIcon(
            groupPermission,
            context
        )
    }

    /**
     * 获取单个权限的图片
     *
     * @param context
     * @param groupPermissions 权限组集合
     *
     * @return 权限图片集合
     */
    fun transformIconGroups(
        context: Context,
        groupPermissions: MutableList<Permission>?
    ): MutableList<Int> {
        return transformIcon(
            context,
            groupPermissions
        )
    }

    private fun transformText(
        context: Context,
        permissions: MutableList<Permission>?
    ): MutableList<String> {
        if (permissions.isNullOrEmpty()) {
            return mutableListOf()
        }
        val textList = mutableListOf<String>()
        for (permission in permissions) {
            val text =
                getPermissionTextName(
                    permission,
                    context
                )
            if (!text.isNullOrBlank() && !textList.contains(text)) {
                textList.add(text)
            }
        }
        return textList
    }

    private fun transformIcon(
        context: Context,
        permissions: MutableList<Permission>?
    ): MutableList<Int> {
        if (permissions.isNullOrEmpty()) {
            return mutableListOf()
        }
        val textList = mutableListOf<Int>()
        for (permission in permissions) {
            val icon = getPermissionIcon(
                permission,
                context
            )
            if (icon != 0 && !textList.contains(icon)) {
                textList.add(icon)
            }
        }
        return textList
    }

    private fun getPermissionTextName(
        permission: Permission,
        context: Context
    ): String {
        return when (permission) {
            Permission.READ_CALENDAR,
            Permission.WRITE_CALENDAR,
            Permission.CALENDAR_GROUP -> {
                context.getString(R.string.permission_name_calendar)
            }
            Permission.CAMERA,
            Permission.CAMERA_GROUP -> {
                context.getString(R.string.permission_name_camera)
            }
            Permission.GET_ACCOUNTS,
            Permission.READ_CONTACTS,
            Permission.WRITE_CONTACTS,
            Permission.CONTACTS_GROUP -> {
                context.getString(R.string.permission_name_contacts)

            }
            Permission.ACCESS_FINE_LOCATION,
            Permission.ACCESS_COARSE_LOCATION,
            Permission.LOCATION_GROUP -> {
                context.getString(R.string.permission_name_location)

            }
            Permission.RECORD_AUDIO,
            Permission.MICROPHONE_GROUP -> {
                context.getString(R.string.permission_name_microphone)
            }
            Permission.READ_PHONE_STATE,
            Permission.CALL_PHONE,
            Permission.ADD_VOICEMAIL,
            Permission.USE_SIP,
            Permission.READ_PHONE_NUMBERS,
            Permission.ANSWER_PHONE_CALLS,
            Permission.PHONE_GROUP -> {
                context.getString(R.string.permission_name_phone)
            }
            Permission.READ_CALL_LOG,
            Permission.WRITE_CALL_LOG,
            Permission.PROCESS_OUTGOING_CALLS,
            Permission.CALL_LOG_GROUP -> {
                val messageId: Int =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        R.string.permission_name_call_log
                    } else {
                        R.string.permission_name_phone
                    }
                context.getString(messageId)
            }
            Permission.BODY_SENSORS,
            Permission.SENSORS_GROUP -> {
                context.getString(R.string.permission_name_sensors)
            }
            Permission.ACTIVITY_RECOGNITION,
            Permission.ACTIVITY_RECOGNITION_GROUP -> {
                context.getString(R.string.permission_name_activity_recognition)
            }
            Permission.SEND_SMS,
            Permission.RECEIVE_SMS,
            Permission.READ_SMS,
            Permission.RECEIVE_WAP_PUSH,
            Permission.RECEIVE_MMS,
            Permission.SMS_GROUP -> {
                context.getString(R.string.permission_name_sms)
            }
            Permission.READ_EXTERNAL_STORAGE,
            Permission.WRITE_EXTERNAL_STORAGE,
            Permission.STORAGE_GROUP -> {
                context.getString(R.string.permission_name_storage)
            }
            else -> ""
        }
    }

    private fun getPermissionIcon(
        permission: Permission,
        context: Context
    ): Int {
        return when (permission) {
            Permission.READ_CALENDAR,
            Permission.WRITE_CALENDAR,
            Permission.CALENDAR_GROUP -> {
                R.drawable.permission_ic_calendar
            }
            Permission.CAMERA,
            Permission.CAMERA_GROUP -> {
                R.drawable.permission_ic_camera
            }
            Permission.GET_ACCOUNTS,
            Permission.READ_CONTACTS,
            Permission.WRITE_CONTACTS,
            Permission.CONTACTS_GROUP -> {
                R.drawable.permission_ic_contacts
            }
            Permission.ACCESS_FINE_LOCATION,
            Permission.ACCESS_COARSE_LOCATION,
            Permission.LOCATION_GROUP -> {
                R.drawable.permission_ic_location

            }
            Permission.RECORD_AUDIO,
            Permission.MICROPHONE_GROUP -> {
                R.drawable.permission_ic_micro_phone
            }
            Permission.READ_PHONE_STATE,
            Permission.CALL_PHONE,
            Permission.ADD_VOICEMAIL,
            Permission.USE_SIP,
            Permission.READ_PHONE_NUMBERS,
            Permission.ANSWER_PHONE_CALLS,
            Permission.PHONE_GROUP -> {
                R.drawable.permission_ic_phone
            }
            Permission.READ_CALL_LOG,
            Permission.WRITE_CALL_LOG,
            Permission.PROCESS_OUTGOING_CALLS,
            Permission.CALL_LOG_GROUP -> {
                val icon: Int =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        R.drawable.permission_ic_phone
                    } else {
                        R.drawable.permission_ic_phone
                    }
                icon
            }
            Permission.BODY_SENSORS,
            Permission.SENSORS_GROUP -> {
                R.drawable.permission_ic_sensors
            }
            Permission.ACTIVITY_RECOGNITION,
            Permission.ACTIVITY_RECOGNITION_GROUP -> {//图片到时候替换
                R.drawable.permission_ic_run
            }
            Permission.SEND_SMS,
            Permission.RECEIVE_SMS,
            Permission.READ_SMS,
            Permission.RECEIVE_WAP_PUSH,
            Permission.RECEIVE_MMS,
            Permission.SMS_GROUP -> {
                context.getString(R.string.permission_name_sms)
                R.drawable.permission_ic_sms
            }
            Permission.READ_EXTERNAL_STORAGE,
            Permission.WRITE_EXTERNAL_STORAGE,
            Permission.STORAGE_GROUP -> {
                context.getString(R.string.permission_name_storage)
                R.drawable.permission_ic_storage
            }
            else -> 0
        }
    }
}