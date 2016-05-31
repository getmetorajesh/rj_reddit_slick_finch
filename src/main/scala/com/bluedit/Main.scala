package com.bluedit

import com.twitter.finagle.{Service, Http}
import com.twitter.util.Await

import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import com.twitter.finagle.http.{Request, Response}
import java.util.UUID

case class Link()

object Link 
{
  def get(id: UUID): Option[Link] = { return None}
}

case class LinkNotFound(id: UUID) extends Exception {
  override def getMessage: String = s"Todo(${id.toString}) is not found"
}



class Main extends App {
  val e: Endpoint[String] = get("hello" / string) { s: String =>
    Ok(s"Hello $s")
  }
  
  val e2: Endpoint[String] = get("hello" / string) { s: String =>
    Ok(s"Hello $s")
  }
  
  val deleteLink: Endpoint[Link] = delete("link" / uuid) { id: UUID =>
     Link.get(id) match {
       case Some(link) => Ok(link)
       case None => throw LinkNotFound(id)
     }
  }
  
  // add multiple endpoints and make it as a service
  // :+:  is called space invader
  //val composedEndpoint: Endpoint[String :+: String :+: CNil] = e :+: e2
  val api: Service[Request, Response] = (e :+: e2).toService
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