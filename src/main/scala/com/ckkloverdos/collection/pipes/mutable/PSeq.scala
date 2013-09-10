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

package com.ckkloverdos.collection.pipes.mutable
import scala.collection.mutable.Seq

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

  // ML-ish
  @inline final def iter[A](f: (A) ⇒ Unit): Seq[A] ⇒ Unit = _.foreach(f)

  @inline final def ofIterable[A]: Iterable[A] ⇒ Seq[A] = it ⇒ scala.collection.mutable.Seq(it.toSeq:_*)

  @inline final def ofList[A]: List[A] ⇒ Seq[A] = it ⇒ scala.collection.mutable.Seq(it.toSeq:_*)

  @inline final def ofArray[A]: Array[A] ⇒ Seq[A] = it ⇒ scala.collection.mutable.Seq(it.toSeq:_*)

  @inline final def ofMap[A, B]: Map[A, B] ⇒ Seq[(A, B)] = it ⇒ scala.collection.mutable.Seq(it.toSeq:_*)
}