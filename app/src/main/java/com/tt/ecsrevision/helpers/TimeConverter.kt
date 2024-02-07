package com.tt.ecsrevision.helpers

import android.content.Context
import androidx.annotation.StringRes
import com.tt.ecsrevision.R

class TimeConverter {

    companion object {
//        fun getClock(int: Int, context: Context): String {
//            val tenMinutes = int / 600
//            val minutes = (int / 60) % 10
//            val tenSeconds = (int % 60) / 10
//            val seconds = (int % 60) % 10
//
//            return context.getString(
//                R.string.time,
//                tenMinutes,
//                minutes,
//                tenSeconds,
//                seconds
//            )
//        }

        fun getTimeWrappedInString(context: Context,@StringRes string:Int, time:Int):String {
            val tenMinutes = time / 600
            val minutes = (time / 60) % 10
            val tenSeconds = (time % 60) / 10
            val seconds = (time % 60) % 10

            return context.getString(
                string,
                tenMinutes,
                minutes,
                tenSeconds,
                seconds
            )
        }
//
//        fun getUserTestTime(int:Int, context: Context):String{
//            val tenMinutes = int / 600
//            val minutes = (int / 60) % 10
//            val tenSeconds = (int % 60) / 10
//            val seconds = (int % 60) % 10
//
//            return context.getString(
//                R.string.user_test_time,
//                tenMinutes,
//                minutes,
//                tenSeconds,
//                seconds
//            )
//        }
    }
}