package com.aitsuki.compose.dialogworkaround.ui.component

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

@Composable
fun Scrim(
    color: Color,
    onDismissRequest: () -> Unit,
) {
    if (color.isSpecified) {
        var visible by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            visible = true
        }
        val alpha by
        animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = TweenSpec())
        val dismiss =
            if (visible) {
                Modifier
                    .pointerInput(onDismissRequest) { detectTapGestures { onDismissRequest() } }
                    .semantics(mergeDescendants = true) {
                        traversalIndex = 1f
                        contentDescription = "Close"
                        onClick {
                            onDismissRequest()
                            true
                        }
                    }
            } else {
                Modifier
            }
        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismiss)
        ) {
            drawRect(color = color, alpha = alpha.coerceIn(0f, 1f))
        }
    }
}