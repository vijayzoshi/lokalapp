package com.vijayjoshi.lokal_assessment_jobs.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vijayjoshi.lokal_assessment_jobs.adapter.JobSavedAdapter
import com.vijayjoshi.lokal_assessment_jobs.database.JobViewModel
import com.vijayjoshi.lokal_assessment_jobs.databinding.FragmentBookMarkBinding
import com.vijayjoshi.lokal_assessment_jobs.model.JobBookmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookMarkFragment : Fragment() {

    private lateinit var binding: FragmentBookMarkBinding
    private lateinit var viewModel: JobViewModel
    private lateinit var adapter: JobSavedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookMarkBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(JobViewModel::class.java)

        adapter = JobSavedAdapter(requireContext(), listOf())
        binding.jobRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.jobRecyclerView.adapter = adapter

        getDataFromDatabase()

        return binding.root
    }

    private fun getDataFromDatabase() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val jobList = viewModel.getAllJobs()
            withContext(Dispatchers.Main) {
                adapter.updateData(jobList)
            }
        }
    }

}