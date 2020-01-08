package com.david.domain

case class Move(startPosition: Position, destinationPosition: Position) {
  val distance: Int = {
    val absoluteDistance = (startPosition.column - destinationPosition.column, startPosition.row - destinationPosition.row) match {
      case (0, rows) => rows
      case (columns, 0) => columns
      case (x, _) => x // diagonal
    }
    Math.abs(absoluteDistance)
  }
}

object Move {
  def apply(points: Array[Int]): Move = {
    new Move(Position(points(0) + 1, 8 - points(1)), Position(points(2) + 1, 8 - points(3)))
  }
}