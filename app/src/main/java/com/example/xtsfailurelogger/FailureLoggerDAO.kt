package com.example.xtsfailurelogger

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FailureLoggerDAO   {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log : FailureLogger)

    @Update
    suspend fun updateLog(log : FailureLogger)

    @Delete
    suspend fun deleteLog(log: FailureLogger)

    @Query("DELETE FROM failure_log WHERE id= :id")
    suspend fun deleteByID(id: Int)

    @Query("SELECT * FROM failure_log ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<FailureLogger>>

    @Query("SELECT * FROM failure_log WHERE id=:id")
    suspend fun getLogById(id:Int)

    @Query("SELECT * FROM failure_log WHERE status=:status ORDER BY timestamp DESC")
    fun getLogByStatus(status:FailureStatus): Flow<List<FailureLogger>>

    @Query("SELECT * FROM failure_log WHERE testSuites = :suite ORDER BY timestamp DESC")
    fun getLogsBySuite(suite: String): Flow<List<FailureLogger>>

    @Query("""
        SELECT * FROM failure_log 
        WHERE testcase LIKE '%' || :query || '%' 
        OR failureMsg LIKE '%' || :query || '%'
        ORDER BY timestamp DESC
    """)
    fun searchLogs(query: String): Flow<List<FailureLogger>>

    // --- STATS ---

    @Query("SELECT COUNT(*) FROM failure_log")
    fun getTotalCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM failure_log WHERE status = 'OPEN'")
    fun getOpenCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM failure_log WHERE status = 'FIXED'")
    fun getFixedCount(): Flow<Int>
}

/*
* A few things worth understanding as you type this out:
*Flow vs suspend — notice the pattern: queries that return data use Flow (so the UI auto-updates when data changes),
*while insert/update/delete use suspend (one-shot operations that need to run on a coroutine).
*
*
*
* */