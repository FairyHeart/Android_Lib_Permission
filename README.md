# LibPermission 

[![](https://jitpack.io/v/FairyHeart/LibPermission.svg)](https://jitpack.io/#FairyHeart/LibPermission)

<video id="video" controls="" preload="none" poster="https://github.com/FairyHeart/LibPermission/blob/master/image/2020_04_01_07_46_05.png">
      <source id="mp4" src="https://github.com/FairyHeart/LibPermission/blob/master/image/2020_04_01_11_41_17.mp4" type="video/mp4">
      </video>

 Android6.0之后，部分权限需要动态申请，每个应用的开发都必须加各种判断进行权限的申请，所以参考网上demo简单封装了一下，方便以后快速的开发；<br />
<br /> **效果：**<br />
<br />**功能描述：**

- 支持单个或者多个权限申请
- 支持单个或者多个权限组申请
- 权限申请前，支持自定义提醒对话框对申请进行说明
- 第一次申请拒绝之后，提示用户是否继续进行申请操作
- 再次申请被拒绝之后，继续提醒用户去设置中进行权限设置
- 循环提醒用户申请权限（某些场景一定要申请权限才能进行操作）


<br />**使用：**

1. [**架包引入**](https://jitpack.io/#FairyHeart/LibPermission)



2. **引入代码**
```kotlin
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
```

<br />说明：

- 方法说明

reminder ：是否重复提醒<br />showCustomDialog ：是否显示自定义的对话框<br />title ：自定义对话框标题（showCustomDialog=true生效）<br />desc ：自定义对话框提示内容（showCustomDialog=true生效）<br />nextBtnText ：自定义对话框按钮（showCustomDialog=true生效）<br />style ：自定义对话框样式（showCustomDialog=true生效）<br />checkSinglePermission ：_单个权限授权，默认使用自带的图片和文案_<br />checkMultiplePermission ：_多个权限一起授权，默认使用自带的图片和文案_<br />checkPermission ：_单个权限授权，自定义图片和文案_<br />checkPermissions ：_多个权限一起授权，自定义图片和文案_

- 接口说明

OnPermissionListener授权回调<br />onDenied(permission) : _被拒绝_<br />onGranted(permission) :_被授予_<br />onFinish() :_全部授权完成_<br />onClose() :_关闭授权_<br />_

- _类说明_

 Permission 定义了所有的权限和权限组<br />

3. **自定义对话框**

用户可以使用上面提供的方法来自定义对话框，也可以使用系统提供的四种默认样式的对话框<br />自定义属性说明：
```kotlin
<resources>
    <attr name="permissionBackground" format="reference"/> 对话框背景
    <attr name="permissionTitleColor" format="color"/> 对话框标题颜色
    <attr name="permissionDescColor" format="color" /> 对话框描述文案颜色
    <attr name="permissionItemTextColor" format="color"/> 对话框中权限的字体颜色
    <attr name="permissionButtonTextColor" format="color"/> 对话框中按钮颜色
    <attr name="permissionButtonBackground" format="reference"/> 对话框中按钮背景
    <attr name="permissionIconFilterColor" format="color"/> 对话框中权限的logo背景颜色
</resources>
```

<br />四种默认样式：<br />

R.style.PermissionDefaultStyle | R.style.PermissionBlueStyle | R.style.PermissionGreenLightStyle | R.style.PermissionGreenStyle
-|-|-|-
<img src="https://github.com/FairyHeart/LibPermission/blob/master/image/2020_04_01_11_44_49.png" width = "320" height = "560" alt="demo picture" align=center> | <img src="https://github.com/FairyHeart/LibPermission/blob/master/image/2020_04_01_07_47_26.png" width = "320" height = "560" alt="demo picture" align=center> | <img src="https://github.com/FairyHeart/LibPermission/blob/master/image/2020_04_01_07_46_05.png" width = "320" height = "560" alt="demo picture" align=center> | <img src="https://github.com/FairyHeart/LibPermission/blob/master/image/2020_04_01_07_46_14.png" width = "320" height = "560" alt="demo picture" align=center>

自定义样式<br />
<img src="https://github.com/FairyHeart/LibPermission/blob/master/image/2020_04_01_07_46_27.png" width = "320" height = "560" alt="demo picture" align=center> 

4. <br />
