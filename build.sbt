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
  "com.typesafe.slick" % "slick_2.10" % "2.0.1-RC1",
  "commons-dbcp" % "commons-dbcp" % "1.2.2",
  "com.itextpdf" % "itextpdf" % "5.1.1"
)
