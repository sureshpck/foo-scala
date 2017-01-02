import com.google.common.collect.ImmutableMap
import models.ProductService
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.db.Databases
import play.db.Database

class ProductSpec extends PlaySpec with OneAppPerSuite {

  var productService: ProductService = app.injector.instanceOf(classOf[ProductService])



  Databases.withDatabase(
    driver = "com.mysql.jdbc.Driver",
    url = "jdbc:mysql://localhost/playtest",
    config = Map(
      "user" -> "play",
      "password" -> "demo"
    )
  ) { database =>
    import play.api.db.evolutions._
    Evolutions.applyEvolutions(database)

    "Product" should {
      "be retrieved by Id" in {
        val product = productService.get(23)

        product.get.name must equal("test")
      }
    }
  }

}