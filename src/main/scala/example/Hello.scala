package example

import scala.concurrent.ExecutionContext
import org.http4s.server.blaze.BlazeServerBuilder
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.ExitCode

object HelloEndpoints {
  import sttp.tapir._
  import sttp.tapir.json.circe._

  val base = infallibleEndpoint.in("examples")

  val one = base.get.in(query[String]("foo"))
  val two = base.get.in(query[String]("bar"))
}

object Hello extends IOApp {
  import sttp.tapir.docs.openapi._
  import sttp.tapir.openapi.circe.yaml._

  import cats.implicits._

  val endpoints = List(
    HelloEndpoints.one.serverLogicRecoverErrors(foo => IO(println(foo))),
    HelloEndpoints.two.serverLogicRecoverErrors(bar => IO(println(bar)))
  )

  println(
    endpoints
      .map(_.endpoint)
      .toOpenAPI("Title", "version")
      .toYaml
  )

  import org.http4s.implicits._

  import sttp.tapir.server.http4s._

  val server = BlazeServerBuilder[IO](ExecutionContext.global)
    .bindHttp(8080, "0.0.0.0")
    .withHttpApp(endpoints.toRoutes.orNotFound)
    .resource

  def run(args: List[String]): IO[ExitCode] =
    server.use(_ => IO.never)
}
