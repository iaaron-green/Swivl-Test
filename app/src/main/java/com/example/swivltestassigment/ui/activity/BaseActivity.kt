package com.example.swivltestassigment.ui.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swivltestassigment.R

abstract class BaseActivity: AppCompatActivity() {
    private var dialogProgressBar: Dialog? = null
    fun showProgressBar() {
            if (dialogProgressBar == null) {
                dialogProgressBar = getDialogProgressBar()
            }
        dialogProgressBar?.show()
    }

    fun hideProgressBar() {
            if (dialogProgressBar != null && !isFinishing && !isDestroyed) {
                dialogProgressBar?.dismiss()
            }
            dialogProgressBar = null
    }

    open fun showToastShort(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    open fun showToastLong(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun getDialogProgressBar(): Dialog? {
        val dialogProgressBar = Dialog(this)
        dialogProgressBar.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogProgressBar.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogProgressBar.setContentView(R.layout.progress_view)
        dialogProgressBar.setCancelable(false)
        return dialogProgressBar
    }
}