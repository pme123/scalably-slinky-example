package pme123.scalably.slinky

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks
import slinky.core.facade.Hooks._
import slinky.core.facade.ReactElement
import slinky.web.html._
import typings.antDesignIcons.components.AntdIcon
import typings.antDesignIconsSvg.downOutlinedMod.{default => DownOutlinedIcon}
import typings.antDesignIconsSvg.downloadOutlinedMod.{
  default => DownloadOutlinedIcon
}
import typings.antDesignIconsSvg.homeOutlinedMod.{default => HomeOutlinedIcon}
import typings.antDesignIconsSvg.lockTwoToneMod.{default => LockTwoToneIcon}
import typings.antDesignIconsSvg.mailTwoToneMod.{default => MailTwoToneIcon}
import typings.antDesignIconsSvg.shopOutlinedMod.{default => ShopOutlinedIcon}
import typings.antDesignIconsSvg.userOutlinedMod.{default => UserOutlinedIcon}
import typings.antd.antdStrings
import typings.antd.components._
import typings.antd.components.{List => AntList}
import typings.antd.notificationMod.ArgsProps
import typings.antd.notificationMod.IconType
import typings.antd.notificationMod.{default => Notification}
import typings.antd.tableInterfaceMod.ColumnGroupType
import typings.antd.tableInterfaceMod.ColumnType
import typings.plotlyJs.anon.PartialPlotDataAutobinx
import typings.plotlyJs.anon.PartialPlotMarkerAutocolorscale
import typings.plotlyJs.mod.Data
import typings.plotlyJs.mod.PlotType
import typings.plotlyJs.plotlyJsStrings
import typings.rcSelect.interfaceMod.OptionData
import typings.react.mod.CSSProperties
import typings.reactPlotlyJs.anon.PartialLayout
import typings.reactPlotlyJs.components.ReactPlotlyDotjs
import typings.std.global.console

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object AntCSS extends js.Any

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@react object App {
  type Props = Unit

  private val css = AppCSS
  private val antCss = AntCSS

  val component = FunctionalComponent[Props] { _ =>
    val (isModalVisible, updateIsModalVisible) = Hooks.useState(false)

    val renderModal = section(
      br(),
      h2("Ant Design with Plotly Example"),
      Button.onClick(_ => updateIsModalVisible(true))("Open Plotly diagram"),
      Modal
        .width("800px")
        .visible(isModalVisible)
        .title("Voilà the Plotly Diagram")
        .onCancel(_ => updateIsModalVisible(false))
        .onOk(_ => updateIsModalVisible(false))(
          plots.MyComponent(())
        )
    )

    Layout(className := "App")(
      Layout.Header(className := "App-header")(
        img(
          src := ReactLogo.asInstanceOf[String],
          className := "App-logo",
          alt := "logo"
        ),
        h1(className := "App-title")("Welcome to React (with Scala.js!)")
      ),
      Layout.Content(style := js.Dynamic.literal(
        `background` = "white")
        )(
        renderModal,
        br(),
        p("See here for more information:"),
        p(a(href := "https://ant.design")("Ant Design")),
        p(a(href := "https://github.com/plotly/plotly.js/")("Plotly"))
      ),
      Layout.Footer(
        "Check it out on Github: ",
        a(href := "https://github.com/pme123/scalably-slinky-example")(
          "scalably-slinky-example"
        )
      )
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

    ReactPlotlyDotjs(
      data = data,
      layout =
        PartialLayout().setWidth(0.8).setHeight(400).setTitle("A Fancy Plot")
    ).debug(true)
  }
}
