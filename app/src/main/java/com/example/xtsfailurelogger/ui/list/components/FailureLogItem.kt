package com.example.xtsfailurelogger.ui.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtsfailurelogger.data.model.FailureLogger
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FailureLogItem(
    log: FailureLogger,
    modifier: Modifier = Modifier
){
    Card(modifier  = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp,  vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            // top row — suite chip + status chip
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SuiteChip(suite = log.testSuites)
                StatusChips(status = log.status)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // test name
            Text(
                text = log.testcase,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            // failure reason
            Text(
                text = log.failureMsg,
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // bottom row — device + timestamp
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
//                    text = if (log.deviceModel.isNotEmpty()) log.deviceModel else "No device",
                    text = if (log.android.isNotEmpty()) log.android else "No device",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
                Text(
                    text = SimpleDateFormat(
                        "dd MMM yyyy, HH:mm",
                        Locale.getDefault()
                    ).format(Date(log.timestamp)),
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}