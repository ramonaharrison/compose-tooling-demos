package com.example.toolstalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.toolstalk.model.BoardState
import com.example.toolstalk.model.KeyboardState
import com.example.toolstalk.model.sampleBoard1
import com.example.toolstalk.ui.Board
import com.example.toolstalk.ui.Keyboard
import com.example.toolstalk.ui.theme.ToolsTalkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolsTalkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen(sampleBoard1)
                }
            }
        }
    }
}

@Composable
fun GameScreen(boardState: BoardState, modifier: Modifier = Modifier) {
    Surface {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Board(
                boardState = boardState,
                modifier = Modifier.weight(1f)
            )
            Keyboard(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                keyboardState = KeyboardState()
            )
        }

    }
}
