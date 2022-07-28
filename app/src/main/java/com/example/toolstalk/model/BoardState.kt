package com.example.toolstalk.model

import androidx.compose.ui.graphics.Color
import com.example.toolstalk.ui.theme.*

data class BoardState(
    val rowStates: List<RowState> = List(6) { RowState() }
)

data class RowState(
    val tileStates: List<TileState> = List(5) { TileState() }
)

data class TileState(
    val letter: String? = null,
    val answerState: AnswerState = AnswerState.PENDING
)

enum class AnswerState(
    val foregroundColor: Color,
    val backgroundColor: Color,
    val showBorder: Boolean = false
) {
    PENDING(Black, White, true),
    WRONG(White, Grey),
    KINDA(White, Yellow),
    CORRECT(White, Green)
}