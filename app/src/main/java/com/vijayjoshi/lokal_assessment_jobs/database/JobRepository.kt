package com.vijayjoshi.lokal_assessment_jobs.database

import androidx.lifecycle.LiveData
import com.vijayjoshi.lokal_assessment_jobs.model.JobBookmark
class JobRepository(private val jobDao: JobDao) {

    suspend fun insertJob(jobBookmark: JobBookmark) {
        jobDao.insertJob(jobBookmark)
    }

    suspend fun getAllJobs(): List<JobBookmark> {
        return jobDao.getAllJobs()
    }

    suspend fun deleteJobById(id : Int) {
        jobDao.deleteJobById(id)
    }

    suspend fun getJobById(id : Int) : JobBookmark?{
       return jobDao.getJobById(id)
    }
}