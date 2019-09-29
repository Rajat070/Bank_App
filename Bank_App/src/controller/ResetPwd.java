package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class ResetPwd extends HttpServlet 
{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		String email=(String) session.getAttribute("email");
		String npwd=request.getParameter("npwd");
		Model m=new Model();
		m.setEmail(email);
		m.setPwd(npwd);
		boolean status=m.resetPwd();
		if (status==true)
		{
			response.sendRedirect("/Bank_App/resetPwdSuccess.jsp");
		}
		else
		{
			response.sendRedirect("/Bank_App/resetPwdFail.jsp");
			
		}
	}
}

