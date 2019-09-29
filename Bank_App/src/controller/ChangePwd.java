package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class ChangePwd extends HttpServlet 
{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String npwd=request.getParameter("npwd");
		HttpSession session = request.getSession();
		String accno=(String) session.getAttribute("accno");
		Model m=new Model();
		m.setAccno(accno);
		m.setPwd(npwd);
		boolean status=m.changePwd();
		if (status==true)
		{
			response.sendRedirect("/Bank_App/changePwdSuccess.jsp");
		}
		else
		{
			response.sendRedirect("/Bank_App/changePwdFail.jsp");
			
		}
	}

	
}
