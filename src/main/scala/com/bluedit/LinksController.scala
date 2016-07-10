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
  def allLinks(): List[Link] = {  return List[Link("1")]}
  def getaLink(id: Int): Link = { return  Link("dsada") }
}

case class LinkNotFound(id: UUID) extends Exception {
  override def getMessage: String = s"Todo(${id.toString}) is not found"
}


object LinksController{
 /* index
  
  create
  
  store
  
  show

  edit

  update

  destroy*/

  val index: Endpoint[List[Link]] = get("links") {
    Ok(Link.allLinks())
  }

  /**
    * create
    */
  val postedLinkReader: RequestReader[Link] =
    body.as[UUID => Link].map(f => f(UUID.randomUUID()))

  val store: Endpoint[Link] =
    post("link" / "store" ? postedLinkReader) { l: Link =>
      // Link.save()
      Ok(l)
    }

  val show: Endpoint[Link] = get("link"/ int) { id: Int =>
    Link.getaLink(id) match {
      case link => {
        println("dasa")
        // TODO :: get link with ID
        Ok(link)
      }
    }
  }

  /*
  * Edit
  * */
  val edit: Endpoint[Link] = put("link" / int) { id: Int =>
    Link.getaLink(id) match {
      case link => {
        // if link exists then update it
        // TODO: //
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
  

  
  val composedLinksEndpoint: Endpoint[
              List[Link]
              :+: Link
              :+: LinK
              :+: CNil] = index :+: getLink :+: newLink
}