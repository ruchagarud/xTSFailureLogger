package com.example.xtsfailurelogger.ui.list

import androidx.room.Query
import com.example.xtsfailurelogger.data.model.FailureLogger
import com.example.xtsfailurelogger.data.model.FailureStatus

data class FailureLogUiState (
    val logs : List<FailureLogger> = emptyList(),
    val isLoading : Boolean  = true,
    val searchQuery: String = "",
    val selectedFilter : FailureStatus? = null,
    val totalCount : Int = 0,
    val openCount : Int =0,
    val fixedCount : Int =0
)