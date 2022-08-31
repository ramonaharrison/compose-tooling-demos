package com.example.toolstalk.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.toolstalk.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    val context: Application,
) : ViewModel() {
    private var _boardState = MutableStateFlow(BoardState())
    val boardState = _boardState.asStateFlow()

    private var _keyboardState = MutableStateFlow(KeyboardState())
    val keyboardState = _keyboardState.asStateFlow()

    private var _position = MutableStateFlow(Position())

    fun playSymbol(symbol: Symbol) {
        val board = boardState.value
        if (board.isSolved) return

        val position = _position.value

        when (symbol) {
            is Letter -> {
                if (position.isEndOfRow && board.letterAtPosition(position) != null) {
                    // At end of row until user presses enter
                    return
                } else {
                    playLetter(board, position, symbol.value)
                }
            }
            is Enter -> {
                val submittedWord = board.wordAtPosition(position)
                if (submittedWord.length == 5) {
                    playWord(board, position, submittedWord)
                } else {
                    // All tiles must contain a letter before moving to the next row
                    return
                }
            }
            is Backspace -> {
                if (position.x > 0) {
                    playLetter(board, position.copy(x = position.x - 1), null)
                } else {
                    // Already at beginning of row
                    return
                }
            }
        }
    }

    private fun playLetter(board: BoardState, position: Position, letter: String?) {
        val currentRow = board.rowStates[position.y]
        val updatedTile = currentRow.tileStates[position.x].copy(
            letter = letter
        )
        val updatedRow = currentRow.copy(
            tileStates = currentRow.tileStates.toMutableList()
                .apply { this[position.x] = updatedTile }
        )
        val updatedRowStates =
            board.rowStates.toMutableList().apply { this[position.y] = updatedRow }

        val newPosition = if (letter == null) {
            position
        } else {
            position.nextPosition()
        }
        this._position.value = newPosition
        this._boardState.value = board.copy(rowStates = updatedRowStates)
    }

    private fun playWord(board: BoardState, position: Position, word: String) {
        val letterBudget = solution.groupingBy { it }.eachCount().toMutableMap()
        val updatedTileStates = board.rowStates[position.y].tileStates
            .mapIndexed { index, tileState ->
                val letter = tileState.letter!![0]
                val answerState = if (solution.contains(letter, true)) {
                    if (solution[index] == letter) {
                        AnswerState.CORRECT
                    } else {
                        AnswerState.KINDA
                    }
                } else {
                    AnswerState.WRONG
                }
                if (answerState == AnswerState.CORRECT) {
                    letterBudget[letter] = letterBudget[letter]!! - 1
                }
                TileState(
                    letter = tileState.letter,
                    answerState = answerState
                )
            }
            .map { tileState ->
                val answerState = tileState.answerState
                val letter = tileState.letter!![0]
                when (answerState) {
                    AnswerState.KINDA -> {
                        if (letterBudget[letter]!! > 0) {
                            letterBudget[letter] = letterBudget[letter]!! - 1
                            tileState
                        } else {
                            tileState.copy(answerState = AnswerState.WRONG)
                        }
                    }
                    else -> tileState
                }
            }

        val updatedRowStates = board.rowStates.mapIndexed { index, rowState ->
            if (index == position.y) {
                RowState(updatedTileStates)
            } else {
                rowState
            }
        }

        this._position.value = position.moveToNextRow()
        this._boardState.value = board.copy(rowStates = updatedRowStates)
    }

    companion object {
        const val solution = "TOOLS"
    }
}