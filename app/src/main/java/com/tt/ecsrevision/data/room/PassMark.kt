package com.tt.ecsrevision.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PassMark(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val passMark:Int = 0
)