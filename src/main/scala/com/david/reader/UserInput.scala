package com.david.reader

import java.io.IOException

trait UserInput {
  @throws(classOf[IOException])
  def nextMove() : Array[Int]
}
