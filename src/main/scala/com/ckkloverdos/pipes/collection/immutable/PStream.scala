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

package com.ckkloverdos.pipes
package collection.immutable

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
object PStream {
  @inline final def filter[A](p: (A) ⇒ Boolean): Stream[A] ⇒ Stream[A] = _.filter(p)

  @inline final def filterDefined[A]: Stream[Option[A]] ⇒ Stream[A] = _.withFilter(_.isDefined).map(_.get)

  @inline final def map[A, B](f: (A) ⇒ B): Stream[A] ⇒ Stream[B] = _.map(f)

  @inline final def map_1[A]: Stream[(A, _)] ⇒ Stream[A] = _.map(_._1)

  @inline final def map_2[A]: Stream[(_, A)] ⇒ Stream[A] = _.map(_._2)

  @inline final def foreach[A](f: (A) ⇒ Unit): Stream[A] ⇒ Unit = _.foreach(f)

  @inline final def length[A]: Stream[A] ⇒ Int = _.length

  @inline final def size[A]: Stream[A] ⇒ Int = _.length

  @inline final def first[A]: Stream[A] ⇒ Option[A] = _.headOption

  @inline final def partition[A](f: (A) ⇒ Boolean): Stream[A] ⇒ (Stream[A], Stream[A]) = _.partition(f)

  @inline final def groupBy[A, K](f: (A) ⇒ K): Stream[A] ⇒ Map[K, Stream[A]] = _.groupBy(f)

  @inline final def mkString[A](sep: String): Stream[A] ⇒ String = _.mkString(sep)

  @inline final def mkString[A](start: String, sep: String, end: String): Stream[A] ⇒ String = _.mkString(start, sep, end)

  // ML-ish
  @inline final def iter[A](f: (A) ⇒ Unit): Stream[A] ⇒ Unit = _.foreach(f)

  @inline final def ofOne[A](x: A): Stream[A] = Stream(x)

  @inline final def ofIterable[A]: Iterable[A] ⇒ Stream[A] = _.toStream

  @inline final def ofSeq[A]: Seq[A] ⇒ Stream[A] = _.toStream

  @inline final def ofList[A]: List[A] ⇒ Stream[A] = _.toStream

  @inline final def ofArray[A]: Array[A] ⇒ Stream[A] = _.toStream

  @inline final def ofMap[A, B]: Map[A, B] ⇒ Stream[(A, B)] = _.toStream

  @inline final def ofMapSortedValuesBy[A, B, C](sortBy: (B) ⇒ C)(implicit ord: Ordering[C]): Map[A, B] ⇒ Stream[B] =
    it ⇒ it |> PSeq.ofMapSortedValuesBy(sortBy) |> PStream.ofSeq

  @inline final def ofMapFilteredValuesByKey[A, B](p: (A) ⇒ Boolean): Map[A, B] ⇒ Stream[B] =
    it ⇒ (for((k, v) ← it if p(k)) yield v).toStream

  @inline final def ofMapValues[A, B]: Map[A, B] ⇒ Stream[B] = it ⇒ it.values.toStream

  @inline final def ofJava[E]: java.util.Collection[E] ⇒ Stream[E] = it ⇒ {
    import scala.collection.JavaConverters._
    it.asScala.toStream
  }

  @inline final def ofSeries[A](n0: Int): ((Int) ⇒ A) ⇒ Stream[A] =
    f ⇒ Stream.cons(f(n0), ofSeries(n0 + 1)(f))

  @inline final def ofOption[A]: Option[A] ⇒ Stream[A] = {
    case Some(value) ⇒ Stream(value)
    case None ⇒ Stream()
  }
}
