package com.UserService;

import java.util.HashMap;
import java.util.Map;

import com.Dbconfig.Dbcon;
import com.beans.Users;

public class UserService {
private Map<String ,String> user;
	public boolean validate(Users u) {
	populate();
	if(user.keySet().contains(u.getName()))
	{
		String pass=user.get(u.getName());
		if(pass.equals(u.getPassword()))
				return true;
	}
	
		return false;
	}
	private void populate() {
		user=new HashMap<String, String>();
		user.put("nags", "123");
		user.put("nags1", "1234");
		user.put("nags2", "1234");
		
		
	}
	private Map<String,String>  clientuser;
	public boolean validateClientUser(Users s) {
		populateclientuser();
		if(clientuser.keySet().contains(s.getName()))
		{
			String pass=s.getPassword();
			if(pass.equals(clientuser.get(s.getName())))
				return true;
		}
		return false;
		// TODO Auto-generated method stub
		
	}
	public void populateclientuser()
	{
		clientuser=new HashMap<>();
		clientuser.put("nagendra","123");
		clientuser.put("sonu","123");
		clientuser.put("sarita","123");
		clientuser.put("sanjay","123");
		
	}
	

}
