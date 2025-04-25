package com.vijayjoshi.lokal_assessment_jobs.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "bookmarked_jobs")
data class JobBookmark(
    @PrimaryKey val id: Int,
    val title: String,
    val companyName: String,
    val salary: String,
    val location: String,
    val phone: String,
    val experience: String,
    val expireOn: String,
    val jobRole: String,
    val qualification: String,
    val jobHours: String,
    val jobCategory: String,
    val imageUrl: String
) : Serializable

