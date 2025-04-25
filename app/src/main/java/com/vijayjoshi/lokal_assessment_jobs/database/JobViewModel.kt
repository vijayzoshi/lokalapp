package com.vijayjoshi.lokal_assessment_jobs.database
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vijayjoshi.lokal_assessment_jobs.model.JobBookmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JobViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JobRepository


    init {
        val jobDao = JobDatabase.getDatabase(application).jobDao()
        repository = JobRepository(jobDao)

    }

    fun insertJob(jobBookmark: JobBookmark) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertJob(jobBookmark)
        }
    }

     suspend fun getAllJobs(): List<JobBookmark>  {
         return withContext(Dispatchers.IO) {
             repository.getAllJobs()
         }
    }

    fun deleteJobById(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteJobById(id )
        }
    }

    suspend fun getJobById(jobId: Int): JobBookmark? {
        return withContext(Dispatchers.IO) {
            repository.getJobById(jobId)
        }
    }
}

