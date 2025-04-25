package com.vijayjoshi.lokal_assessment_jobs.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vijayjoshi.lokal_assessment_jobs.R
import com.vijayjoshi.lokal_assessment_jobs.model.JobBookmark
class JobSavedAdapter(val context: Context, var jobList: List<JobBookmark>) :
    RecyclerView.Adapter<JobSavedAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cardJob: CardView = view.findViewById(R.id.job_card)
        var title: TextView = view.findViewById(R.id.job_title)
        var location: TextView = view.findViewById(R.id.job_location)
        var phone: TextView = view.findViewById(R.id.job_phone)
        var salary: TextView = view.findViewById(R.id.job_salary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.job_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentJob = jobList[position]

        holder.title.text = currentJob.title
        holder.salary.text = currentJob.salary
        holder.location.text = currentJob.location
        holder.phone.text = currentJob.phone

        holder.cardJob.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("jobId", currentJob.id)
            holder.itemView.findNavController().navigate(R.id.action_bookMarkFragment_to_detailedFragment, bundle)
        }
    }

    fun updateData(newJobList: List<JobBookmark>) {
        jobList = newJobList
        notifyDataSetChanged()
    }

}