package com.example.xtsfailurelogger

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.xtsfailurelogger.data.local.AppDatabase
import com.example.xtsfailurelogger.data.model.FailureLogger
import com.example.xtsfailurelogger.data.model.FailureStatus
import com.example.xtsfailurelogger.data.model.TestSuites
import com.example.xtsfailurelogger.ui.list.LogListScreen
import com.example.xtsfailurelogger.ui.theme.XTSFailureLoggerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint       // required on every Activity that uses Hilt
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Test: insert a dummy failure log and read it back
//        lifecycleScope.launch(Dispatchers.IO) {
//            val dao = database.failureLoggerDao()
//
//            //Insert the record
//            dao.insertLog(
//                FailureLogger(
//                    testSuites = TestSuites.CTS,
//                    testcase = "module test#testcase",
//                    failureMsg = " xzy error ",
//                    status = FailureStatus.IN_PROGRESS,
//                    note = "Need to work",
//                    android = "14"
//                )
//            )
//
//            //Read the data
//            val logs = database.failureLoggerDao().getAllLogs().first()
//            Log.d("CTSLogger", "Total logs in DB: ${logs.size}")
//            logs.forEach {
//                Log.d("CTSLogger", "Test: ${it.testcase} | Status: ${it.status}")
//            }
//        }

        setContent {
            Text("Week 2 done — ViewModel ready")
            Text("DB test running — check Logcat")

            XTSFailureLoggerTheme {
                LogListScreen(
                    onAddClick = { },     // Week 4 — navigation
                    onItemClick = { }     // Week 4 — navigation
                )
            }
        }

//        setContent {
//            XTSFailureLoggerTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Week 2 done — ViewModel ready",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XTSFailureLoggerTheme {
        Greeting("Android")
    }
}