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

import scala.collection.immutable.Set

/**
  *
  * @author Christos KK Loverdos <loverdos@gmail.com>
  */
object PSet {
   @inline final def filter[A](p: (A) ⇒ Boolean): Set[A] ⇒ Set[A] = _.filter(p)

   @inline final def map[A, B](f: (A) ⇒ B): Set[A] ⇒ Set[B] = _.map(f)

   @inline final def foreach[A](f: (A) ⇒ Unit): Set[A] ⇒ Unit = _.foreach(f)

   @inline final def length[A]: Set[A] ⇒ Int = _.size

   @inline final def size[A]: Set[A] ⇒ Int = _.size

   // ML-ish
   @inline final def iter[A](f: (A) ⇒ Unit): Set[A] ⇒ Unit = _.foreach(f)

   @inline final def ofIterable[A]: Iterable[A] ⇒ Set[A] = _.toSet

   @inline final def ofList[A]: List[A] ⇒ Set[A] = _.toSet

   @inline final def ofArray[A]: Array[A] ⇒ Set[A] = _.toSet

   @inline final def ofMap[A, B]: Map[A, B] ⇒ Set[(A, B)] = _.toSet

   @inline final def ofSet[A]: Set[A] ⇒ Set[A] = identity
 }
