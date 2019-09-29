package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class Balance extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		String accno=(String)session.getAttribute("accno");
		Model m=new Model();
		m.setAccno(accno);
		boolean status=m.checkBalance();
		if (status==true)
		{
			String balance=m.getBalance();
			session.setAttribute("balance", balance);
			response.sendRedirect("/Bank_App/balance.jsp");
		}
		else
		{
			response.sendRedirect("/Bank_App/balanceFail.jsp");
		}
		
	}
}
