package com.example.toolstalk.ui

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.toolstalk.model.BoardState
import com.example.toolstalk.model.KeyboardState
import com.example.toolstalk.model.Position
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
                Toast.makeText(context, "Enter!", Toast.LENGTH_SHORT).show()
            }
            is Backspace -> {
                playLetter(board, position, null)
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
            position.previousPosition()
        } else {
            position.nextPosition()
        }
        this._position.value = newPosition
        this._boardState.value = board.copy(rowStates = updatedRowStates)
    }
}