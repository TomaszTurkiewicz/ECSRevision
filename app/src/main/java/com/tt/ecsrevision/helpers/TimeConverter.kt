package com.tt.ecsrevision.helpers

import android.content.Context
import com.tt.ecsrevision.R

class TimeConverter {

    companion object {
        fun getClock(int: Int, context: Context): String {
            val tenMinutes = int / 600
            val minutes = (int / 60) % 10
            val tenSeconds = (int % 60) / 10
            val seconds = (int % 60) % 10

            return context.getString(
                R.string.time,
                tenMinutes,
                minutes,
                tenSeconds,
                seconds
            )
        }
    }
}