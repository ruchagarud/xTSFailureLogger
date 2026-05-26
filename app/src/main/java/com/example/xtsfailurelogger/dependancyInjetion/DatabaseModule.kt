package com.example.xtsfailurelogger.dependancyInjetion

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xtsfailurelogger.data.local.AppDatabase
import com.example.xtsfailurelogger.data.local.FailureLoggerDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "xTS_logger_db"
        ).build()
    }

    @Provides
    fun provideFailureLoggerDao(db : AppDatabase) : FailureLoggerDAO = db.failureLoggerDao()
}