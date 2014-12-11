/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author autarch
 */
public class StatefulTestServ extends HttpServlet {
	@EJB
	private StatefulTestBeanLocal atEJB;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			StatefulTestBeanLocal tmpBean = (StatefulTestBeanLocal) new InitialContext().lookup("java:app/U8A2-ejb/StatefulTestBean");
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet StatefulTestServ</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet StatefulTestServ at " + request.getContextPath() + "</h1>");
			out.println("<p>@EJB creation date: " + atEJB.getCreationDate() + "</p>");
			out.println("<p>Inside processRequest: " + tmpBean.getCreationDate() + "</p>");
			out.println("<p>Stored in HTTP session: " + getBeanFromHTTPSession(request).getCreationDate() + "</p>");
			out.println("</body>");
			out.println("</html>");
		} catch(NamingException ex) {
			Logger.getLogger(StatefulTestServ.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private StatefulTestBeanLocal getBeanFromHTTPSession(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(true);
		StatefulTestBeanLocal bean = (StatefulTestBeanLocal) httpSession.getAttribute("TESTBEAN");

		if(bean == null) {
			try {
				bean = (StatefulTestBeanLocal) new InitialContext().lookup("java:app/U8A2-ejb/StatefulTestBean");
				httpSession.setAttribute("TESTBEAN", bean);
			} catch(NamingException ex) {
				Logger.getLogger(StatefulTestServ.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return bean;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
