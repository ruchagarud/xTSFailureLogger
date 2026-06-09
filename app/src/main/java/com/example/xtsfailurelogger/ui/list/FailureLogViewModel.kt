package com.example.xtsfailurelogger.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xtsfailurelogger.data.model.FailureLogger
import com.example.xtsfailurelogger.data.model.FailureStatus
import com.example.xtsfailurelogger.repository.FailureLogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FailureLogViewModel @Inject constructor(
    private val repository: FailureLogRepository
): ViewModel(){

    //internal mutable state
    private val _searchQuery = MutableStateFlow("")
    private val _selectedFilter = MutableStateFlow<FailureStatus?>(null)

    //expose search and filter so ui can read them
    val searchQuery : StateFlow<String> = _searchQuery
    val selectedFiler : StateFlow<FailureStatus?> = _selectedFilter

    // debounce search — waits 300ms after user stops typing
    // so we don't query the DB on every single keystroke
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _logs = combine(_searchQuery, _selectedFilter) { query, filter ->
        Pair(query, filter)
    }.flatMapLatest { (query, filter) ->
        when {
            query.isNotBlank() -> repository.searchLogs(query)
            filter != null -> repository.getLogByStatus(filter)
            else -> repository.getAllLogs()
        }
    }

    private val _totalCount = repository.getTotalCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    private val _openCount = repository.getOpenCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    private val _fixedCount = repository.getFixedCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // --- single UI state the screen observes ---
    val uiState: StateFlow<FailureLogUiState> = combine(
        _logs,
        _totalCount,
        _openCount,
        _fixedCount
    ) { logs, total, open, fixed ->
        FailureLogUiState(
            logs = logs,
            isLoading = false,
            searchQuery = _searchQuery.value,
            selectedFilter = _selectedFilter.value,
            totalCount = total,
            openCount = open,
            fixedCount = fixed
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FailureLogUiState()
    )
// --- actions called from UI ---

    fun onSearchQueryChange(query: String) {
        _searchQuery.update { query }
    }

    fun onFilterChange(filter: FailureStatus?) {
        _selectedFilter.update { filter }
    }

    fun deleteLog(log: FailureLogger) {
        viewModelScope.launch { repository.deleteLog(log) }
    }

    fun insertLog(log: FailureLogger) {
        viewModelScope.launch { repository.insertLog(log) }
    }

    fun updateLog(log: FailureLogger) {
        viewModelScope.launch { repository.updateLog(log) }
    }
}