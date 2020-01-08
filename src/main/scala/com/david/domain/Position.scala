package com.david.domain

case class Position(column: Int, row: Int) {
  def +(that: (Int, Int)): Position = Position(column + that._1, row + that._2)
}
