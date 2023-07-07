package StoreFunctionTesting;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SFTesting 
{
	Connection con=null;
	Statement stmt=null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	CallableStatement cStmt=null;

	@BeforeClass
	public void setup() throws SQLException
	{
		
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","root");
		// to establish connection to database
	}
	
	@AfterClass
	void tearDown() throws SQLException
	{
		con.close();
	}
	
	@Test(priority = 1)
	void test_storedFunctionExists() throws SQLException
	{
		rs=con.createStatement().executeQuery("SHOW FUNCTION STATUS WHERE Name='CustomerLevel'");
		rs.next();
		
		 Assert.assertEquals(rs.getString("Name"),"CustomerLevel");
		
	}
	
	@Test(priority = 2)
	void test_CustomerLevel_with_SQLStatement() throws SQLException
	{
		rs1=con.createStatement().executeQuery("select customerName,CustomerLevel(creditlimit) from customers'");
	 rs2=con.createStatement().executeQuery("select customerName,CASE WHEN creditlimit>50000 Then 'PLATINUM' WHEN creditLimit >=10000 AND creditLimit<50000 THEN 'SILVER' END as customerlevel FROM customers");
		
		 Assert.assertEquals(compareResultSets(rs1, rs2), true); 
		
	}
	
	//utility methods for comparing two ResultSets
	public boolean compareResultSets(ResultSet rs1,ResultSet rs2) throws SQLException
	{
		while(rs1.next())
		{
			rs2.next();
			int count=rs1.getMetaData().getColumnCount();
			for(int i=1; i<=count; i++)
			{
				
				if(!StringUtils.equals(rs1.getString(i),rs2.getString(i)))
				{
					return false;
				}
			}
			
		}
		return true;
	}
	
	
	@Test(priority = 3)
	void test_CustomerLevel_with_StoresProcedure() throws SQLException
	{
		 cStmt = con.prepareCall("{CALL GetCustomerLevel(?,?)}");    //two input parameter for call statement
		   cStmt.setInt(1, 131);     //setting up the value ? input parameter
		   cStmt.registerOutParameter(2, Types.VARCHAR);
		    cStmt.executeQuery();
			String custlevel = cStmt.getString(2);
			stmt=con.createStatement();                             
		  rs=con.createStatement().executeQuery("select customerName,CASE WHEN creditlimit>50000 Then 'PLATINUM' WHEN creditLimit >=10000 AND creditLimit<50000 THEN 'SILVER' END as customerlevel FROM customers");
		   rs.next();
		   
		   String exp_custlevel = rs.getString("customerLevel");
		     Assert.assertEquals(custlevel, false);
	}
	}


