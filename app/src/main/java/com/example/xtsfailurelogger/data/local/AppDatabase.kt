package com.example.xtsfailurelogger.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.xtsfailurelogger.data.model.FailureLogger

@Database(
    entities = [FailureLogger::class],
    version = 1,
    exportSchema = false
)
    @TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun failureLoggerDao() : FailureLoggerDAO
}