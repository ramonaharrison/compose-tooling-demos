package com.example.toolstalk.demos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toolstalk.model.RowState
import com.example.toolstalk.model.sampleRow1

@Composable
fun WordRow(
    rowState: RowState,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(.5f))
        rowState.tileStates.forEach { tileState ->
            Tile(
                tileState = tileState,
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            )
        }
        Spacer(modifier = Modifier.weight(.5f))
    }
}

@Preview
@Composable
fun WordRowPreview() {
    Surface {
        WordRow(rowState = sampleRow1)
    }
}