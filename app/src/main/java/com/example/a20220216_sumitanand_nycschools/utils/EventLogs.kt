package com.example.a20220216_sumitanand_nycschools.utils

import android.util.Log

class EventLogs {
    companion object {
        fun setLogCat(tag: String, msg: String?) {
            Log.i("$tag :", msg!!)
        }
    }
}