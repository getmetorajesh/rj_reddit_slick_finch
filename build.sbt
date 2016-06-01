name := """rj_reddit_slick_finch"""

version := "1.0"

scalaVersion := "2.11.7"

//lazy val root = (project in file(".")).enablePlugins(sbt-resolver)

addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.1")
// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "2.2.4" % "test",
	"com.typesafe.slick" %% "slick" % "3.1.1",
  	"org.slf4j" % "slf4j-nop" % "1.6.4",
  	"com.h2database" % "h2" % "1.4.187",
	"com.typesafe.slick" %% "slick-codegen" % "3.1.1",
	"com.github.finagle" %% "finch-core" % "0.10.0",
	"com.github.finagle" %% "finch-circe" % "0.10.0",
	 "io.circe" %% "circe-generic" % "0.3.0"
	)

// Uncomment to use Akka
//libraryDependencies +=


resolvers ++= Seq(
  Resolver.defaultLocal,
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  Resolver.typesafeRepo("releases"),
  Resolver.typesafeRepo("snapshots"),
  Resolver.sonatypeRepo("snapshots"),
  "spray repo" at "http://repo.spray.io",
  "jgit-repo" at "http://download.eclipse.org/jgit/maven"
)