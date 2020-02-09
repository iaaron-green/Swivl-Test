package com.example.swivltestassigment.ui.fragment

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.swivltestassigment.ui.activity.BaseActivity

abstract class BaseFragment: Fragment() {
    open fun getBaseActivity(): BaseActivity? {
        return activity as BaseActivity?
    }

    open fun showProgressBar() {
            getBaseActivity()?.showProgressBar()
    }

    open fun hideProgressBar() {
            getBaseActivity()?.hideProgressBar()
    }

    open fun showToastLong(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            getBaseActivity()?.showToastLong(message)
        }
    }

    open fun showToastShort(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            getBaseActivity()?.showToastShort(message)
        }
    }
}