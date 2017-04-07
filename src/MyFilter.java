import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.http.*;
import javax.servlet.*;

public class MyFilter implements Filter
{
	public void init(FilterConfig conf) throws ServletException
	{}
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
	{
		PrintWriter out = resp.getWriter();
		out.print("<h1> Filter is invoked here. </h1>");

		chain.doFilter(req, resp);

		out.print("Filter is invoked after");
	}
	public void destroy(){}
}