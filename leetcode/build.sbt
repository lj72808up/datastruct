lazy val tencent = (project in file("./tencent"))
  .settings(Seq(
    version := "0.1",
    scalaVersion := "2.12.8"
  ):_*)


lazy val root = (project in file("."))
  .settings(
    name := "leetcode",
    version := "0.1",
    scalaVersion := "2.12.8"
  ).aggregate(tencent)
