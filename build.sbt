lazy val root = project
  .in(file("."))
  .enablePlugins(
    ScalablyTypedConverterPlugin,
    ScalaJSBundlerPlugin,
    ScalaJSPlugin
  )
  .configure(
    projectSettings,
    slinkyBasics,
    webpackSettings,
    plotlyExample,
    antdSettings
  )

lazy val projectSettings: Project => Project =
  _.settings(
    name := "scalably-slinky-example",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.2",
    webpackDevServerPort := 8023,
  )

lazy val slinkyBasics: Project => Project =
  _.settings(
    scalacOptions += "-Ymacro-annotations",
    requireJsDomEnv in Test := true,
    addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS"),
    addCommandAlias("build", "fullOptJS::webpack"),
    libraryDependencies ++= Seq(
      "me.shadaj" %%% "slinky-web" % "0.6.5",
      "me.shadaj" %%% "slinky-hot" % "0.6.5"
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % "3.1.1" % Test
    ),
    Compile / npmDependencies ++= Seq(
      "react" -> "16.13.1",
      "react-dom" -> "16.13.1",
      "react-proxy" -> "1.1.8"
    ),
    Compile / npmDevDependencies ++= Seq(
      "file-loader" -> "6.0.0",
      "style-loader" -> "1.2.1",
      "css-loader" -> "3.5.3",
      "html-webpack-plugin" -> "4.3.0",
      "copy-webpack-plugin" -> "5.1.1",
      "webpack-merge" -> "4.2.2"
    )
  )

lazy val plotlyExample: Project => Project =
  _.settings(
    stFlavour := Flavour.Slinky,
    useYarn := true,
    stIgnore := List("react-proxy"),
     Compile / npmDependencies  ++= Seq(
      "plotly.js" -> "1.57.1",
      "react-plotly.js" -> "2.5.0",
      "@types/react-plotly.js" -> "2.2.4",
      "@types/react" -> "16.9.42",
      "@types/react-dom" -> "16.9.8"
    )
  )

lazy val webpackSettings: Project => Project =
  _.settings(
    version in webpack := "4.43.0",
    version in startWebpackDevServer := "3.11.0",
    webpackResources := baseDirectory.value / "webpack" * "*",
    webpackConfigFile in fastOptJS := Some(
      baseDirectory.value / "webpack" / "webpack-fastopt.config.js"
    ),
    webpackConfigFile in fullOptJS := Some(
      baseDirectory.value / "webpack" / "webpack-opt.config.js"
    ),
    webpackConfigFile in Test := Some(
      baseDirectory.value / "webpack" / "webpack-core.config.js"
    ),
    webpackDevServerExtraArgs in fastOptJS := Seq("--inline", "--hot"),
    webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly()
  )

lazy val antdSettings: Project => Project =
  _.settings(
    Compile / npmDependencies ++= Seq(
      "antd" -> "4.8.2"
    )
  )
