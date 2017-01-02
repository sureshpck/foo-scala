package controllers

import javax.inject._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hello = Action {
    Ok("Hello World")
  }

  def echo(message: String) = Action {
    Ok(s"Echoing $message")
  }

  private val productMap = Map(1 -> "Keyboard", 2 -> "Mouse", 3 -> "Monitor")

  def listProducts() = Action {
    Ok(views.html.products(productMap.values.toSeq))
  }

  def listProductsAsTXT() = Action {
    Ok(views.txt.products(productMap))
  }

  def listProductsAsXML() = Action {
    Ok(views.xml.products(productMap))
  }
}
