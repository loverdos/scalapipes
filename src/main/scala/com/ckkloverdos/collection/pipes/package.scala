/*
 * Copyright (c) 2013 Christos KK Loverdos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ckkloverdos.collection

/**
 * Scala collections with forward pipe operator, as in F# and OCaml.
 * No batteries included.
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
package object pipes {
  // let (|>) x f = f x
  implicit final class MLPipe[T](val x: T) extends AnyVal {
    def |>[B](f: (T) ⇒ B) = f(x)
  }

  private[this] object tests {
    val one = 1
    val two = one |> (_ + 1)
    val three = two |> (_ + 1)
    val threeAgain = one |> (_ + 1) |> (_ + 1)

    val letters = Seq('a', 'b', 'c', 'd', 'a')
    val As = letters |>  PSeq.filter(ch ⇒ ch == 'a')
    val AsLen = As |> PSeq.length
    val AsLenPlusOne = As |>
      PSeq.length |>
      (_ + 1)

    val less = IndexedSeq(1, 2, 3) |> PSeq.filter(_ < 2)

    val map = Map(1 → "one", 2 → "two", 3 → "three")
    val incKeys = map |>
      PMap.map {case (k, v) ⇒ k } |>
      PSeq.ofIterable
  }
}
