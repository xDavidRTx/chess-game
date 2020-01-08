package com.david.domain.pieces

import com.david.domain.{Direction, Directions, Move}
import com.david.engine.GameEngine.ChessBoard

trait PieceType {
  val displayName: Char
  val allowedDirections: Set[Direction]

  // Standard move validator that will be overided for more complex pieces
  def validMove(move: Move, board: ChessBoard): Boolean = {
    Directions.getDirection(move).exists { direction =>
      Vector(
        allowedDirections.contains(direction), //The direction is valid
        !piecesOnTheWay(move, board, direction) // There are no pieces on the way
      ).forall(identity)
    }
  }

  def piecesOnTheWay(move: Move, board: ChessBoard, direction: Direction): Boolean = {
    val offset = Directions.getDirectionOffset(direction)
    // checks every position on the board until the destination for pieces
    (1 until move.distance).foldLeft(false)((carryOver, dist) =>
      carryOver || board.isDefinedAt(move.startPosition + (offset._1 * dist, offset._2 * dist)))
  }
}