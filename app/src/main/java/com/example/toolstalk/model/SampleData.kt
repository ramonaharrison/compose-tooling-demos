package com.example.toolstalk.model

val sampleRow1 = RowState("MOUSE".map { TileState(it.toString(), AnswerState.CORRECT) })

val sampleTile = TileState("A")
val sampleTile1 = TileState("A", AnswerState.CORRECT)
val sampleTile2 = TileState("B", AnswerState.KINDA)
val sampleTile3 = TileState("C", AnswerState.WRONG)
val sampleTile4 = TileState("D", AnswerState.PENDING)

val sampleKeyboardState = KeyboardState()
