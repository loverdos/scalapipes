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

package com.ckkloverdos.pipes.collection.immutable

import scala.collection.Iterable

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
object PList {
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
