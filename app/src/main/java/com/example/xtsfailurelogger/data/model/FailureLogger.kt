package com.example.xtsfailurelogger.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TestSuites{
    CTS, GSI, ATS, STS, ATS_I, BFG, HSAPI, BTS
}

enum class FailureStatus{
    OPEN, FIXED, WONT_FIX, IN_PROGRESS
}

@Entity(tableName = "failure_log")
data class FailureLogger (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val testSuites: TestSuites,
    val testcase : String,
    val failureMsg : String,
    val status : FailureStatus,
    val note : String,
    val android: String = "Android 14",
    val timestamp: Long = System.currentTimeMillis()
)

/*
* @PrimaryKey(autoGenerate = true) with id = 0 as default — Room treats 0 as "no ID yet,
* generate one". So when you create a new FailureLog, you just don't pass an id and Room handles it.
* */