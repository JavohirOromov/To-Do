package com.javohir.to_do.presentation.screens.components
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun SwipeToDeleteContainer(
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    val deleteThreshold = 150f
    val cornerRadius = 12.dp
    val clipShape = RoundedCornerShape(cornerRadius)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(clipShape)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.error,
                    shape = clipShape
                )
                .alpha((abs(offsetX) / deleteThreshold).coerceIn(0f, 1f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "O'chirish",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(clipShape)
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            offsetX += dragAmount

                            if (offsetX > 0) offsetX = 0f

                            if (abs(offsetX) > deleteThreshold * 2) {
                                offsetX = -deleteThreshold * 2
                            }
                        },
                        onDragEnd = {
                            if (abs(offsetX) > deleteThreshold) {

                                onDelete()
                            }
                            offsetX = 0f
                        }
                    )
                }
        ) {
            content()
        }
    }
}