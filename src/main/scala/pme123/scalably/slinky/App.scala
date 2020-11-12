package pme123.scalably.slinky

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import typings.plotlyJs.anon.{
  PartialPlotDataAutobinx,
  PartialPlotMarkerAutocolorscale
}
import typings.plotlyJs.mod.{Data, PlotType}
import typings.plotlyJs.plotlyJsStrings
import typings.reactPlotlyJs.anon.PartialLayout
import typings.reactPlotlyJs.components.ReactPlotlyDotjs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@react class App extends StatelessComponent {
  type Props = Unit

  private val css = AppCSS

  def render() = {
    div(className := "App")(
      header(className := "App-header")(
        img(
          src := ReactLogo.asInstanceOf[String],
          className := "App-logo",
          alt := "logo"
        ),
        h1(className := "App-title")("Welcome to React (with Scala.js!)")
      ),
      plots.MyComponent(())
    )
  }
}

object plots {
  val MyComponent = FunctionalComponent[Unit] { _ =>
          val data = js.Array[Data](
        PartialPlotDataAutobinx()
          .setXVarargs(1, 2, 3)
          .setYVarargs(2, 6, 3)
          .setType(PlotType.scatter)
          .setMode(plotlyJsStrings.linesPlussignmarkers)
          .setMarker(
            PartialPlotMarkerAutocolorscale()
              .setColor("red")
          ),
        PartialPlotDataAutobinx()
          .setType(PlotType.bar)
          .setXVarargs(1, 2, 3)
          .setYVarargs(2, 5, 3)
      )

      ReactPlotlyDotjs(data = data, layout = PartialLayout().setWidth(0.8).setHeight(400).setTitle("A Fancy Plot")).debug(true)
  }
}
