package com.bluedit

import com.twitter.finagle.{Service, Http}
import com.twitter.util.Await
import scala.collection.mutable
import io.finch._

import com.twitter.finagle.http.{Request, Response}
import java.util.UUID
import io.circe.generic.auto._
import io.finch.circe._
import io.circe.{Encoder, Json}

/*
 * Request readers
 * case class Foo(i: Int, s: Option[String])
 * 
 * val foo1: RequestReader[Foo] = 
 * (param("i").as[Int] :: paramOption("s")).as[Foo]
 * 
 * val foo2: RequestHeader[Foo] = RequestReader.derive[Foo].fromParams
 * 
 * implicit class RROps(val rr: RequestReader[String]) { 
 * 	def as[A: DecodeRequest] : RequestHeader[A] = ??
 * }
 * 
 * val foo: RequestReader[Foo] = body.as[Foo]
 * 
 * val patchUser: Endpoint[User] = 
 * patch("users" / int ? body.as[User]) { (id: Int, u: User) =>
 * */


/* Incomplete Decoders from Circe
 * 
 * case class user(id: Int, name: String)
 * val user: RequestReader[User] =
 * 	body.as[Int => User].map(f => f(random.nextInt)
 * */


object Main extends App {
  
  val e: Endpoint[String] = get("hello" / string) { s: String =>
    Ok(s"Hello $s")
  }
  
  val e2: Endpoint[String] = get("hello" / string) { s: String =>
    Ok(s"Hello $s")
  }
  

  
  // add multiple endpoints and make it as a service
  // :+:  is called space invader
  //val composedEndpoint: Endpoint[String :+: String :+: CNil] = e :+: e2
  val api: Service[Request, Response] = Links.composedLinksEndpoint.toService
  Await.ready(Http.server.serve(":8089",  api))
  
  /*
   * type classes powered encoding
   * trait EncodeResponse[A] {
   * 	def apply(rep: A): Buf
   * }
   * 
   * implicit def encodeCirci[A](implicit 
   * 	e: io.circi.Encoder[A]
   * ): EncodeResponse[A] = EncodeResponse(a => Utf8(e(a).noSpaces)
   * 
   * coproduct endpoints
   * val foo: Endpoint[Foo] = Endpoint { Ok(Foo("doo")) }
   * */
}