package example

import scalaj.http.{Http, HttpOptions, HttpRequest}

import java.io.FileOutputStream
import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}


@WebServlet(name = "Demo", value = Array("/demo"))
class Demo extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    val t1 = System.nanoTime

    Demo.fib(45)

    val t2 = System.nanoTime
    println("fib", (t2 - t1) / 1e9d)

    Demo.buildString(5000000)

    val t3 = System.nanoTime
    println("buildString", (t3 - t2) / 1e9d)

    Demo.alloc(20000, 10000)

    val t4 = System.nanoTime
    println("alloc", (t4 - t3) / 1e9d)

    Demo.callExternalAPI()

    val t5 = System.nanoTime
    println("callExternalAPI", (t5 - t4) / 1e9d)

    println("total", (t5 - t1) / 1e9d)

    response.setContentType("text/plain")
    response.getWriter.print("done")
    response.flushBuffer()
  }
}

object Demo {
  def fib(n: Int): Int = {
    if (n < 3) 1;
    else fib(n - 1) + fib(n - 2);
  }

  def buildString(n: Int): Unit = {
    val devNull = new FileOutputStream("/dev/null")
    for (i <- 0 until n) {
      val s = "a" + i.toString
      devNull.write(s.getBytes())
    }
  }

  def alloc(n: Int, m: Int): Unit = {
    import java.io.FileOutputStream
    val devNull = new FileOutputStream("/dev/null")
    for (_ <- 0 until n) {
      val arr = (0 until m).toArray
      devNull.write(arr.map(_.toByte))
    }
  }

  def callExternalAPI(): Unit = {
    val request: HttpRequest = Http("http://externalapi:9091/sleep")
      .options(HttpOptions.connTimeout(10000), HttpOptions.readTimeout(20000))
    val response = request.asString
    println(response.code, response.body)
  }
}
