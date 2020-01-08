package com.david.domain

sealed trait Direction

object Directions {

  case object N extends Direction // North
  case object NE extends Direction // North-East
  case object E extends Direction // East
  case object SE extends Direction // South-East
  case object S extends Direction // South
  case object SW extends Direction // South-West
  case object W extends Direction // West
  case object NW extends Direction // North-West

  def getDirectionOffset(direction: Direction): (Int, Int) = {
    direction match {
      case N => (0, 1)
      case NE => (1, 1)
      case E => (1, 0)
      case SE => (1, -1)
      case S => (0, -1)
      case SW => (-1, -1)
      case W => (-1, 0)
      case NW => (-1, 1)
    }
  }

  def getDirection(move: Move): Option[Direction] = {
    (move.destinationPosition.column - move.startPosition.column, move.destinationPosition.row - move.startPosition.row) match {
      case (0, 0) => None
      case (0, r) => if (r > 0) Some(N) else Some(S)
      case (c, 0) => if (c > 0) Some(E) else Some(W)
      case (c, r) if (Math.abs(c) == Math.abs(r)) => getDiagonal(c, r)
      case _ => None
    }
  }

  private def getDiagonal(colDiff: Int, rowDiff: Int): Option[Direction] =
    (colDiff / Math.abs(colDiff), rowDiff / Math.abs(rowDiff)) match {
      case (1, 1) => Some(NE)
      case (1, -1) => Some(SE)
      case (-1, -1) => Some(SW)
      case (-1, 1) => Some(NW)
      case _ => None
    }
}


