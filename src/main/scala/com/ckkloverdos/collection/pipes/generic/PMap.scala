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

package com.ckkloverdos.collection.pipes.generic

import scala.collection.Map
import scala.collection.Seq
import scala.collection.Set

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
object PMap {
  @inline final def filter[A, B](p: ((A, B)) ⇒ Boolean): Map[A, B] ⇒ Map[A, B] = _.filter(p)

  @inline final def map[A, B, C](f: ((A, B)) ⇒ C): Map[A, B] ⇒ Iterable[C] = _.map(f)

  @inline final def foreach[A, B](f: ((A, B)) ⇒ Unit): Map[A, B] ⇒ Unit = _.foreach(f)

  @inline final def length[A, B]: Map[A, B] ⇒ Int = _.size

  @inline final def size[A, B]: Map[A, B] ⇒ Int = _.size

  @inline final def groupBy[A, B, K](f: ((A, B)) ⇒ K): Map[A, B] ⇒ Map[K, Map[A, B]] = _.groupBy(f)

  // ML-ish
  @inline final def iter[A, B](f: ((A, B)) ⇒ Unit): Map[A, B] ⇒ Unit = _.foreach(f)

  @inline final def ofSeq[A, B]: Seq[(A, B)] ⇒ Map[A, B] = _.toMap

  @inline final def ofSet[A, B]: Set[(A, B)] ⇒ Map[A, B] = _.toMap

  @inline final def ofList[A, B]: List[(A, B)] ⇒ Map[A, B] = _.toMap

  @inline final def ofIterable[A, B]: Iterable[(A, B)] ⇒ Map[A, B] = _.toMap

  @inline final def ofArray[A, B]: Array[(A, B)] ⇒ Map[A, B] = _.toMap

  @inline final def ofJava[K, V]: java.util.Map[K, V] ⇒ Map[K, V] = it ⇒ {
    import scala.collection.JavaConverters._
    it.asScala
  }

  @inline final def ofFuncWithInitialMap[K, V](initialMap: Map[K, V] = Map())(f: (K) ⇒ V): Map[K, V] =
    new Map[K, V] {
      private[this] var cachedMap = initialMap

      def get(key: K): Option[V] =
        cachedMap.get(key) match {
          case some@Some(value) ⇒
            some

          case None ⇒
            try {
              val value = f(key)
              cachedMap += key → value
              Some(value)
            }
            catch {
              case e: Throwable ⇒ None
            }
        }


      def iterator: Iterator[(K, V)] =
        cachedMap.iterator

      def -(key: K): Map[K, V] =
        ofFuncWithInitialMap(cachedMap - key)(f)

      def +[B1 >: V](kv: (K, B1)): Map[K, B1] =
        ofFuncWithInitialMap(cachedMap + kv)(f)
    }

  @inline final def ofFuncWithInitialKeys[K, V](allKeys: Set[K] = Set())(f: (K) ⇒ V): Map[K, V] =
    new Map[K, V] {
      private[this] var cachedMap = Map[K, V]((for(k ← allKeys) yield (k, f(k))).toSeq:_*)

      def get(key: K): Option[V] =
        cachedMap.get(key) match {
          case some@Some(value) ⇒
            some

          case None ⇒
            try {
              val value = f(key)
              cachedMap += key → value
              Some(value)
            }
            catch {
              case e: Throwable ⇒ None
            }
        }

      def iterator: Iterator[(K, V)] =
        cachedMap.iterator

      def -(key: K): Map[K, V] =
        ofFuncWithInitialMap(cachedMap - key)(f)

      def +[B1 >: V](kv: (K, B1)): Map[K, B1] =
        ofFuncWithInitialMap(cachedMap + kv)(f)
    }
}
