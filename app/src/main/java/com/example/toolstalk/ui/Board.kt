package com.example.toolstalk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.toolstalk.model.*

@Composable
fun Board(modifier: Modifier = Modifier, boardState: BoardState) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        boardState.rowStates.forEach { rowState ->
            WordRow(rowState)
        }
    }
}

class BoardStatePreviewParameterProvider : PreviewParameterProvider<BoardState> {
    override val values = sequenceOf(
        listOf("BOAR", "", "", "", "", "").toBoardState("TOOLS"),
        listOf("BOARD", "TOPIC", "TILES", "TOOL", "", "").toBoardState("TOOLS"),
        listOf("BOARD", "TOPIC", "TILES", "TRIAL", "TOOLS", "").toBoardState("TOOLS"),
    )
}

@Preview
@Composable
fun BoardPreview(
    @PreviewParameter(BoardStatePreviewParameterProvider::class) boardState: BoardState,
) {
    Surface {
        Board(boardState = boardState)
    }
}