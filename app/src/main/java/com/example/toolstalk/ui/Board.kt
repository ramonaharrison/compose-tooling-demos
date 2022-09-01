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
import com.example.toolstalk.model.AnswerState
import com.example.toolstalk.model.BoardState
import com.example.toolstalk.model.RowState
import com.example.toolstalk.model.TileState

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

class BoardStatePreviewParameterProvider
    : PreviewParameterProvider<BoardState> {
    override val values = sequenceOf(
        listOf("BOAR", "", "", "", "", "")
            .toBoardState("TOOLS"),
        listOf("BOARD", "TOPIC", "TILES", "TOOL", "", "")
            .toBoardState("TOOLS"),
        listOf("BOARD", "TOPIC", "TILES", "TRIAL", "TOOLS", "")
            .toBoardState("TOOLS"),
    )

    private fun List<String>.toBoardState(solution: String): BoardState {
        val rows = mutableListOf<RowState>()
        for (y in 0..5) {
            val tiles = mutableListOf<TileState>()
            for (x in 0..4) {
                val row = this.getOrNull(y)
                val letter = row?.getOrNull(x)?.toString()
                val tile = if (letter != null) {
                    if (row.length < 5) {
                        TileState(letter, AnswerState.PENDING)
                    } else if (letter == solution[x].toString()) {
                        TileState(letter, AnswerState.CORRECT)
                    } else if (solution.contains(letter)) {
                        TileState(letter, AnswerState.KINDA)
                    } else {
                        TileState(letter, AnswerState.WRONG)
                    }
                } else {
                    TileState()
                }
                tiles.add(tile)
            }
            rows.add(RowState(tiles))
        }
        return BoardState(rows)
    }
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