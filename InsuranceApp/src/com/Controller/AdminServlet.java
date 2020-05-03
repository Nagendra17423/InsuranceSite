package com.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.UserService.AdminService;
import com.UserService.UserService;
import com.beans.Category;
import com.beans.Policy;
import com.beans.Users;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null) {
			System.out.println("dfjndjdkgjjgkjgjk dfgfjgkjgfkjdghfdhjf");
			request.setAttribute("msg", " ");
			request.getRequestDispatcher("AdminSide/Login.jsp").forward(request, response);

		} else
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		AdminService as = new AdminService();
		if (id.equals("login")) {
			System.out.println("inside login");
			Users u = new Users();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			u.setName(name);
			u.setPassword(password);
			UserService us = new UserService();
			boolean check = us.validate(u);
			if (check == true) {
				System.out.println("passed all case");

				List<Category> cat = as.getcat();
				List<Policy> pol = as.getpol();
				request.setAttribute("cat", cat);
				request.setAttribute("pol", pol);
				request.getRequestDispatcher("AdminSide/Index.jsp").forward(request, response);

			} else {
				request.setAttribute("msg", "Wrong credentials ");
				request.getRequestDispatcher("AdminSide/Login.jsp").forward(request, response);
			}

		}
		
		if(id.equals("addtopolicy"))
		{
			//link href add policy
			
			int cid=Integer.parseInt(request.getParameter("cid"));
			System.out.println("id "+cid);
			request.setAttribute("catid", cid);
			request.getRequestDispatcher("AdminSide/InsurancEdit.jsp").forward(request, response);
		}
		
		
		if(id.equals("editPolicy"))
		{
			//from tkeditor 2 phase
			
			String title=request.getParameter("title");
			String description=request.getParameter("description");
			int cid=Integer.parseInt(request.getParameter("cid"));
			String ogtitle=request.getParameter("ptitle");
			System.out.println("step 1 of edit\n title="+title+" description="+description+" cid="+cid+" original title "+ogtitle);
			try {
				as.updatePolicy(title,description,cid,ogtitle);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Category> cat = as.getcat();
			List<Policy> pol = as.getpol();
			request.setAttribute("cat", cat);
			request.setAttribute("pol", pol);
			request.getRequestDispatcher("AdminSide/Index.jsp").forward(request, response);
			
			
			
			
		}
		
		if (id.equals("edit"))
		{
			// edit 1 phase
			int cid=Integer.parseInt(request.getParameter("cid"));
			String ptitle=request.getParameter("ptitle");
			System.out.println("id "+cid);
			request.setAttribute("catid", cid);
			request.setAttribute("ptitle", ptitle);
			request.getRequestDispatcher("AdminSide/edit.jsp").forward(request, response);
		}
		if (id.equals("delete")) {

			// Policy policy=(Policy) request.getAttribute("p");
			System.out.println("dkjfkjdsfklsfjksdfksfkjsdfjksjfk");
			// Policy p=Class.forName(request.getParameter("p"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			String title = (String) request.getParameter("ptitle");

			System.out.println("pid=" + pid + "ptitle" + title);
//			     Policy p=request.getParameter("p");
//System.out.println();
			as.delete(title, Integer.toString(pid));
			List<Category> cat = as.getcat();
			List<Policy> pol = as.getpol();
			request.setAttribute("cat", cat);
			request.setAttribute("pol", pol);
			System.out.println("\n DELETE ^ STUFF\n");
			request.getRequestDispatcher("AdminSide/Index.jsp").forward(request, response);

		}
		
		if(id.equals("addPolicyDetails"))
		{
			String title=request.getParameter("title");
			String description=request.getParameter("description");
			System.out.println(" title "+title+" description "+description);
			int catid=Integer.parseInt(request.getParameter("catid"));
			System.out.println("id "+catid);
			try {
				as.addpolicy(title,description,catid);
			}
			catch (ClassNotFoundException | SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("succesfully added policy to the table ");
			
			List<Category> cat = as.getcat();
			List<Policy> pol = as.getpol();
			request.setAttribute("cat", cat);
			request.setAttribute("pol", pol);
			System.out.println("\n DELETE ^ STUFF\n");
			request.getRequestDispatcher("AdminSide/Index.jsp").forward(request, response);
			
		}
		
		
		
		
		if (id.equals("showPolicyHolders")) {
			int pid=Integer.parseInt(request.getParameter("pid"));
			String title=request.getParameter("ptitle");
			
			System.out.println("\npid:"+pid+"\t and title:"+title);
			
			try {
				List<Users> user=as.showPolicyHolders(pid,title);
				request.setAttribute("user", user);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			List<Category> cat=as.getcat();
//			List<Policy> pol=as.getpol();
		System.out.println("\ncame back from dbconn for showing name\n ");
			request.setAttribute("title", title);
			request.setAttribute("pid",pid);
			
			request.getRequestDispatcher("AdminSide/showholders.jsp").forward(request,response);
			
			

		}

	}

}
