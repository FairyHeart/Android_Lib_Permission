package com.lib.permission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.lib.permission.listener.OnPermissionListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Permission.builder()
            .build(this)
            .checkSinglePermission(Manifest.permission.CAMERA, object : OnPermissionListener {
                /**
                 * 被拒绝
                 * @param permission 权限
                 * @param position 权限角标
                 */
                override fun onDenied(permission: String, position: Int) {
                    Toast.makeText(this@MainActivity, "onDenied", Toast.LENGTH_LONG).show()
                }

                /**
                 * 被授予
                 * @param permission 权限
                 * @param position 权限角标
                 */
                override fun onGranted(permission: String, position: Int) {
                    Toast.makeText(this@MainActivity, "onGranted", Toast.LENGTH_LONG).show()
                }

                /**
                 * 全部授权完成
                 */
                override fun onFinish() {
                    Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_LONG).show()
                }

                /**
                 * 关闭授权
                 */
                override fun onClose() {
                    Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_LONG).show()
                }

            })
    }
}
