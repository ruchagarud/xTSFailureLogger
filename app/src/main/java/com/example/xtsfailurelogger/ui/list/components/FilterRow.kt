package com.example.xtsfailurelogger.ui.list.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import com.example.xtsfailurelogger.data.model.FailureStatus

@Composable
fun FilterRow(
    selectedFilter : FailureStatus?,
    onFilterSelected : (FailureStatus?)  -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        //AllFilter
        FilterChip(
            selected = selectedFilter == null,
            onClick ={ onFilterSelected(null)},
            label = { Text("All") }
        )

        // One chip per status
        FailureStatus.entries.forEach { status ->
            FilterChip(
                selected = selectedFilter ==status,
                onClick = { onFilterSelected (status)},
                label = {
                    Text(
                        text = when(status){
                            FailureStatus.OPEN -> "OPEN"
                            FailureStatus.FIXED -> "FIXED"
                            FailureStatus.IN_PROGRESS -> "In-PROGRESS"
                            FailureStatus.WONT_FIX -> "WONT_FIX"
                        }
                    )
                }
            )
        }
    }
}