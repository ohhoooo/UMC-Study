package com.kjh.umc_project8.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    val content: String,
    val bookmark: Boolean = false,
    val like: Boolean = false,
    @PrimaryKey(autoGenerate = true) val memoId: Int = 0
)