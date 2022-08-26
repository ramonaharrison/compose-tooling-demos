package com.example.toolstalk.demo

import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toolstalk.model.RowState
import com.example.toolstalk.model.TileState
import com.example.toolstalk.model.sampleRow1
import com.example.toolstalk.ui.Tile

@Composable
fun AnimatingRow(
    rowState: RowState,
    modifier: Modifier = Modifier,
) {

    var currentState by remember { mutableStateOf(AnimatingRowState.Active) }
    val transition = updateTransition(currentState, label = "Animating Row State")

    val rotation by transition.animateFloat(label = "Rotation") { state ->
        when (state) {
            AnimatingRowState.Active -> 0f
            AnimatingRowState.Submitted -> 90f
        }
    }

    var rotated by remember { mutableStateOf(false) }

    Row(modifier = modifier.fillMaxWidth().clickable { rotated = !rotated }) {
        Spacer(modifier = Modifier.weight(.5f))
        rowState.tileStates.forEach { tileState ->
            AnimatingTile(
                tileState = tileState,
                rotation = rotation,
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            )
        }
        Spacer(modifier = Modifier.weight(.5f))
    }
}

@Composable
fun AnimatingTile(
    tileState: TileState,
    rotation: Float,
    modifier: Modifier = Modifier,
) {
    Tile(
        tileState = tileState,
        modifier = modifier.graphicsLayer {
            rotationX = rotation
            cameraDistance = 8 * density
        }
    )
}

enum class AnimatingRowState {
    Active,
    Submitted
}

@Preview
@Composable
fun AnimatingRowPreview() {
    AnimatingRow(
        rowState = sampleRow1
    )
}