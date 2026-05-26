package com.example.xtsfailurelogger.data.local

import androidx.room.TypeConverter
import com.example.xtsfailurelogger.data.model.FailureStatus
import com.example.xtsfailurelogger.data.model.TestSuites
class Converters {

    @TypeConverter
    fun fromTestSuite(value: TestSuites): String = value.name

    @TypeConverter
    fun toTestSuite(value: String): TestSuites = TestSuites.valueOf(value)

    @TypeConverter
    fun fromFailureStatus(value: FailureStatus): String = value.name

    @TypeConverter
    fun toFailureStatus(value: String): FailureStatus = FailureStatus.valueOf(value)
}