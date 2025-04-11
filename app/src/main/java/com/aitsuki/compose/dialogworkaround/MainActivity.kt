package com.aitsuki.compose.dialogworkaround

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.aitsuki.compose.dialogworkaround.ui.component.BottomDialog
import com.aitsuki.compose.dialogworkaround.ui.component.CenterDialog
import com.aitsuki.compose.dialogworkaround.ui.component.WorkaroundDialog
import com.aitsuki.compose.dialogworkaround.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(it)
                            .padding(24.dp)
                    ) {
                        HomeContent()
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeContent() {
    var showSimple by remember { mutableStateOf(false) }
    var showBottomSimple by remember { mutableStateOf(false) }
    var showInput by remember { mutableStateOf(false) }
    var showBottomInput by remember { mutableStateOf(false) }
    var showScrollable by remember { mutableStateOf(false) }
    var showBottomScrollable by remember { mutableStateOf(false) }
    var showFullScreen by remember { mutableStateOf(false) }
    var showFullScreenWithoutWorkaround by remember { mutableStateOf(false) }

    if (showSimple) {
        CenterDialog(onDismissRequest = { showSimple = false }) {
            SimpleContent()
        }
    }

    if (showBottomSimple) {
        BottomDialog(onDismissRequest = { showBottomSimple = false }) {
            SimpleContent()
        }
    }

    if (showInput) {
        CenterDialog(onDismissRequest = { showInput = false }) {
            InputContent()
        }
    }

    if (showBottomInput) {
        BottomDialog(onDismissRequest = { showBottomInput = false }) {
            InputContent()
        }
    }

    if (showScrollable) {
        CenterDialog(
            onDismissRequest = { showScrollable = false },
            modifier = Modifier.fillMaxHeight(0.92f)
        ) {
            ScrollContent()
        }
    }

    if (showBottomScrollable) {
        BottomDialog(
            onDismissRequest = { showBottomScrollable = false },
            modifier = Modifier.fillMaxHeight(0.86f)
        ) {
            ScrollContent()
        }
    }

    if (showFullScreen) {
        WorkaroundDialog(onDismissRequest = { showFullScreen = false }) {
            FullScreenContent("Hello, this is a custom full-screen dialog") {
                showFullScreen = false
            }
        }
    }

    if (showFullScreenWithoutWorkaround) {
        Dialog(
            onDismissRequest = { showFullScreenWithoutWorkaround = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            )
        ) {
            FullScreenContent("Full-screen dialog without workaround") {
                showFullScreenWithoutWorkaround = false
            }
        }
    }


    Button(onClick = { showSimple = true }) {
        Text("Simple Dialog")
    }

    Button(onClick = { showBottomSimple = true }) {
        Text("Bottom Simple Dialog")
    }

    Button(onClick = { showInput = true }) {
        Text("Input Dialog")
    }

    Button(onClick = { showBottomInput = true }) {
        Text("Bottom Input Dialog")
    }

    Button(onClick = { showScrollable = true }) {
        Text("Scrollable Content Dialog")
    }

    Button(onClick = { showBottomScrollable = true }) {
        Text("Bottom Scrollable Content Dialog")
    }

    Button(onClick = { showFullScreen = true }) {
        Text("Custom FullScreen Dialog")
    }

    Button(onClick = { showFullScreenWithoutWorkaround = true }) {
        Text("❌ FullScreen Dialog Without Workaround")
    }
}

@Composable
private fun SimpleContent() {
    Text(
        "Simple Dialog",
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.padding(24.dp)
    )
}

@Composable
private fun InputContent() {
    var value by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        value = value,
        onValueChange = { value = it },
        label = { Text("Email") },
        placeholder = { Text("Please enter your email") },
    )
}

@Composable
private fun ScrollContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(stringResource(R.string.lorem_ipsum).take(150))
        InputContent()
        Text(stringResource(R.string.lorem_ipsum))
    }
}

@Composable
private fun FullScreenContent(
    text: String,
    onDismissRequest: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFCFF8D0)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text)
            Spacer(Modifier.height(24.dp))
            Button(onClick = onDismissRequest) {
                Text("Close")
            }
        }
    }
}