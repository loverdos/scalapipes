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

package com.ckkloverdos


/**
 * Scala collections with forward pipe operator, as in F# and OCaml.
 * No batteries included.
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
package object scalapipes {

  // let (|>) x f = f x
  implicit final class MLPipe[T](val x: T) extends AnyVal {
    def |>[B](f: (T) ⇒ B) = f(x)
  }

  object ISeq {
    import scala.collection.Seq

    @inline final def filter[A](p: (A) ⇒ Boolean): Seq[A] ⇒ Seq[A] = _.filter(p)

    @inline final def map[A, B](f: (A) ⇒ B): Seq[A] ⇒ Seq[B] = _.map(f)

    @inline final def foreach[A](f: (A) ⇒ Unit): Seq[A] ⇒ Unit = _.foreach(f)

    @inline final def length[A]: Seq[A] ⇒ Int = _.length

    @inline final def size[A]: Seq[A] ⇒ Int = _.length

    // ML-ish
    @inline final def iter[A](f: (A) ⇒ Unit): Seq[A] ⇒ Unit = _.foreach(f)

    @inline final def ofIterable[A]: Iterable[A] ⇒ Seq[A] = _.toSeq

    @inline final def ofList[A]: List[A] ⇒ Seq[A] = _.toSeq

    @inline final def ofArray[A]: Array[A] ⇒ Seq[A] = _.toSeq

    @inline final def ofMap[A, B]: Map[A, B] ⇒ Seq[(A, B)] = _.toSeq
  }

  object IList {
    import scala.collection.Iterable

    @inline final def filter[A](p: (A) ⇒ Boolean): List[A] ⇒ List[A] = _.filter(p)

    @inline final def map[A, B](f: (A) ⇒ B): List[A] ⇒ List[B] = _.map(f)

    @inline final def foreach[A](f: (A) ⇒ Unit): List[A] ⇒ Unit = _.foreach(f)

    @inline final def length[A]: List[A] ⇒ Int = _.length

    @inline final def size[A]: List[A] ⇒ Int = _.length

    // ML-ish
    @inline final def iter[A](f: (A) ⇒ Unit): List[A] ⇒ Unit = _.foreach(f)

    @inline final def ofSeq[A]: Seq[A] ⇒ List[A] = _.toList

    @inline final def ofIterable[A]: Iterable[A] ⇒ List[A] = _.toList

    @inline final def ofArray[A]: Array[A] ⇒ List[A] = _.toList

    @inline final def ofMap[A, B]: Map[A, B] ⇒ List[(A, B)] = _.toList
  }

  object IMap {
    import scala.collection.Map
    @inline final def filter[A, B](p: ((A, B)) ⇒ Boolean): Map[A, B] ⇒ Map[A, B] = _.filter(p)

    @inline final def map[A, B, C](f: ((A, B)) ⇒ C): Map[A, B] ⇒ Iterable[C] = _.map(f)

    @inline final def foreach[A, B](f: ((A, B)) ⇒ Unit): Map[A, B] ⇒ Unit = _.foreach(f)

    @inline final def length[A, B]: Map[A, B] ⇒ Int = _.size

    @inline final def size[A, B]: Map[A, B] ⇒ Int = _.size

    // ML-ish
    @inline final def iter[A, B](f: ((A, B)) ⇒ Unit): Map[A, B] ⇒ Unit = _.foreach(f)

    @inline final def ofSeq[A, B]: Seq[(A, B)] ⇒ Map[A, B] = _.toMap

    @inline final def ofList[A, B]: List[(A, B)] ⇒ Map[A, B] = _.toMap

    @inline final def ofIterable[A, B]: Iterable[(A, B)] ⇒ Map[A, B] = _.toMap

    @inline final def ofArray[A, B]: Array[(A, B)] ⇒ Map[A, B] = _.toMap
  }


  private[this] object tests {
    val one = 1
    val two = one |> (_ + 1)
    val three = two |> (_ + 1)
    val threeAgain = one |> (_ + 1) |> (_ + 1)

    val letters = Seq('a', 'b', 'c', 'd', 'a')
    val As = letters |>  ISeq.filter(ch ⇒ ch == 'a')
    val AsLen = As |> ISeq.length
    val AsLenPlusOne = As |>
                       ISeq.length |>
                       (_ + 1)

    val less = IndexedSeq(1, 2, 3) |> ISeq.filter(_ < 2)

    val map = Map(1 → "one", 2 → "two", 3 → "three")
    val incKeys = map |>
                  IMap.map {case (k, v) ⇒ k } |>
                  ISeq.ofIterable
  }
}
