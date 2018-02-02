name := "RoamingProject"

version := "1.0"

scalaVersion := "2.10.6"
val sparkVersion = "2.1.1"

dependencyOverrides ++= Set(
  "io.netty" % "netty" % "3.9.9.Final",
  "commons-net" % "commons-net" % "2.2",
  "com.google.guava" % "guava" % "11.0.2"
)
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-mllib" % sparkVersion
libraryDependencies += "com.holdenkarau" %% "spark-testing-base" % "2.1.1_0.8.0" % "test"
libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion % "test"

