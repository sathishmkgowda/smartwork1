package com.sambram1.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.sambram1.dbutil.DBUtil;
import com.sambram1.service.UserService;

@Service
public class userserviceimpl implements UserService
{
	Connection connection;
	int flag=0;
	
	public userserviceimpl() throws SQLException
	{
		connection=DBUtil.getConnection();
	}
	
	
	@Override
	public int loginValidation(String username,String password)
	{
		try {
			PreparedStatement statement=connection.prepareStatement("SELECT * from users where username='"+username+"'");
			ResultSet rs=statement.executeQuery();
			
			while(rs.next())
			{
				if(rs.getString(6).equals(username) && rs.getString(7).equals(password))
				{
					flag=1;
				}
				else
				{
					System.out.println("invalid username and password");
					flag=0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	

}
 