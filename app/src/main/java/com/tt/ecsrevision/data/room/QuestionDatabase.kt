package com.tt.ecsrevision.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tt.ecsrevision.R

@Database(
    entities = [Question::class, Test::class, PassMark::class, TestTime::class],
    version = 1,
    exportSchema = false
)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun testDao(): TestDao

    abstract fun passMarkDao(): PassMarkDao
    abstract fun testTimeDao(): TestTimeDao

    companion object{
        @Volatile
        private var INSTANCE: QuestionDatabase? = null

        fun getDatabase(context: Context): QuestionDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionDatabase::class.java,
                    context.getString(R.string.questions_database)
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}