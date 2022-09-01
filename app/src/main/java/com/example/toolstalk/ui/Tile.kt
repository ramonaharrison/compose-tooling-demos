package com.example.toolstalk.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.example.toolstalk.model.TileState

@Composable
fun Tile(
    tileState: TileState,
    modifier: Modifier = Modifier,
) {
    TileContents(
        text = tileState.letter.orEmpty(),
        backgroundColor =
        tileState.answerState.backgroundColor,
        foregroundColor =
        tileState.answerState.foregroundColor,
        showBorder =
        tileState.answerState.showBorder,
        modifier = modifier
    )
}

@Composable
fun TileContents(
    text: String,
    backgroundColor: Color,
    foregroundColor: Color,
    showBorder: Boolean,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .then(
                if (showBorder)
                    Modifier.border(
                        width = 1.dp,
                        color = Color.Gray
                    )
                else Modifier
            )
            .background(backgroundColor)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        val size = min(maxWidth * 1.7f, maxHeight)
        val fontSize = size * 0.60f
        Text(
            text = text.uppercase(),
            color = foregroundColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = LocalDensity.current.run {
                fontSize.toSp()
            }
        )
    }
}

@Preview
@Composable
fun TilePreview() {
    Tile(tileState = TileState(letter = "A"))
}
