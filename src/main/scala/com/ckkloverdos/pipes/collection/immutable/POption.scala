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

import scala.collection.{Seq, Map, Set}

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
object POption {
  @inline final def filter[A](p: (A) ⇒ Boolean): Option[A] ⇒ Option[A] = _.filter(p)

  @inline final def getOr[A](default: ⇒A): Option[A] ⇒ A = _.getOrElse(default)

  @inline final def map[A, B](f: (A) ⇒ B): Option[A] ⇒ Option[B] = _.map(f)

  @inline final def map_1[A]: Option[(A, _)] ⇒ Option[A] = _.map(_._1)

  @inline final def map_2[A]: Option[(_, A)] ⇒ Option[A] = _.map(_._2)

  @inline final def flatMap[A, B](f: (A) ⇒ Option[B]): Option[A] ⇒ Option[B] = _.flatMap(f)

  @inline final def foreach[A](f: (A) ⇒ Unit): Option[A] ⇒ Unit = _.foreach(f)

  @inline final def length[A]: Option[A] ⇒ Int = size(_)

  @inline final def size[A]: Option[A] ⇒ Int = x ⇒ if(x.isDefined) 1 else 0

  @inline final def first[A]: Option[A] ⇒ Option[A] = identity

//  @inline final def flatten[A]: Traversable[Traversable[A]] ⇒ Option[A] = _.flatten.headOption
  @inline final def flatten[A]: Option[Option[A]] ⇒ Option[A] = _.flatten.headOption

  @inline final def mkString[A](sep: String): Option[A] ⇒ String = _.mkString(sep)

  @inline final def mkString[A](start: String, sep: String, end: String): Option[A] ⇒ String = _.mkString(start, sep, end)

  // ML-ish
  @inline final def iter[A](f: (A) ⇒ Unit): Option[A] ⇒ Unit = _.foreach(f)

  @inline final def ofIterable[A]: Iterable[A] ⇒ Option[A] = _.headOption

  @inline final def ofSeq[A]: Seq[A] ⇒ Option[A] = _.headOption

  @inline final def ofSet[A]: Set[A] ⇒ Option[A] = _.headOption

  @inline final def ofList[A]: List[A] ⇒ Option[A] = _.headOption

  @inline final def ofArray[A]: Array[A] ⇒ Option[A] = _.headOption

  @inline final def ofMap[A, B]: Map[A, B] ⇒ Option[(A, B)] = _.headOption

  @inline final def ofAny[A]: A ⇒ Option[A] = Option.apply
}
