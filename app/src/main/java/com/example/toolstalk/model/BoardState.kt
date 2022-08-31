package com.example.toolstalk.model

import androidx.compose.ui.graphics.Color
import com.example.toolstalk.ui.Letter
import com.example.toolstalk.ui.theme.*

data class BoardState(
    val rowStates: List<RowState> = List(6) { RowState() }
) {
    val isSolved = rowStates.any { row -> row.isCorrectWord }

    fun letterAtPosition(position: Position): String? =
        rowStates[position.y].tileStates[position.x].letter

    fun wordAtPosition(position: Position): String =
        rowStates[position.y].tileStates.joinToString(separator = "") { tileState ->
            tileState.letter ?: ""
        }
}

data class RowState(
    val tileStates: List<TileState> = List(5) { TileState() }
) {
    val isCorrectWord =
        tileStates.filter { tile -> tile.answerState == AnswerState.CORRECT }.size == 5
}

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

data class KeyboardState(
    val keys: Map<Letter, AnswerState> =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ".associate { Letter(it.toString()) to AnswerState.PENDING }
)

data class Position(
    val x: Int = 0,
    val y: Int = 0
) {
    val isEndOfRow = x == 4

    fun nextPosition() = if (isEndOfRow) {
        this
    } else Position(x + 1, y)

    fun previousPosition() = if (x == 0) {
        this
    } else {
        Position(x - 1, y)
    }

    fun moveToNextRow() = if (y == 5) {
        this
    } else {
        Position(0, this.y + 1)
    }
}

