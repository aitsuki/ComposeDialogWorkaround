package com.example.myapplication.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun CenterDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(28.dp),
    containerColor: Color = Color.White,
    contentColor: Color = contentColorFor(containerColor),
    scrimColor: Color = Color.Black.copy(0.32f),
    properties: WorkaroundDialogProperties = WorkaroundDialogProperties(
        lightSystemBars = false
    ),
    content: @Composable ColumnScope.() -> Unit,
) {
    WorkaroundDialog(
        properties = properties,
        onDismissRequest = onDismissRequest,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .semantics { isTraversalGroup = true }) {
            Scrim(
                color = scrimColor,
                onDismissRequest = onDismissRequest,
            )
            Surface(
                modifier =
                    modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.8f)
                        .windowInsetsPadding(WindowInsets.safeDrawing),
                shape = shape,
                color = containerColor,
                contentColor = contentColor,
            ) {
                Column(Modifier.fillMaxWidth()) {
                    content()
                }
            }
        }
    }
}