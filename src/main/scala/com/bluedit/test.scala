package com.bluedit

import com.twitter.finagle.http.{Response, Request}
import com.twitter.finagle.{Service, Http}
import com.twitter.util.Await

import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import shapeless.{CNil, :+:}
import io.circe._, io.circe.generic.auto._, io.circe.syntax._
case class Foo2( f: String )
case class Bar2( b: String )
case class Person2( firstname: String, lastname: String )

object HelloService4 extends App {

  val foo2: Endpoint[Foo2] = get("foo" / string ) {
    name: String => Ok( Foo2(name))
  }

  val bar2: Endpoint[Bar2] = get("bar" / string ) {
    name: String => Ok( Bar2(name))
  }

  val person2: Endpoint[Person2] = get("person" / string ) {
    name: String => Ok( Person2( name, "Welkenbach"))
  }

  val api: Service[Request, Response] = (foo2 :+: bar2 :+: person2).toService 

  Await.ready(Http.server.serve(":9001", api ))
}