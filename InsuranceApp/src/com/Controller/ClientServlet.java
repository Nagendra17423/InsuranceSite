package com.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.UserService.AdminService;
import com.UserService.UserService;
import com.beans.Category;
import com.beans.Policy;
import com.beans.Users;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		session=request.getSession();
		if (id == null) {
			session.setAttribute("cart","0");
			request.setAttribute("msg", " ");
			request.getRequestDispatcher("ClientSide/Login.jsp").forward(request, response);
		} else
			doPost(request, response);

		// 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserService us;
		AdminService as;
		System.out.println("inside clientside");
		String id = request.getParameter("id");
		System.out.println("id "+id);
		if (id.equals("login")) {
			session = request.getSession();
			String name = request.getParameter("name");
			//
			String pass = request.getParameter("password");
			Users s = new Users();
			s.setName(name);
			s.setPassword(pass);
			us = new UserService();
			boolean check = us.validateClientUser(s);
			System.out.println("check " + check+"\n");
			if (check == true) {
				session.setAttribute("name", name);
				
				System.out.println("successfully logined in\nname="+session.getAttribute("name"));
				as=new AdminService();
				List<Category> cat = as.getcat();
				List<Policy> pol = as.getpol();
				request.setAttribute("cat", cat);
				request.setAttribute("pol", pol);
				
				
				
				
				
				request.getRequestDispatcher("ClientSide/Index.jsp").forward(request, response);
			} 
			else {
				System.out.println("unsuccessfull logined in");
				request.setAttribute("msg", "Invalid Credentials");
				request.getRequestDispatcher("ClientSide/Login.jsp").forward(request, response);
			}

		}
		if(id.equals("buy"))
		{
			
		System.out.println("************************************************\n");
		System.out.println("cart :"+(String)request.getAttribute("cart"));
			int tot=Integer.parseInt((String)session.getAttribute("cart"));
			System.out.println("cart :"+tot);
			tot+=1;
			session.setAttribute("cart", new Integer(tot).toString());
			as=new AdminService();
			int pid=Integer.parseInt(request.getParameter("pid"));
			String title=request.getParameter("ptitle");
			String name=(String) session.getAttribute("name");
			
			try {
				as.addpolicytouser(pid,title,name);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			List<Category> cat = as.getcat();
			List<Policy> pol = as.getpol();
			request.setAttribute("cat", cat);
			request.setAttribute("pol", pol);
			
			request.getRequestDispatcher("ClientSide/Index.jsp").forward(request, response);
		}
		
		if(id.equals("delete"))
		{
			int pid=Integer.parseInt(request.getParameter("pid"));
			String title=request.getParameter("ptitle");
//			String name=session.getAttribute("name");
			
			as=new AdminService();
			try {
				as.deleteuserpolicy(pid,title);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<Category> cat = as.getcat();
			List<Policy> pol = as.getpol();
			request.setAttribute("cat", cat);
			request.setAttribute("pol", pol);
			
			request.getRequestDispatcher("ClientSide/Index.jsp").forward(request, response);
			
			
			
		}
		
		
		
	}

}
