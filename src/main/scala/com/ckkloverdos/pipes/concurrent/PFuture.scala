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
package concurrent

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Promise, ExecutionContext, Future}

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
object PFuture {
  @inline final def filter[A](p: (A) ⇒ Boolean)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[A] ⇒ Future[A] =
    _.filter(p)

  @inline final def filterSeq[A](p: (A) ⇒ Boolean)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[Seq[A]] ⇒ Future[Seq[A]] =
    _.map(_.filter(p))

  @inline final def foreach[A](f: (A) ⇒ Unit)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[A] ⇒ Unit =
    _.foreach(f)

  @inline final def foreachSeq[A](f: (A) ⇒ Unit)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[Seq[A]] ⇒ Unit =
    _.foreach(_.foreach(f))

  @inline final def filterDefined[A]
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[Option[A]] ⇒ Future[A] =
    _.withFilter(_.isDefined).map(_.get)

  @inline final def filterSeqDefined[A]
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[Seq[Option[A]]] ⇒ Future[Seq[A]] =
    _.map(ISeq.filterDefined)

  @inline final def map[A, B](f: (A) ⇒ B)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[A] ⇒ Future[B] =
    _.map(f)

  @inline final def mapSeq[A, B](f: (A) ⇒ B)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[Seq[A]] ⇒ Future[Seq[B]] =
    _.map(_.map(f))

  @inline final def flatMap[A, B](f: (A) ⇒ Future[B])
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[A] ⇒ Future[B] =
    _.flatMap(f)

  @inline final def awaitCompletion[A](atMost: Duration)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[A] ⇒ Future[A] =
    Await.ready(_, atMost)

  @inline final def awaitResult[A](atMost: Duration): Future[A] ⇒ A =
    Await.result(_, atMost)

  // ML-ish
  @inline final def ofValue[A]: A ⇒ Future[A] = Future.successful

  @inline final def ofComputation[A]
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): (⇒A) ⇒ Future[A] =
    Future(_)

  @inline final def ofPromise[A]: Promise[A] ⇒ Future[A] = _.future

  @inline final def ofSeqFuture[A]
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Seq[Future[A]] ⇒ Future[Seq[A]] =
    Future.sequence[A, Seq]

  @inline final def iter[A](f: (A) ⇒ Unit)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[A] ⇒ Unit =
    _.foreach(f)

  @inline final def iterSeq[A](f: (A) ⇒ Unit)
                    (implicit ec: ExecutionContext = ExecutionContext.Implicits.global): Future[Seq[A]] ⇒ Unit =
    _.foreach(_.foreach(f))
}
