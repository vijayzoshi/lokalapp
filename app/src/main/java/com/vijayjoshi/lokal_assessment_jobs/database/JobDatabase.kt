package com.vijayjoshi.lokal_assessment_jobs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vijayjoshi.lokal_assessment_jobs.model.JobBookmark


@Database(entities = [JobBookmark::class], version = 1, exportSchema = false)
abstract class JobDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao

    companion object {
        @Volatile
        private var INSTANCE: JobDatabase? = null

        fun getDatabase(context: Context): JobDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobDatabase::class.java,
                    "bookmarked_jobs"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}