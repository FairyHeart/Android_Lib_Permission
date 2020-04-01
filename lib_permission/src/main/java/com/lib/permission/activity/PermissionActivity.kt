package com.lib.permission.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lib.permission.R
import com.lib.permission.bean.Param
import com.lib.permission.bean.PermissionVo
import com.lib.permission.constant.Constant
import com.lib.permission.dialog.AlertDialog
import com.lib.permission.listener.OnPermissionListener

/**
 * 授权activity
 *
 * @author: 笨小孩.
 * @date  : 2020/3/29.
 */
internal class PermissionActivity : AppCompatActivity() {

    private val permissionMap = mutableMapOf<String, PermissionVo>()

    private var reminder = false

    companion object {

        private var listener: OnPermissionListener? = null

        fun setPermissionListener(listener: OnPermissionListener) {
            this.listener = listener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val param = intent.getParcelableExtra<Param>(
            Constant.INTENT_PARAM
        )
        if (param != null) {
            this.reminder = param.reminder
            if (param.showCustomDialog) {
                AlertDialog.builder()
                    .title(param.titleText)
                    .desc(param.descText)
                    .setNextBtnText(param.nextBtnText)
                    .setStyle(param.style)
                    .setPermissionVos(param.permissions)
                    .build(object : AlertDialog.OnConfirmListener {
                        override fun onConfirm() {
                            startRequestPermission(param)
                        }
                    })
                    .show(this)
            } else {
                startRequestPermission(param)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //设置完成之后判断权限是否设置成功
        if (requestCode == Constant.REQUEST_SETTING) {
            var iterator = permissionMap.keys.iterator()
            if (iterator.hasNext()) {
                if (checkPermission(this, iterator.next())) {
                    iterator.remove()
                }
            }
            if (permissionMap.isEmpty()) {
                onFinish()
            } else {
                onClose()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constant.TYPE_SINGLE -> {
                //权限允许
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionMap.remove(permissions[0])
                    onFinish()
                } else {
                    listener?.onDenied(permissions[0], 0)
                }
                //用户拒绝了某个或多个权限，重新申请
                if (permissionMap.isNotEmpty()) {
                    this.reRequestPermission(permissionMap)
                }
            }
            Constant.TYPE_MUTI -> {
                grantResults.indices.forEach {
                    //权限允许
                    if (grantResults[it] == PackageManager.PERMISSION_GRANTED) {
                        listener?.onGranted(permissions[it], it)
                        permissionMap.remove(permissions[it])
                    } else {
                        listener?.onDenied(permissions[it], it)
                    }
                }

                //用户拒绝了某个或多个权限，重新申请
                if (permissionMap.isNotEmpty()) {
                    this.reRequestPermission(permissionMap)
                } else {
                    onFinish()
                }
            }
            Constant.TYPE_SINGLE_MORE -> {
                //重新申请后再次拒绝
                grantResults.indices.forEach {
                    //权限允许
                    if (grantResults[it] == PackageManager.PERMISSION_GRANTED) {
                        listener?.onGranted(permissions[it], it)
                        permissionMap.remove(permissions[it])
                    } else {
                        listener?.onDenied(permissions[it], it)
                    }
                }
                //重新申请后再次拒绝
                if (permissionMap.isNotEmpty()) {
                    if (reminder) {
                        this.reRequestPermission(permissionMap)
                    } else {
                        showRefundTip(permissionMap)
                    }
                } else {
                    onFinish()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
    }

    override fun onBackPressed() {
        finish()
    }

    /**
     * 开始获取权限
     */
    private fun startRequestPermission(param: Param) {
        permissionMap.clear()
        if (param.type == Constant.TYPE_SINGLE) {
            permissionMap[param.permissions[0].permission.value] = param.permissions[0]
            this.requestPermission(permissionMap.keys.toMutableList(), param.type)
        } else if (param.type == Constant.TYPE_MUTI) {
            val permissions = param.permissions
            permissions.forEach {
                permissionMap[it.permission.value] = it
            }
            this.requestPermission(permissionMap.keys.toMutableList(), param.type)
        }
    }

    /**
     * 重新提示获取权限
     */
    private fun reRequestPermission(permissionMap: MutableMap<String, PermissionVo>?) {
        if (permissionMap.isNullOrEmpty()) {
            return
        }
        var name = ""
        permissionMap.keys.forEach {
            name = if (name.isNullOrBlank()) {
                "${permissionMap[it]?.permissionName}"
            } else {
                "$name、${permissionMap[it]?.permissionName}"
            }
        }
        if (reminder) {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(this.getString(R.string.permission_title_tip, name))
                .setMessage(this.getString(R.string.permission_denied_tip, name))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.permission_btn_confirm)) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    this.requestPermission(
                        permissionMap.keys.toMutableList(),
                        Constant.TYPE_SINGLE_MORE
                    )
                }
                .show()
                .setCanceledOnTouchOutside(false)
        } else {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(this.getString(R.string.permission_title_tip, name))
                .setMessage(this.getString(R.string.permission_denied_tip, name))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.permission_btn_cancel)) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    onClose()
                }
                .setPositiveButton(getString(R.string.permission_btn_confirm)) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    this.requestPermission(
                        permissionMap.keys.toMutableList(),
                        Constant.TYPE_SINGLE_MORE
                    )
                }
                .show()
                .setCanceledOnTouchOutside(false)
        }
    }

    /**
     * 再次申请拒绝之后提示
     */
    private fun showRefundTip(permissionMap: MutableMap<String, PermissionVo>) {
        if (permissionMap.isNullOrEmpty()) {
            return
        }
        var name = ""
        permissionMap.keys.forEach {
            name = if (name.isNullOrBlank()) {
                "${permissionMap[it]?.permissionName}"
            } else {
                "$name、${permissionMap[it]?.permissionName}"
            }
        }
        val appName = applicationInfo.loadLabel(packageManager)
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle(this.getString(R.string.permission_title_tip, name))
            .setMessage(this.getString(R.string.permission_refund_tip, appName, name, appName))
            .setCancelable(true)
            .setNegativeButton(getText(R.string.permission_btn_cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
                onClose()
            }
            .setPositiveButton(getString(R.string.permission_go_to_setting)) { dialogInterface, _ ->
                dialogInterface.dismiss()
                try {
                    val packageURI = Uri.parse("package:$packageName")
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI)
                    startActivityForResult(intent, Constant.REQUEST_SETTING)
                } catch (e: Exception) {
                    e.printStackTrace()
                    onClose()
                }
            }
            .setCancelable(false)
            .show()
            .setCanceledOnTouchOutside(false)
    }

    private fun onClose() {
        listener?.onClose()
        finish()
    }

    private fun onFinish() {
        listener?.onFinish()
        finish()
    }

    /**
     * 申请权限
     */
    private fun requestPermission(
        permissions: MutableList<String>,
        requestCode: Int
    ) {
        ActivityCompat.requestPermissions(
            this@PermissionActivity,
            permissions.toTypedArray(),
            requestCode
        )
    }

    /**
     * 检测是否已经授权
     * @return true = 已授权
     */
    private fun checkPermission(context: Context, permission: String): Boolean {
        val checkPermission = ContextCompat.checkSelfPermission(context, permission)
        return checkPermission == PackageManager.PERMISSION_GRANTED
    }

}