package com.tt.ecsrevision

import android.app.Application
import com.tt.ecsrevision.data.room.QuestionDatabase

class ECSApplication : Application() {
    val database: QuestionDatabase by lazy { QuestionDatabase.getDatabase(this) }
}