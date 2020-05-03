package com.Dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.Category;
import com.beans.Policy;
import com.beans.Users;

public class Dbcon {
	String name="postgres";
	String password="NAGENDRA1@1999";
	String url="jdbc:postgresql://localhost:5432/javaproj2";
	String  driver="org.postgresql.Driver";
	Connection conn=null;

	public List<Category> getcat() throws ClassNotFoundException, SQLException 
	{
		dbconnect();
		String q1="select * from category order by cid asc";
		PreparedStatement pstmt=conn.prepareStatement(q1);
		ResultSet rs=pstmt.executeQuery();
		List<Category> cat=new ArrayList<>();
		while(rs.next())
		{
			Category p=new Category();
			p.setId(Integer.parseInt(rs.getString("cid")));
			p.settitle(rs.getString("ctitle"));
//			System.out.println("catgeories title "+p.gettitle());
			cat.add(p);
			
		}
		 rs.close();
		 pstmt.close();
			System.out.println("exit from db  service of category table ");
		return cat;
	}

	private void dbconnect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		 conn= DriverManager.getConnection(url,name,password);
		 if(conn==null)
			 System.out.println("null");
		 else
			 System.out.println("sucessfully connected ");
	}

	public List<Policy> getpol() throws ClassNotFoundException, SQLException {
		dbconnect();
		String q1="select * from policy order by id asc";
		PreparedStatement pstmt=conn.prepareStatement(q1);
		ResultSet rs=pstmt.executeQuery();
		List<Policy> cat=new ArrayList<>();
		while(rs.next())
		{
			Policy p=new Policy();
			p.setId(Integer.parseInt(rs.getString("id")));
			p.setTitle(rs.getString("title"));
			p.setDiscription(rs.getString("context"));
			cat.add(p);
			
		}
		 rs.close();
		 pstmt.close();
			System.out.println("exit from db  service");
		return cat;

		// TODO Auto-generated method stub
		
	}

	public void delete(String ptitle, String pid) throws ClassNotFoundException, SQLException {
		
		dbconnect();
		System.out.println("inside dbconnection.java class \npid="+pid+"\tptitle="+ptitle);
		String q1="delete from policy  where id=? and title=? ";
		
		PreparedStatement pstmt=conn.prepareStatement(q1);
		pstmt.setInt(1, Integer.parseInt(pid));
		pstmt.setString(2,ptitle);
		pstmt.executeUpdate();
		System.out.println("********************deleted  succeffuly **********************");
	}

	public List<Users> showPolicyHolders(int id, String title) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		dbconnect();
		System.out.println("inside dbconn for showing holders \n");
		String q="select name from insurance_holder where id=? and title=?  order by id";
		PreparedStatement pstmt=conn.prepareStatement(q);
		pstmt.setInt(1, id);
		pstmt.setString(2,title);
		ResultSet rs=pstmt.executeQuery();
		List <Users> u=new ArrayList<>();
		
		while(rs.next())
		{
			Users u1=new Users();
			u1.setName(rs.getString("name"));
			u.add(u1);
		}
		
		return u;
	}

	public void addpolicy(String title, String description,int id) throws ClassNotFoundException, SQLException {
		dbconnect();
		System.out.println("inside add policy \n");
		String q="insert into policy values(?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(q);
		pstmt.setInt(1, id);
		pstmt.setString(2,title);
		pstmt.setString(3, description);
		pstmt.executeUpdate();
		System.out.println("complete adding policy into category \n");
		// TODO Auto-generated method stub
		
	}

	public void updatePolicy(String title, String description, int cid,String ogtitle) throws ClassNotFoundException, SQLException {
		dbconnect();
		
		System.out.println("step 3 of edit\n");
		String q="update policy  set title=?, context=? where title=? AND id=?";
		
		PreparedStatement pstmt=conn.prepareStatement(q);
		pstmt.setString(1, title);
		pstmt.setString(2, description);
		pstmt.setString(3, ogtitle);
		pstmt.setInt(4, cid);
		
		int rowsAffected =pstmt.executeUpdate();
		System.out.println("title "+ogtitle+"id "+cid+" no of row affeted \n "+rowsAffected);
		System.out.println("complete updating policy table \ncompleted");
		
		// TODO Auto-generated method stub
		
	}

	public void addpolicytouser(int pid,String title,String name) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		dbconnect();
		String q="insert into insurance_holder values(?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(q);
		pstmt.setInt(1, pid);
		pstmt.setString(2, title);
		pstmt.setString(3, name);
		pstmt.executeUpdate();
		System.out.println("succefully added policy to customers\n");
		
	}

	public void deleteuserpolicy(int pid, String title) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		dbconnect();
		String q="delete from insurance_holder where id=? and title=?";
		PreparedStatement pstmt=conn.prepareStatement(q);
		pstmt.setInt(1, pid);
		pstmt.setString(2, title);
		pstmt.executeUpdate();
		System.out.println("succefully deleted policy to customers\n");
		
	}



}
