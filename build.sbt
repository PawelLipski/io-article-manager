import play.Project._

name := "IOArticleManager"

version := "1.0"

playScalaSettings

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.30",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "org.apache.commons" % "commons-email" % "1.2",
  "com.typesafe" % "slick_2.10.0-M7" % "0.11.1"
)

libraryDependencies += "com.itextpdf" % "itextpdf" % "5.1.1"

