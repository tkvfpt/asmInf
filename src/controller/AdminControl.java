package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Customers;
import model.DefaultHB;
import model.Products;

/**
 * Servlet implementation class AdminControl
 */
@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action")!=null){
			if(request.getParameter("action").equalsIgnoreCase("login")){
				DefaultHB hb=new DefaultHB("Customers");		
				try{
					hb.beginTransaction();
					List<Products> listP = hb.getEntity("Products");
					hb.commitTransaction();
					if(request.getParameter("username").equalsIgnoreCase("admin") && request.getParameter("password").equalsIgnoreCase("admin")){				
						request.setAttribute("listP", listP);
						request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
					}
					else{
						request.setAttribute("errorMessage", "Login Failed");
						request.getRequestDispatcher("admin.jsp").forward(request, response);
					}
				}finally{
					hb.closeTransaction();
				}
			}else if(request.getParameter("action").equalsIgnoreCase("search")){
				DefaultHB hb=new DefaultHB("Products");
				try{
					hb.beginTransaction();
					if(request.getParameter("product-name").equals("")){
						List<Products> listP = hb.getEntity("Products");
						hb.commitTransaction();
						request.setAttribute("listP", listP);
					}else{
						List<Products> listP = hb.getEntity("Products","where e.name like '%"+request.getParameter("product-name")+"%'");
						hb.commitTransaction();
						request.setAttribute("listP", listP);
					}
					request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
				}finally{
					hb.closeTransaction();
				}
			}
		}else{
				if (!ServletFileUpload.isMultipartContent(request)) {
		            // if not, we stop here
		            PrintWriter writer = response.getWriter();
		            writer.println("Error: Form must has enctype=multipart/form-data.");
		            writer.flush();
		            return;
		        }
				DefaultHB hb=new DefaultHB("Products");
				byte[] b=null;
				String name=null,code=null,action=null;
				float price=0;
				int quantity=0;
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024*1024*3);
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setFileSizeMax(1024*1024*40);
				upload.setSizeMax(1024*1024*50);
				try{
					List<FileItem> formItem = upload.parseRequest(request);
					Iterator<FileItem> items = formItem.iterator();
					if(formItem!=null && formItem.size()>0){
						while(items.hasNext()){
							FileItem item = items.next();
							if(!item.isFormField()){
								b=item.get();
							}else{
								InputStream input = item.getInputStream();
								byte[] str = new byte[input.available()];
								input.read(str);
								if(item.getFieldName().equals("product-name")){
									name = new String(str,"UTF8");
								}else if(item.getFieldName().equals("product-price")){
									price = Float.parseFloat(new String(str,"UTF-8"));
								}else if(item.getFieldName().equals("product-quantity")){
									quantity=Integer.parseInt(new String(str,"UTF-8"));
								}else if(item.getFieldName().equalsIgnoreCase("product-code")){
									code=new String(str,"UTF8");
								}else if(item.getFieldName().equalsIgnoreCase("action")){
									action=new String(str,"UTF8");
								}
							}
						}
					}
					hb.beginTransaction();
					//delete and update object
					List<Products> listP=hb.getEntity("Products", "where e.code like '%"+code+"%'");
					
					if(action.equals("Delete")){
						hb.deleteObject(listP.get(0));
					}else if(action.equals("Update")){
						listP.get(0).setName(name);
						listP.get(0).setPrice(price);
						listP.get(0).setQuantity(quantity);
						if(b.length>0){
							listP.get(0).setImage(b);
						}
					}else if(action.equals("Add")){
						Products product=new Products(code,name,price,b,quantity);
						hb.saveObject(product);
					}
					hb.commitTransaction();
					listP = hb.getEntity("Products");
					request.setAttribute("listP", listP);
					
					request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
					
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					hb.closeTransaction();
				}
			
		}
	}

}
