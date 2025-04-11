package com.aitsuki.compose.dialogworkaround

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.aitsuki.compose.dialogworkaround.R
import com.aitsuki.compose.dialogworkaround.ui.component.CenterDialog
import com.aitsuki.compose.dialogworkaround.ui.component.WorkaroundDialog
import com.aitsuki.compose.dialogworkaround.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
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
    var showSimpleDialog by remember { mutableStateOf(false) }
    var showListDialog by remember { mutableStateOf(false) }
    var showInputDialog by remember { mutableStateOf(false) }
    var showListInputDialog by remember { mutableStateOf(false) }
    var workaround by remember { mutableStateOf(true) }

    if (showSimpleDialog) {
        if (workaround) {
//            WorkaroundDialog(onDismissRequest = { showSimpleDialog = false }) {
//                SimpleDialogContent()
//            }
            CenterDialog(
                onDismissRequest = { showSimpleDialog = false },
            ) {
                Text(
                    stringResource(R.string.lorem_ipsum),
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
        } else {
            Dialog(onDismissRequest = { showSimpleDialog = false }) {
                SimpleDialogContent()
            }
        }
    }

    if (showListDialog) {
        if (workaround) {
            WorkaroundDialog(onDismissRequest = { showListDialog = false }) {
                ListDialogContent()
            }
        } else {
            Dialog(onDismissRequest = { showListDialog = false }) {
                ListDialogContent()
            }
        }
    }

    if (showInputDialog) {
        if (workaround) {
            WorkaroundDialog(onDismissRequest = { showInputDialog = false }) {
                InputDialogContent { showInputDialog = false }
            }
        } else {
            Dialog(
                onDismissRequest = { showInputDialog = false },
            ) {
                InputDialogContent(
                    modifier = Modifier.imePadding()
                ) { showInputDialog = false }
            }
        }
    }

    if (showListInputDialog) {
        if (workaround) {
            WorkaroundDialog(onDismissRequest = { showListInputDialog = false }) {
                ListInputDialogContent { showListInputDialog = false }
            }
        } else {
            Dialog(
                onDismissRequest = { showListInputDialog = false },
            ) {
                ListInputDialogContent(
                    modifier = Modifier.imePadding()
                ) { showListInputDialog = false }
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = workaround, onCheckedChange = { workaround = !workaround })
            Text(text = "Workaround")
        }
        Button(onClick = { showSimpleDialog = true }) {
            Text(text = "Simple dialog")
        }
        Button(onClick = { showListDialog = true }) {
            Text(text = "List dialog")
        }
        Button(onClick = { showInputDialog = true }) {
            Text(text = "Input dialog")
        }
        Button(onClick = { showListInputDialog = true }) {
            Text(text = "List input dialog")
        }
    }
}

@Composable
private fun SimpleDialogContent() {
    Surface(shape = shapes.extraLarge, color = MaterialTheme.colorScheme.primary) {
        Text(
            text = "Simple Dialog",
            style = typography.displaySmall,
            modifier = Modifier.padding(32.dp)
        )
    }
}

@Composable
private fun ListDialogContent() {
    Surface(shape = shapes.extraLarge) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(100) { index ->
                Text(text = "Item ${index + 1}")
            }
        }
    }
}

@Composable
private fun InputDialogContent(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {
    Surface(shape = shapes.extraLarge, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            var value by remember { mutableStateOf("") }
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                modifier = Modifier.weight(1f, false)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onConfirm, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Confirm")
            }
        }
    }
}

@Composable
private fun ListInputDialogContent(modifier: Modifier = Modifier, onConfirm: () -> Unit) {
    Surface(shape = shapes.extraLarge, modifier = modifier) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(100) { index ->
                    Text(text = "Item ${index + 1}")
                }
            }
            var value by remember { mutableStateOf("") }
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                maxLines = 5,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Button(
                onClick = onConfirm, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Confirm")
            }
        }
    }
}