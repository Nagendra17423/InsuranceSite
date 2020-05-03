package com.UserService;

import java.sql.SQLException;
import java.util.List;

import com.Dbconfig.Dbcon;
import com.beans.Category;
import com.beans.Policy;
import com.beans.Users;

public class AdminService {
	private Dbcon conn;

	public List<Category> getcat() {
		conn = new Dbcon();
		try {
			return conn.getcat();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public List<Policy> getpol() {

		conn = new Dbcon();
		try {
			return conn.getpol();
		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		

	}

	public void delete(String ptitle, String pid) {
		conn = new Dbcon();
		try {
			System.out.println("inside admin service.java\n");
			conn.delete(ptitle,pid);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Users> showPolicyHolders(int id, String title) throws ClassNotFoundException, SQLException {
		conn=new Dbcon();
		return conn.showPolicyHolders(id, title);
		
	}

	public void addpolicy(String title, String description,int id) throws ClassNotFoundException, SQLException {
		conn=new Dbcon();
		conn.addpolicy(title,description,id);
		// TODO Auto-generated method stub
		
	}

	public void updatePolicy(String title, String description, int cid,String ogtitle) throws ClassNotFoundException, SQLException {
		conn=new Dbcon();
		System.out.println("step 2 of edit\n");
		conn.updatePolicy(title,description,cid, ogtitle);
		
	}

	public void addpolicytouser(int pid,String title,String name) throws ClassNotFoundException, SQLException 
		{
			conn=new Dbcon();
			conn.addpolicytouser(pid,title,name);
		}

	public void deleteuserpolicy(int pid, String title) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		conn=new Dbcon();
		conn.deleteuserpolicy(pid,title);
		
	}


}
