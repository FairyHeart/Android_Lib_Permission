package com.lib.permission

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lib.permission.bean.PermissionVo
import com.lib.permission.listener.OnPermissionListener
import com.lib.permission.test.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_single.setOnClickListener {
            PermissionManager.builder()
                .showCustomDialog(true)
                .reminder(false)
                .title(getString(R.string.permission_dialog_title,applicationInfo.loadLabel(packageManager)))
                .desc(getString(R.string.permission_dialog_msg))
                .nextBtnText(getString(R.string.permission_btn_next))
                .style(R.style.PermissionDefaultStyle)
                .build(this)
                .checkSinglePermission(
                    Permission.WRITE_EXTERNAL_STORAGE,
                    object : OnPermissionListener {
                        /**
                         * 被拒绝
                         * @param permission 权限
                         */
                        override fun onDenied(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onDenied $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 被授予
                         * @param permission 权限
                         */
                        override fun onGranted(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onGranted $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 全部授权完成
                         */
                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_SHORT).show()
                        }

                        /**
                         * 关闭授权
                         */
                        override fun onClose() {
                            Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
                        }

                    })

        }
        btn_single_dialog.setOnClickListener {
            PermissionManager.builder()
                .showCustomDialog(true)
                .build(this)
                .checkSinglePermission(
                    Permission.ACCESS_COARSE_LOCATION,
                    object : OnPermissionListener {
                        /**
                         * 被拒绝
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onDenied(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onDenied $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 被授予
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onGranted(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onGranted $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 全部授权完成
                         */
                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_SHORT).show()
                        }

                        /**
                         * 关闭授权
                         */
                        override fun onClose() {
                            Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
                        }

                    })

        }
        btn_mult.setOnClickListener {
            PermissionManager.builder()
                .showCustomDialog(true)
                .build(this)
                .checkMultiplePermission(mutableListOf(
                    Permission.CAMERA,
                    Permission.CALL_PHONE,
                    Permission.WRITE_EXTERNAL_STORAGE,
                    Permission.ACTIVITY_RECOGNITION
                ), object : OnPermissionListener {
                    /**
                     * 被拒绝
                     * @param permission 权限
                     * @param position 权限角标
                     */
                    override fun onDenied(permission: String) {
                        Toast.makeText(
                            this@MainActivity,
                            "onDenied $permission ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    /**
                     * 被授予
                     * @param permission 权限
                     * @param position 权限角标
                     */
                    override fun onGranted(permission: String) {
                        Toast.makeText(
                            this@MainActivity,
                            "onGranted $permission ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    /**
                     * 全部授权完成
                     */
                    override fun onFinish() {
                        Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_SHORT).show()
                    }

                    /**
                     * 关闭授权
                     */
                    override fun onClose() {
                        Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
                    }

                })
        }
        btn_style1.setOnClickListener {
            PermissionManager.builder()
                .showCustomDialog(true)
                .style(com.lib.permission.R.style.PermissionBlueStyle)
                .build(this)
                .checkSinglePermission(
                    Permission.RECORD_AUDIO,
                    object : OnPermissionListener {
                        /**
                         * 被拒绝
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onDenied(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onDenied $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 被授予
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onGranted(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onGranted $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 全部授权完成
                         */
                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_SHORT).show()
                        }

                        /**
                         * 关闭授权
                         */
                        override fun onClose() {
                            Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
                        }

                    })
        }
        btn_style2.setOnClickListener {
            PermissionManager.builder()
                .showCustomDialog(true)
                .style(com.lib.permission.R.style.PermissionGreenLightStyle)
                .build(this)
                .checkSinglePermission(
                    Permission.RECORD_AUDIO,
                    object : OnPermissionListener {
                        /**
                         * 被拒绝
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onDenied(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onDenied $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 被授予
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onGranted(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onGranted $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 全部授权完成
                         */
                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_SHORT).show()
                        }

                        /**
                         * 关闭授权
                         */
                        override fun onClose() {
                            Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
                        }

                    })
        }
        btn_style3.setOnClickListener {
            PermissionManager.builder()
                .showCustomDialog(true)
                .style(com.lib.permission.R.style.PermissionGreenStyle)
                .build(this)
                .checkSinglePermission(
                    Permission.RECORD_AUDIO,
                    object : OnPermissionListener {
                        /**
                         * 被拒绝
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onDenied(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onDenied $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 被授予
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onGranted(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onGranted $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 全部授权完成
                         */
                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_SHORT).show()
                        }

                        /**
                         * 关闭授权
                         */
                        override fun onClose() {
                            Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
                        }

                    })
        }
        btn_customer.setOnClickListener {
            var permissions = mutableListOf<PermissionVo>()
            permissions.add(
                PermissionVo(
                    com.lib.permission.Permission.BODY_SENSORS,
                    "自定义名字1",
                    R.mipmap.ic_launcher_round
                )
            )
            permissions.add(
                PermissionVo(
                    Permission.READ_CALL_LOG,
                    "自定义名字2",
                    R.mipmap.ic_launcher_round
                )
            )
            PermissionManager.builder()
                .showCustomDialog(true)
                .style(com.lib.permission.test.R.style.PermissionCustomerStyle)
                .title("自定义标题")
                .desc("自定义描述")
                .nextBtnText("自定义文案")
                .build(this)
                .checkPermissions(permissions,
                    object : OnPermissionListener {
                        /**
                         * 被拒绝
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onDenied(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onDenied $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 被授予
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onGranted(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onGranted $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 全部授权完成
                         */
                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_SHORT).show()
                        }

                        /**
                         * 关闭授权
                         */
                        override fun onClose() {
                            Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
                        }

                    })

        }
        btn_reminder.setOnClickListener {
            PermissionManager.builder()
                .showCustomDialog(true)
                .reminder(true)
                .build(this)
                .checkSinglePermission(
                    Permission.RECORD_AUDIO,
                    object : OnPermissionListener {
                        /**
                         * 被拒绝
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onDenied(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onDenied $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 被授予
                         * @param permission 权限
                         * @param position 权限角标
                         */
                        override fun onGranted(permission: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "onGranted $permission ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        /**
                         * 全部授权完成
                         */
                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "onFinish", Toast.LENGTH_SHORT).show()
                        }

                        /**
                         * 关闭授权
                         */
                        override fun onClose() {
                            Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
                        }

                    })
        }


    }
}
