package vn.iotstar.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/hi", "/home"})

public class HomeController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String username = req.getParameter("ten");
//		String lastname = req.getParameter("ho");
//		PrintWriter pw = resp.getWriter();
//		pw.print("Hello " + " " +  lastname + " " + username);
//		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("ten");
		String lastname = req.getParameter("ho");
		PrintWriter pw = resp.getWriter();
		pw.print("Hello " + " " +  lastname + " " + username);
		pw.close();
	}
	

}
