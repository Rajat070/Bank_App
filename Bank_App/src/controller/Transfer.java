package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class Transfer extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String tamt=request.getParameter("tamt");
		HttpSession session = request.getSession();
		String accno=(String) session.getAttribute("accno");
		Model m=new Model();
		m.setAccno(accno);
		boolean status=m.transfer(tamt);
		if (status==true)
		{
			System.out.println("Transfer Successfully");
			response.sendRedirect("/Bank_App/transferSuccess.jsp");
		}
		else
		{
			System.out.println("Transfer Failed");
			response.sendRedirect("/Bank_App/transferFail.jsp");
			
		}
	}
}
