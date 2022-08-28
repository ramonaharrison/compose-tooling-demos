package com.example.toolstalk.ui

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.toolstalk.model.BoardState
import com.example.toolstalk.model.KeyboardState
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

    fun playSymbol(symbol: Symbol) {
        when (symbol) {
            is Letter -> {
                Toast.makeText(context, symbol.value, Toast.LENGTH_SHORT).show()
            }
            is Enter -> {
                Toast.makeText(context, "Enter!", Toast.LENGTH_SHORT).show()
            }
            is Backspace -> {
                Toast.makeText(context, "Backspace!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}