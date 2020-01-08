package com.david.domain

import com.david.domain.pieces.{Bishop, King, Knight, Pawn, Queen, Rook}

import scala.collection.mutable

case class Board(chessBoard: mutable.HashMap[Position, Piece], whiteKing: Position, blackKing: Position)

object Board {
  // Initializes the board with the default kings positions
  def apply(): Board = new Board(new mutable.HashMap[Position, Piece](), Position(5, 1), Position(5, 8))

  def initializeBoard(): Board = {
    val columns = 1 to 8
    val rows = 1 to 8
    val board: Board = Board()
    //Position of the pieces on the first row for each player
    val firstRow = List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook)
    //White
    columns.map(i => board.chessBoard.addOne((Position(i, rows.start), Piece(firstRow(i - 1), White))))
    columns.map(i => board.chessBoard.addOne((Position(i, rows.start + 1), Piece(Pawn, White))))
    //Black
    columns.map(i => board.chessBoard.addOne((Position(i, rows.end), Piece(firstRow(i - 1), Black))))
    columns.map(i => board.chessBoard.addOne((Position(i, rows.end - 1), Piece(Pawn, Black))))
    board
  }
}


