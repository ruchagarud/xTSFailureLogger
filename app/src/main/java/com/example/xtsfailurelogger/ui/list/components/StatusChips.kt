package com.example.xtsfailurelogger.ui.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtsfailurelogger.data.model.FailureStatus

@Composable
fun StatusChips(status : FailureStatus) {
    val (backgroundColor, textColor, label) = when(status){
        FailureStatus.OPEN -> Triple(
            Color(0xFFFFEBEE), Color(0xFFC62828), "OPEN"
        )
        FailureStatus.IN_PROGRESS -> Triple(
            Color(0xFFFFF8E1), Color(0xFFF57F17), "IN PROGRESS"
        )
        FailureStatus.FIXED -> Triple(
            Color(0xFFE8F5E9), Color(0xFF2E7D32), "FIXED"
        )
        FailureStatus.WONT_FIX -> Triple(
            Color(0xFFF5F5F5), Color(0xFF616161), "WON'T FIX"
        )
    }

    Text(
        text = label,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 3.dp)

    )
}