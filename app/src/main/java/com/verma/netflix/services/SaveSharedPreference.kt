package com.verma.netflix.services

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


object SaveSharedPreference {
    const val PREF_USER_NAME = "username"
    fun getSharedPreferences(ctx: Context?): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun setUserName(ctx: Context?, userName: String?) {
        val editor: SharedPreferences.Editor = getSharedPreferences(ctx).edit()
        editor.putString(PREF_USER_NAME, userName)
        editor.commit()
    }

    fun getUserName(ctx: Context?): String? {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "")
    }
}