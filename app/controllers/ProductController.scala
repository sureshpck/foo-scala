package controllers

import javax.inject._

import models._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import play.filters.csrf.CSRF
import models.Product

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ProductController @Inject()(productService: ProductService, val messagesApi: MessagesApi) extends Controller with I18nSupport{

  val form = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText(minLength = 3, maxLength = 100)
    )(Product.apply)(Product.unapply)
  )

  def create = Action { implicit request =>
    val token = CSRF.getToken.get
    println(token)
    Ok(views.html.product.form(form))
  }

  def postForm = Action { implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.product.form(formWithErrors))
      },
      product => {productService.save(product)
        Redirect(routes.ProductController.create).flashing("success" -> "Product saved!")
      }
    )
  }
}
