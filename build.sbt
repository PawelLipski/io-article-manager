import play.Project._

name := "IOArticleManager"

version := "1.0"

playScalaSettings

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.30",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4"
)

libraryDependencies += "com.itextpdf" % "itextpdf" % "5.1.1"

