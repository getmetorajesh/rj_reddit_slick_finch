package com.bluedit


import com.twitter.finagle.{Service, Http}
import com.twitter.util.Await
import scala.collection.mutable
import io.finch._

import com.twitter.finagle.http.{Request, Response}
import java.util.UUID
import io.circe.generic.auto._
import io.finch.circe._
//import io.circe.{Encoder, Json}

case class Link(title: String)

object Link {
  
  def getaLink(id: Int): Link = { return  Link("dsada") }
}

case class LinkNotFound(id: UUID) extends Exception {
  override def getMessage: String = s"Todo(${id.toString}) is not found"
}


object Links{
 /* index
  
  new
  
  create
  
  show*/
  
  
 val getLink: Endpoint[Link] = get("link"/ int) { id: Int => 
     Link.getaLink(id) match {
       case link => {
         println("dasa")
        // TODO :: get link with ID 
         Ok(link)
       }
   }
  }

  /* val updateLink: Endpoint[Link] = put("link"/ uuid) { id: UUID => 
     Link.get(id) match {
       case Some(link) => {
        // TODO :: update 
         Ok(link);
       }
       case None => throw LinkNotFound(id)
       }
   }
  
  val deleteLink: Endpoint[Link] = delete("link" / uuid) { id: UUID =>
     Link.get(id) match {
       case Some(link) => Ok(link)
       case None => throw LinkNotFound(id)
     }
  }*/
  
  val postedLink: RequestReader[Link] =
    body.as[UUID => Link].map(f => f(UUID.randomUUID()))
   
  val postLink: Endpoint[Link] = 
    post("link" ? postedLink) { l: Link =>
   // Link.save() 
    Ok(l)
  }
  
  val composedLinksEndpoint: Endpoint[String :+: String :+: CNil] = getLink :+: postedLink
}