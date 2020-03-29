package com.lib.permission.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.lib.permission.Constant
import com.lib.permission.Param
import com.lib.permission.listener.OnPermissionListener

/**
 * 授权activity
 *
 * @author: 笨小孩.
 * @date  : 2020/3/29.
 */
internal class PermissionActivity : AppCompatActivity() {

    companion object {

        private var listener: OnPermissionListener? = null

        fun setPermissionListener(listener: OnPermissionListener) {
            this.listener = listener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val param = intent.getParcelableExtra<Param>(Constant.INTENT_PARAM)
        if (param != null) {
            Toast.makeText(this, "xxx", Toast.LENGTH_LONG).show()
            val permissions = param.permissions
            if (permissions != null) {
                val permissionStrs = mutableListOf<String>()
                permissions.forEach {
                    permissionStrs.add(it.permission)
                }
                this.requestPermission(permissionStrs, Constant.REQUEST_CODE_SINGLE)
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
            Constant.REQUEST_CODE_SINGLE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    listener?.onFinish()
                } else {
                    listener?.onDenied(permissions[0], 0)
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

}