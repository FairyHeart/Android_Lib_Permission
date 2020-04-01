package com.lib.permission.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.lib.permission.R
import com.lib.permission.adapter.PermissionAdapter
import com.lib.permission.bean.PermissionVo
import com.lib.permission.view.NestedGridView

/**
 * 提示对话框
 *
 * @author: Admin.
 * @date  : 2019-08-10.
 */
class AlertDialog : DialogFragment() {

    private var isShow = false

    private lateinit var confirmListener: OnConfirmListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.BaseDialogFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_alert, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initView(view)
    }

    @SuppressLint("ResourceType")
    private fun initView(view: View) {
        isCancelable = false
        val title = arguments?.getString(TITLE)
        val desc = arguments?.getString(DESC)
        val confirmBtnTxt = arguments?.getString(BTN_CONFIRM_TXT)
        val permissionVos =
            arguments?.getParcelableArrayList(PERMISSIONS) ?: mutableListOf<PermissionVo>()
        var styleId = arguments?.getInt(STYLE, 0) ?: 0

        val layout = view.findViewById<LinearLayout>(R.id.layout)
        val titleTv = view.findViewById<TextView>(R.id.tv_title)
        val descTv = view.findViewById<TextView>(R.id.tv_desc)
        val nextBtn = view.findViewById<Button>(R.id.btn_next)
        val gridView = view.findViewById<NestedGridView>(R.id.grid_view)

        if (!confirmBtnTxt.isNullOrBlank()) {
            nextBtn.text = confirmBtnTxt
        }
        if (styleId <= 0) {
            styleId = R.style.PermissionDefaultStyle
        }

        val ints = intArrayOf(
            R.attr.permissionBackground,
            R.attr.permissionTitleColor,
            R.attr.permissionDescColor,
            R.attr.permissionButtonTextColor,
            R.attr.permissionButtonBackground,
            R.attr.permissionItemTextColor,
            R.attr.permissionIconFilterColor
        )
        val theme = resources.newTheme()
        theme.applyStyle(styleId, true)

        val typedArray = theme.obtainStyledAttributes(ints)
        val background = typedArray.getDrawable(0)
        val titleColor = typedArray.getColor(1, 0)
        val msgColor = typedArray.getColor(2, 0)
        val btnTextColor = typedArray.getColor(3, 0)
        val btnBackground = typedArray.getDrawable(4)
        val itemTextColor = typedArray.getColor(5, 0)
        val iconFilterColor = typedArray.getColor(6, 0)
        if (titleColor != 0) {
            titleTv.setTextColor(titleColor)
        }
        if (msgColor != 0) {
            descTv.setTextColor(msgColor)
        }
        if (btnTextColor != 0) {
            nextBtn.setTextColor(btnTextColor)
        }
        if (background != null) {
            layout.background = background
        }
        if (btnBackground != null) {
            nextBtn.background = btnBackground
        }
        val mContext = context ?: return
        if (!title.isNullOrBlank()) {
            titleTv.text = title
        } else {
            titleTv.text = resources.getString(
                R.string.permission_dialog_title,
                mContext.applicationInfo.loadLabel(mContext.packageManager)
            )
        }
        if (!desc.isNullOrBlank()) {
            descTv.text = desc
        }
        gridView.numColumns = if (permissionVos.size < 3) {
            permissionVos.size
        } else {
            3
        }
        val adapter = PermissionAdapter(mContext)
        adapter.setFilterColor(iconFilterColor)
        adapter.setTextColor(itemTextColor)
        gridView.adapter = adapter
        adapter.list = permissionVos

        nextBtn.setOnClickListener {
            confirmListener.onConfirm()
            hide()
        }
    }

    fun show(context: FragmentActivity) {
        try {
            if (!isShow && !this.isAdded) {
                isShow = true
                show(context.supportFragmentManager, this.javaClass.simpleName)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            isShow = false
        }

    }

    private fun hide() {
        dismissAllowingStateLoss()
    }

    interface OnConfirmListener {
        fun onConfirm()
    }

    companion object {
        private const val TITLE = "title"
        private const val DESC = "desc"
        private const val BTN_CONFIRM_TXT = "btn_confirm_txt"
        private const val PERMISSIONS = "permissions"
        private const val STYLE = "style_id"

        fun builder(): FragmentBuilder =
            FragmentBuilder()

        class FragmentBuilder {

            private var args: Bundle = Bundle()

            fun build(listener: OnConfirmListener): AlertDialog {
                val fragment = AlertDialog()
                fragment.arguments = args
                fragment.confirmListener = listener
                return fragment
            }

            /**
             * 可选方法
             *
             * 标题
             *
             * @param title 默认值 = 开启${应用名}
             * @return
             */
            fun title(title: String?): FragmentBuilder {
                args.putString(TITLE, title)
                return this
            }

            /**
             * 提示内容
             *
             * @param desc 默认值 = 为了您能正常使用，需要开启一下权限
             * @return
             */
            fun desc(desc: String?): FragmentBuilder {
                args.putString(DESC, desc)
                return this
            }

            /**
             * 可选方法
             *
             * @param confirmBtnText 确认按钮文字 默认值为'确认'
             * @return
             */
            fun setNextBtnText(confirmBtnText: String?): FragmentBuilder {
                args.putString(BTN_CONFIRM_TXT, confirmBtnText)
                return this
            }

            /**
             * 具体权限
             * @param permissionVos
             * @return
             */
            fun setPermissionVos(permissionVos: MutableList<PermissionVo>): FragmentBuilder {
                args.putParcelableArrayList(PERMISSIONS, ArrayList(permissionVos))
                return this
            }

            /**
             * 自定义样式，默认为R.style.PermissionDefaultStyle
             */
            fun setStyle(styleId: Int = 0): FragmentBuilder {
                args.putInt(STYLE, styleId)
                return this
            }
        }
    }
}