package com.example.xtsfailurelogger.ui.addedit

import com.example.xtsfailurelogger.data.model.FailureStatus
import com.example.xtsfailurelogger.data.model.TestSuites

data class AddEditUiState(
    val testName: String = "",
    val suiteName: TestSuites = TestSuites.CTS,
    val failureReason: String = "",
    val status: FailureStatus = FailureStatus.OPEN,
    val notes: String = "",
    val deviceModel: String = "",
    val androidVersion: String = "",
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val testNameError: String? = null,
    val failureReasonError: String? = null
)