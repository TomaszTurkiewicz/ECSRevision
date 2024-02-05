package com.tt.ecsrevision.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TestTime (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val testTime:Int = 0
)