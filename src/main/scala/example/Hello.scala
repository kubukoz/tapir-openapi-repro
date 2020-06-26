package example

object HelloEndpoints {
  import sttp.tapir._
  import sttp.tapir.json.circe._

  val base = infallibleEndpoint.in("examples")

  val one = base.get.in(query[String]("foo"))
  val two = base.get.in(query[String]("bar"))
}

object Hello extends App {
  import sttp.tapir.docs.openapi._
  import sttp.tapir.openapi.circe.yaml._

  println(
    List(HelloEndpoints.one, HelloEndpoints.two)
      .toOpenAPI("Title", "version")
      .toYaml
  )
}
