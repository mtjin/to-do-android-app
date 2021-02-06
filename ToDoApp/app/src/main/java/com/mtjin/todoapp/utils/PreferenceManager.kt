package com.mtjin.todoapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(TO_DO_APP, Context.MODE_PRIVATE)

    var recyclerLayoutManagerType: Int
        get() = sharedPref.getInt(LAYOUT_MANAGER_TYPE, 0)
        set(value) {
            val editor = sharedPref.edit()
            editor.putInt(LAYOUT_MANAGER_TYPE, value)
            editor.commit()
        }


    companion object {
        private const val TO_DO_APP = "TO_DO_APP"
        private const val LAYOUT_MANAGER_TYPE = "LAYOUT_MANAGER_TYPE"

    }
}