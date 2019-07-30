name := "Demo"

val hadoopCommon= "org.apache.hadoop" % "hadoop-common" % "2.7.5"

lazy val commonSettings = Seq(
  version := "0.1",
  scalaVersion := "2.12.8"
)

lazy val root = (project in file("."))
  .settings(commonSettings:_*)
  .settings(
    name := "root",
  ) //.dependsOn(sub2)