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
import com.example.xtsfailurelogger.data.model.TestSuites

@Composable
fun SuiteChip( suite : TestSuites){

    val (backgroundColor, textColor) = when (suite){
        TestSuites.CTS -> Pair(Color(0xFFE3F2FD), Color(0xFF1565C0))
        TestSuites.ATS -> Pair(Color(0xFFEDE7F6), Color(0xFF4527A0))
        TestSuites.ATS_I -> Pair(Color(0xFFEDE7F6), Color(0xFFFFEB3B))
        TestSuites.GSI -> Pair(Color(0xFFE8F5E9), Color(0xFF2E7D32))
        TestSuites.STS -> Pair(Color(0xFFFCE4EC), Color(0xFFC62828))
        TestSuites.BFG -> Pair(Color(0xFFFFF3E0), Color(0xFFE65100))
        TestSuites.BTS -> Pair(Color(0xFFE0F7FA), Color(0xFF00695C))
        TestSuites.HSAPI -> Pair(Color(0xFFE0F7FA), Color(0xFF9C27B0))
    }

    Text(
        text = suite.name,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    )
}