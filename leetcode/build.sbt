lazy val tencent = (project in file("./tencent"))
  .settings(Seq(
    version := "0.1",
    scalaVersion := "2.12.8"
  ):_*)

lazy val top150 = (project in file("./top150"))
  .settings(Seq(
    version := "0.1",
    scalaVersion := "2.12.8"
  ):_*)

lazy val math = (project in file("./math"))
  .settings(Seq(
    version := "0.1",
    scalaVersion := "2.12.8"
  ):_*)

lazy val byteCodeDance = (project in file("./byteCodeDance"))
  .settings(Seq(
    version := "0.1",
    scalaVersion := "2.12.8"
  ):_*)


lazy val root = (project in file("."))
  .settings(
    name := "leetcode",
    version := "0.1",
    scalaVersion := "2.12.8"
  ).aggregate(tencent,top150)
