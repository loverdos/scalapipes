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

package com.ckkloverdos.pipes.collection.generic

import scala.collection.Map
import scala.collection.Seq

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
object PSeq {
  @inline final def filter[A](p: (A) ⇒ Boolean): Seq[A] ⇒ Seq[A] = _.filter(p)

  @inline final def find[A](p: (A) ⇒ Boolean): Seq[A] ⇒ Option[A] = _.find(p)

  @inline final def filterDefined[A]: Seq[Option[A]] ⇒ Seq[A] = _.withFilter(_.isDefined).map(_.get)

  @inline final def map[A, B](f: (A) ⇒ B): Seq[A] ⇒ Seq[B] = _.map(f)

  @inline final def map_1[A]: Seq[(A, _)] ⇒ Seq[A] = _.map(_._1)

  @inline final def map_2[A]: Seq[(_, A)] ⇒ Seq[A] = _.map(_._2)

  @inline final def foreach[A](f: (A) ⇒ Unit): Seq[A] ⇒ Unit = _.foreach(f)

  @inline final def length[A]: Seq[A] ⇒ Int = _.length

  @inline final def size[A]: Seq[A] ⇒ Int = _.length

  @inline final def take[A](n: Int): Seq[A] ⇒ Seq[A] = _.take(n)

  @inline final def first[A]: Seq[A] ⇒ Option[A] = _.headOption

  @inline final def partition[A](f: (A) ⇒ Boolean): Seq[A] ⇒ (Seq[A], Seq[A]) = _.partition(f)

  @inline final def groupBy[A, K](f: (A) ⇒ K): Seq[A] ⇒ Map[K, Seq[A]] = _.groupBy(f)

  @inline final def mkString[A](sep: String): Seq[A] ⇒ String = _.mkString(sep)

  @inline final def mkString[A](start: String, sep: String, end: String): Seq[A] ⇒ String = _.mkString(start, sep, end)

  // This is for debugging
  @inline final def passThrough[A](f: (A) ⇒ Any): Seq[A] ⇒ Seq[A] = seq ⇒ {
    seq.foreach(f)
    seq
  }

  // ML-ish
  @inline final def iter[A](f: (A) ⇒ Unit): Seq[A] ⇒ Unit = _.foreach(f)

  @inline final def ofOne[A](x: A): Seq[A] = Seq(x)

  @inline final def ofIterable[A]: Iterable[A] ⇒ Seq[A] = _.toSeq

  @inline final def ofIterator[A]: Iterator[A] ⇒ Seq[A] = _.toSeq

  @inline final def ofList[A]: List[A] ⇒ Seq[A] = _.toSeq

  @inline final def ofArray[A]: Array[A] ⇒ Seq[A] = _.toSeq

  @inline final def ofMap[A, B]: Map[A, B] ⇒ Seq[(A, B)] = _.toSeq

  @inline final def ofMapSortedValuesBy[A, B, C](sortBy: (B) ⇒ C)(implicit ord: Ordering[C]): Map[A, B] ⇒ Seq[B] =
    it ⇒ Seq(it.toSeq.sortBy(kv ⇒ sortBy(kv._2)).map(_._2):_*)

  @inline final def ofMapFilteredValuesByKey[A, B](p: (A) ⇒ Boolean): Map[A, B] ⇒ Seq[B] =
    it ⇒ (for((k, v) ← it if p(k)) yield v).toSeq

  @inline final def ofMapValues[A, B]: Map[A, B] ⇒ Seq[B] = _.values.toSeq

  @inline final def ofJava[E]: java.util.Collection[E] ⇒ Seq[E] = it ⇒ {
    import scala.collection.JavaConverters._
    it.asScala.toSeq
  }

  @inline final def ofOption[A]: Option[A] ⇒ Seq[A] = {
    case Some(value) ⇒ Seq(value)
    case None ⇒ Seq()
  }
}
