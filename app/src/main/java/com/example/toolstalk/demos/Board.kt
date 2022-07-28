package com.example.toolstalk.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toolstalk.model.*

@Composable
fun Board(modifier: Modifier = Modifier, boardState: BoardState) {
    Column(modifier = modifier.padding(20.dp)) {
        boardState.rowStates.forEach { rowState ->
            WordRow(rowState)
        }
    }
}


@Preview
@Composable
fun BoardPreview() {
    Surface {
        Board(boardState = sampleBoard1)
    }
}