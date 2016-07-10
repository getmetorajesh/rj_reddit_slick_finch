//import cats._
//import io.circe._

object trail {
  println("Welcome to the Scala ")                //> Welcome to the Scala 
  
  implicit val multiplier = 2                     //> multiplier  : Int = 2
	implicit val showImag = true              //> showImag  : Boolean = true

	//def toString(implicit precision: Int, showImag:Boolean): String = "dsada"
   // toString()

	def toString(showImag:Boolean)(implicit precision: Int): String = "sdsd"
                                                  //> toString: (showImag: Boolean)(implicit precision: Int)String
	toString(true)(3)                         //> res0: String = sdsd

	def log[A](a: A)(implicit s: Show[A]) = println(s.show(a))
                                                  //> log: [A](a: A)(implicit s: Show[A])Unit
  implicit val stringShow = new Show[String] {
  	def show(s: String) = s
  }                                               //> stringShow  : Show[String] = trail$$anonfun$main$1$$anon$1@57829d67
  log("a string")                                 //> a string
	
}

trait Show[A] {
	def show(f: A): String
}

