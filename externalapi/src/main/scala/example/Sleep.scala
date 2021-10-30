package example

import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}


@WebServlet(name = "Sleep", value = Array("/sleep"))
class Sleep extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    println("good night")

    Thread.sleep(5000)

    response.setContentType("text/plain")
    response.getWriter.print("good morning")
    response.flushBuffer()
  }
}
