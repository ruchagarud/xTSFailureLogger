package com.example.xtsfailurelogger.repository

import com.example.xtsfailurelogger.data.local.FailureLoggerDAO
import com.example.xtsfailurelogger.data.model.FailureLogger
import com.example.xtsfailurelogger.data.model.FailureStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class FailureLogRepository @Inject constructor(
    private val dao : FailureLoggerDAO
) {

    // --- READ ---
    fun getAllLogs() : Flow<List<FailureLogger>> = dao.getAllLogs()
    fun getLogByStatus(status: FailureStatus): Flow<List<FailureLogger>> = dao.getLogByStatus(status)
    fun getLogsBySuite(suite: String): Flow<List<FailureLogger>> = dao.getLogsBySuite(suite)
    fun searchLogs(query: String): Flow<List<FailureLogger>> = dao.searchLogs(query)
    fun getTotalCount(): Flow<Int> = dao.getTotalCount()
    fun getOpenCount(): Flow<Int> = dao.getOpenCount()
    fun getFixedCount(): Flow<Int> = dao.getFixedCount()
    suspend fun getLogById(id:Int): FailureLogger? = dao.getLogById(id)

    // --- WRITE ---
    suspend fun insertLog(log : FailureLogger) =  dao.insertLog(log)
    suspend fun updateLog(log : FailureLogger) = dao.updateLog(log)
    suspend fun deleteLog(log: FailureLogger) = dao.deleteLog(log)
    suspend fun deleteByID(id: Int) : Int = dao.deleteByID(id)


}