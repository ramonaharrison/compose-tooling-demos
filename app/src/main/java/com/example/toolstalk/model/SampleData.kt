package com.example.toolstalk.model


val sampleBoard1 = listOf("BOARD", "TOPIC", "TILES", "TOOL", "", "").toBoardState("TOOLS")

val sampleRow1 = RowState("MOUSE".map { TileState(it.toString(), AnswerState.CORRECT) })

val sampleTile1 = TileState("A", AnswerState.CORRECT)
val sampleTile2 = TileState("B", AnswerState.KINDA)
val sampleTile3 = TileState("C", AnswerState.WRONG)
val sampleTile4 = TileState("D", AnswerState.PENDING)
val sampleTile5 = TileState(null, AnswerState.PENDING)

fun List<String>.toBoardState(solution: String): BoardState {
    val rows = mutableListOf<RowState>()
    for (y in 0..5) {
        val tiles = mutableListOf<TileState>()
        for (x in 0..4) {
            val row = this.getOrNull(y)
            val letter = row?.getOrNull(x)?.toString()
            val tile = if (letter != null) {
                if (row.length < 5) {
                    TileState(letter, AnswerState.PENDING)
                } else if (letter == solution[x].toString()) {
                    TileState(letter, AnswerState.CORRECT)
                } else if (solution.contains(letter)) {
                    TileState(letter, AnswerState.KINDA)
                } else {
                    TileState(letter, AnswerState.WRONG)
                }
            } else {
                TileState()
            }
            tiles.add(tile)
        }
        rows.add(RowState(tiles))
    }
    return BoardState(rows)
}


