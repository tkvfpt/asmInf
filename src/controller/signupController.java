package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customers;
import model.DefaultHB;

/**
 * Servlet implementation class signupController
 */
@WebServlet("/signupController")
public class signupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equalsIgnoreCase("signup")){
			DefaultHB hb = new DefaultHB("Customers");
			try{
				hb.beginTransaction();
				Customers customer = new Customers(request.getParameter("username"),request.getParameter("password"),request.getParameter("fullname"),request.getParameter("gender"),request.getParameter("email"),"user",request.getParameter("phone"));
				hb.saveObject(customer);
				hb.commitTransaction();
				response.getWriter().println("Sign up successfully <br/><a href='Login.jsp'>Back to Login</a>");
			}finally{
				hb.closeTransaction();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
