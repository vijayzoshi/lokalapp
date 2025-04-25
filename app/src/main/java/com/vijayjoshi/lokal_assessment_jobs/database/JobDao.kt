package com.vijayjoshi.lokal_assessment_jobs.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vijayjoshi.lokal_assessment_jobs.model.JobBookmark
@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(jobBookmark: JobBookmark)

    @Query("SELECT * FROM bookmarked_jobs")
    suspend fun getAllJobs(): List<JobBookmark>

    @Query("DELETE FROM bookmarked_jobs WHERE id = :jobId")
    suspend fun deleteJobById(jobId: Int)

    @Query("SELECT * FROM bookmarked_jobs WHERE id = :jobId")
    suspend fun getJobById(jobId: Int): JobBookmark?
}