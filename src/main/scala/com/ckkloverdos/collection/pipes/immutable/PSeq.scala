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

package com.ckkloverdos.collection.pipes.immutable
import scala.collection.immutable.Seq
import scala.collection.immutable.Map

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
object PSeq {
  @inline final def filter[A](p: (A) ⇒ Boolean): Seq[A] ⇒ Seq[A] = _.filter(p)

  @inline final def map[A, B](f: (A) ⇒ B): Seq[A] ⇒ Seq[B] = _.map(f)

  @inline final def foreach[A](f: (A) ⇒ Unit): Seq[A] ⇒ Unit = _.foreach(f)

  @inline final def length[A]: Seq[A] ⇒ Int = _.length

  @inline final def size[A]: Seq[A] ⇒ Int = _.length

  @inline final def first[A]: Seq[A] ⇒ Option[A] = _.headOption

  @inline final def partition[A](f: (A) ⇒ Boolean): Seq[A] ⇒ (Seq[A], Seq[A]) = _.partition(f)

  @inline final def groupBy[A, K](f: (A) ⇒ K): Seq[A] ⇒ Map[K, Seq[A]] = _.groupBy(f)

  @inline final def mkString[A](sep: String): Seq[A] ⇒ String = _.mkString(sep)

  @inline final def mkString[A](start: String, sep: String, end: String): Seq[A] ⇒ String = _.mkString(start, sep, end)

  // ML-ish
  @inline final def iter[A](f: (A) ⇒ Unit): Seq[A] ⇒ Unit = _.foreach(f)

  @inline final def ofIterable[A]: Iterable[A] ⇒ Seq[A] = it ⇒ scala.collection.immutable.Seq(it.toSeq:_*)

  @inline final def ofList[A]: List[A] ⇒ Seq[A] = _.toSeq

  @inline final def ofArray[A]: Array[A] ⇒ Seq[A] = it ⇒ scala.collection.immutable.Seq(it.toSeq:_*)

  @inline final def ofMap[A, B]: Map[A, B] ⇒ Seq[(A, B)] = it ⇒ scala.collection.immutable.Seq(it.toSeq:_*)
}
