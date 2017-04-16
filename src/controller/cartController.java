package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Customers;
import model.DefaultHB;
import model.Products;

/**
 * Servlet implementation class cartController
 */
@WebServlet("/cartController")
public class cartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equalsIgnoreCase("remove")){
			DefaultHB hb = new DefaultHB("Products");
			List<Products> list = (List<Products>)request.getSession().getAttribute("listCart");
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getCode().equals(request.getParameter("code"))){
					list.remove(i);
					break;
				}
			}
			request.getSession().setAttribute("listCart", list);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}else if(request.getParameter("action").equals("Checkout")){
			DefaultHB hb = new DefaultHB("Cart");
			try{
				hb.beginTransaction();
				Customers cust=(Customers)request.getSession().getAttribute("user");
				Cart cart = new Cart(1,cust.getUsername(),Float.parseFloat(request.getParameter("total")));
				hb.saveObject(cart);
				hb.commitTransaction();
				response.getWriter().println("<a href='index.jsp'>Continue to shop</a>");
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
