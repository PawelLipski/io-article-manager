import play.Project._

name := "IOArticleManager"

version := "1.0"

playScalaSettings

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.30"
)
