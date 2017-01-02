package models

import javax.inject.Inject

import play.api.db.DBApi
import anorm._
import anorm.SqlParser.{int, str}

@javax.inject.Singleton
class ProductService @Inject() (dbApi: DBApi) {

  private val DB = dbApi.database("default")

  val defaultParser = int("id") ~ str("name") map {
    case id ~ name  => Product(id, name)
  }

  def save(product: Product) = {
    println("Product saved!!")
    DB.withConnection { implicit c =>
      SQL("INSERT INTO Products(id, name) VALUES ({id}, {name});")
        .on('id -> product.id, 'name -> product.name).executeInsert()
    }
  }


  def update(product: Product) = {


    DB.withConnection { implicit c =>
      SQL("UPDATE Products SET name = {name} WHERE id = {id}")
        .on('name -> product.name, 'id -> product.id).executeUpdate()
    }
  }


  def delete(id: Long) = {
    DB.withConnection { implicit c =>
      SQL("DELETE FROM Products WHERE id={id};")
        .on('id -> id).executeUpdate()
    }
  }


  def get(id: Long) = {
    DB.withConnection { implicit c =>
      SQL("SELECT * FROM Products WHERE id={id};")
        .on('id -> id).executeQuery().singleOpt(defaultParser)
    }
  }

  def all = {
    DB.withConnection { implicit c =>
      SQL("SELECT * FROM Products;").executeQuery().list(defaultParser)
    }
  }
}

