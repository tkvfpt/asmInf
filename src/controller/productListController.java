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

import model.DefaultHB;
import model.Products;

/**
 * Servlet implementation class productListController
 */
@WebServlet("/productListController")
public class productListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List listCart=new ArrayList();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productListController() {
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
				product.setQuantity(1);
				if(request.getSession().getAttribute("listCart")!=null){
					listCart =(List) request.getSession().getAttribute("listCart"); 
				}
				listCart.add(product);
				HttpSession session=request.getSession();
				session.setAttribute("listCart", listCart);
				request.getRequestDispatcher("productList.jsp").forward(request, response);
			}finally{
				hb.closeTransaction();
			}
		}else if(request.getParameter("action").equalsIgnoreCase("page")){
			DefaultHB hb = new DefaultHB("Products");
			List<Products> listP;
				try{
				hb.beginTransaction();
				 listP=hb.getEntity("Products",((Integer.parseInt(request.getParameter("count"))-1)*12)+1,Integer.parseInt(request.getParameter("count"))*12);
				 request.setAttribute("listP", listP);
				 request.getRequestDispatcher("productList.jsp").forward(request, response);
				hb.commitTransaction();
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
