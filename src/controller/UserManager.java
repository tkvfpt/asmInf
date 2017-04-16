package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customers;
import model.DefaultHB;
import model.Products;

/**
 * Servlet implementation class UserManager
 */

public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equalsIgnoreCase("search")){
			DefaultHB hb=new DefaultHB("Customers");		
			try{
				hb.beginTransaction();
				if(request.getParameter("user-name").equals("")){
					List<Customers> listC = hb.getEntity("Customers");
					hb.commitTransaction();
					request.setAttribute("listC", listC);
				}else{
					List<Customers> listC = hb.getEntity("Customers","where e.name like '%"+request.getParameter("user-name")+"%'");
					hb.commitTransaction();
					request.setAttribute("listC", listC);
				}
				request.getRequestDispatcher("adminUser.jsp").forward(request, response);
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
