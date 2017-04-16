package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customers;
import model.DefaultHB;
import model.Products;

/**
 * Servlet implementation class indexController
 */
@WebServlet("/indexController")
public class indexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Products> listCart=new ArrayList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equalsIgnoreCase("AddToCart")){
			DefaultHB hb=new DefaultHB("Products");
			try{
				hb.beginTransaction();
				Products product=(Products)hb.getSpecificEntity(new Products(), request.getParameter("product-code"));
				
				if(request.getSession().getAttribute("listCart")!=null){
					listCart =(List<Products>) request.getSession().getAttribute("listCart");
					if(listCart.indexOf(product)<0){
						product.setQuantity(1);
						listCart.add(product);
					}else{
						listCart.get(listCart.indexOf(product)).setQuantity(listCart.get(listCart.indexOf(product)).getQuantity()+1);
					}
					
				}else {
					listCart.add(product);
				}
				
				
				HttpSession session=request.getSession();
				session.setAttribute("listCart", listCart);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}finally{
				hb.closeTransaction();
			}
		}else if(request.getParameter("action").equalsIgnoreCase("logout")){
			request.getSession().setAttribute("user", null);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
