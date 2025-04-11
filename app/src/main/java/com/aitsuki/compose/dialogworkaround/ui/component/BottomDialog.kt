package com.aitsuki.compose.dialogworkaround.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
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
fun BottomDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
    containerColor: Color = Color.White,
    contentColor: Color = contentColorFor(containerColor),
    scrimColor: Color = Color.Black.copy(0.32f),
    contentWindowInsets: @Composable () -> WindowInsets = {
        WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)
    },
    properties: WorkaroundDialogProperties = WorkaroundDialogProperties(),
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
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top)),
                shape = shape,
                color = containerColor,
                contentColor = contentColor,
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(contentWindowInsets())
                ) {
                    content()
                }
            }
        }
    }
}