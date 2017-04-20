import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

public class LoginServletSecure extends HttpServlet// implements HttpSessionListener
{

	public void sessionCreated(HttpSessionEvent event)
	{
		/*HttpSession session = event.getSession();
		if(session.isNew())
		{
			session.setAttribute("loginCount", 0);
		}*/
	}
	public void sessionDestroyed(HttpSessionEvent event)
	{}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		if (password.equals("Gmail@123"))
		{
			out.print("Welcome, " + name);
			session.setAttribute("name", name);
		}
		else
		{
			if(session.isNew())
			{
				int loginCount = 1;
				session.setAttribute("loginCount", loginCount);
				out.print("Sorry, username or password error! ");
				request.getRequestDispatcher("login.html").include(request, response);
			}
			else if ((Integer)session.getAttribute("loginCount")>3)
			{
				long lastAccessedTime = session.getLastAccessedTime();
				Date date = new Date();
				long currentTime = date.getTime();
				long timeDiff = currentTime - lastAccessedTime;
				if (timeDiff>=120000)
				{
					session.invalidate();
					request.getRequestDispatcher("login.html").include(request, response);
				}
				else
				{
					out.print("Barred from entering data anymore.");
				}
			}
			else
			{
				int loginCount = (Integer) session.getAttribute("loginCount");
				loginCount++;
				session.setAttribute("loginCount", loginCount);

				out.print("<h1>WARNING</h1>too many username or password error! " + loginCount);
				request.getRequestDispatcher("login.html").include(request, response);	
			}
		}

		out.close();
	}
}