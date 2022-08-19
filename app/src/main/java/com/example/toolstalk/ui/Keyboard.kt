package com.example.toolstalk.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toolstalk.model.AnswerState
import com.example.toolstalk.model.KeyboardState

@Composable
fun Keyboard(
    keyboardState: KeyboardState,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row {
            "QWERTYUIOP".forEach {
                Key(
                    symbol = Letter(it.toString()),
                )
            }
        }
        Row {
            "ASDFGHJKL".forEach {
                Key(
                    symbol = Letter(it.toString()),
                )
            }
        }
        Row {
            Key(
                symbol = Enter,
                modifier = Modifier.weight(2f)
            )
            "ZXCVBNM".forEach {
                Key(
                    symbol = Letter(it.toString()),
                )
            }
            Key(
                symbol = Backspace,
                modifier = Modifier.weight(2f)
            )
        }
    }
}

@Composable
fun Key(
    symbol: Symbol,
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray,
    onClick: (Symbol) -> Unit = {}
) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .defaultMinSize(minWidth = 30.dp)
            .background(color = color)
            .clickable { onClick(symbol) }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        when (symbol) {
            is Backspace -> {
                Text(
                    text = "←",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
            }
            is Enter -> {
                Text(
                    text = "ENTER",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
            }
            is Letter -> {
                Text(
                    text = symbol.value,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
            }
        }

    }
}

sealed class Symbol

data class Letter(val value: String) : Symbol()
object Enter : Symbol()
object Backspace : Symbol()
