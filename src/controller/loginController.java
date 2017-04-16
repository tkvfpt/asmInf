package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customers;
import model.DefaultHB;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equalsIgnoreCase("Login")){
			
			DefaultHB hb=new DefaultHB("Customers");
			try{
				hb.beginTransaction();
				Customers customer=(Customers)hb.getSpecificEntity(new Customers(), request.getParameter("username"));
				if(customer!=null){
					if(customer.getPassword().equals(request.getParameter("password"))){
						HttpSession session = request.getSession();
						session.setAttribute("user", customer);
						session.setMaxInactiveInterval(60*60*24);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("error", "Username or Password is wrong");
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				}
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
