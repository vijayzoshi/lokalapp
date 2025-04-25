package com.vijayjoshi.lokal_assessment_jobs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vijayjoshi.lokal_assessment_jobs.R
import com.vijayjoshi.lokal_assessment_jobs.database.JobViewModel
import com.vijayjoshi.lokal_assessment_jobs.databinding.FragmentDetailedBinding
import com.vijayjoshi.lokal_assessment_jobs.model.JobBookmark
import com.vijayjoshi.lokal_assessment_jobs.model.Result
import com.vijayjoshi.lokal_assessment_jobs.utilites.Utilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailedFragment : Fragment() {

    private lateinit var binding: FragmentDetailedBinding
    private lateinit var viewModel: JobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(JobViewModel::class.java)

        val jobId = arguments?.getInt("jobId")



            fetchData(jobId)



        return binding.root
    }




    private fun fetchData(jobId: Int?) {
        lifecycleScope.launch(Dispatchers.IO) {
            val response = Utilities.getApiData()
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()?.results
                val jobDetail = data?.find { it.id == jobId }
                withContext(Dispatchers.Main) {
                    if (jobDetail != null) {
                        checkJobSaved(jobDetail)
                    } else {
                        Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private suspend fun checkJobSaved(jobDetail: Result) {
        val isSaved = withContext(Dispatchers.IO) {
            viewModel.getJobById(jobDetail.id) != null
        }
        updateUI(jobDetail, isSaved)
    }

    private fun updateUI(jobDetail: Result, isSaved: Boolean) {
        binding.jobDetailedTitle.text = "Title : ${jobDetail.title}"
        binding.jobDetailedCompany.text = "Company : ${jobDetail.company_name}"
        binding.jobDetailedSalary.text = "Salary : ${jobDetail.primary_details?.Salary}"
        binding.jobDetailedPlace.text = "Location : ${jobDetail.primary_details?.Place}"
        binding.jobDetailedPhoneno.text = "Phone : ${jobDetail.custom_link?.substring(4)}"
        binding.jobDetailedExperience.text = "Experience : ${jobDetail.experience}"
        binding.jobDetailedExpiry.text = "Expire on : ${jobDetail.expire_on.substring(0, 10)}"
        binding.jobDetailedRole.text = "Job Role : ${jobDetail.job_role}"
        binding.jobDetailedQualification.text = "Qualification : ${jobDetail.primary_details.Qualification}"
        binding.jobDetailedJobHour.text = "Job hours : ${jobDetail.job_hours}"
        binding.jobDetailedCategory.text = "Job Category : ${jobDetail.job_category}"
        Utilities.loadImage(requireContext(), binding.jobPicture, jobDetail.creatives[0].file)

        if (isSaved) {
            binding.jobItemSaved.setImageResource(R.drawable.ic_star)
            binding.jobItemSaved.tag = true
        } else {
            binding.jobItemSaved.setImageResource(R.drawable.ic_star_outline)
            binding.jobItemSaved.tag = false
        }

        binding.jobItemSaved.setOnClickListener {
            val isCurrentlySaved = binding.jobItemSaved.tag as? Boolean ?: false
            if (isCurrentlySaved) {
                deleteJob(jobDetail.id)
                binding.jobItemSaved.setImageResource(R.drawable.ic_star_outline)
                binding.jobItemSaved.tag = false
            } else {
                insertJob(jobDetail)
                binding.jobItemSaved.setImageResource(R.drawable.ic_star)
                binding.jobItemSaved.tag = true
            }
        }
    }

    private fun insertJob(jobDetail: Result) {
        val job = JobBookmark(
            id = jobDetail.id,
            title = jobDetail.title ?: "",
            companyName = jobDetail.company_name ?: "",
            salary = jobDetail.primary_details?.Salary ?: "",
            location = jobDetail.primary_details?.Place ?: "",
            phone = jobDetail.custom_link?.substring(4) ?: "",
            experience = jobDetail.experience.toString(),
            expireOn = jobDetail.expire_on.substring(0, 10),
            jobRole = jobDetail.job_role ?: "",
            qualification = jobDetail.primary_details?.Qualification ?: "",
            jobHours = jobDetail.job_category ?: "",
            jobCategory = jobDetail.job_hours ?: "",
            imageUrl = jobDetail.creatives[0].file
        )

        viewModel.insertJob(job)
    }

    private fun deleteJob(jobId: Int) {
        viewModel.deleteJobById(jobId)
    }
}
