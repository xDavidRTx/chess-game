package com.david.domain

import com.david.domain.pieces.PieceType

case class Piece(pieceType: PieceType, color: Color) {
  def getName: Char = color match {
    case White => pieceType.displayName.toUpper
    case Black => pieceType.displayName.toLower
  }
}

sealed trait Color

case object White extends Color

case object Black extends Color