import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;

public class ProfileServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);

		HttpSession session = request.getSession(false);
		if (session!=null)
			out.print("Hello " + ((String)session.getAttribute("name")) + ",\n Welcome to the profile page");
		else
		{
			out.print("Please login first");
			request.getRequestDispatcher("login.html").include(request, response);
		}

		out.close();
	}
}