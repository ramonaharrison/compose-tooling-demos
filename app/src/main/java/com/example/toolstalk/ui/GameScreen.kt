package com.example.toolstalk.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.toolstalk.ui.theme.Typography

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameScreenViewModel = hiltViewModel(),
) {
    val boardState = viewModel.boardState.collectAsState()
    val keyboardState = viewModel.keyboardState.collectAsState()
    Surface {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = "WORDTOOL",
                textAlign = TextAlign.Center,
                style = Typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            Board(
                boardState = boardState.value,
                modifier = Modifier.weight(1f)
            )
            Keyboard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                keyboardState = keyboardState.value,
                onKeyClick = { symbol -> viewModel.playSymbol(symbol) }
            )
        }
    }
}
