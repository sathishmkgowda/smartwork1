package StoreProcedureTesting;

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

import com.mysql.cj.protocol.Resultset;


public class SPTesting 
{
	Connection con=null;
	Statement stmt=null;
	ResultSet rs = null;
	CallableStatement cStmt=null;
	ResultSet rs1=null;
	ResultSet rs2=null;

	
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
	void test_storedProcedureExists() throws SQLException
	{
		 stmt = con.createStatement();  //createstatement means call method for storeProcedure
		 rs = stmt.executeQuery("SHOW PROCEDURE STATUS WHERE Name='SelectAllCustomers'"); //to exceute the querry executeQuerry method is used and also get the response that response stored in reference variable
		 rs.next();   //pointing to the current 
		 
		 //verification of that perticular name selectallcustomers procedure exist in the name column in the database
		 Assert.assertEquals(rs.getString("Name"),"SelectAllCustomers");
	}
	
	@Test(priority = 2)
	void test_SelectAllCustomers() throws SQLException
	{
	   cStmt = con.prepareCall("{CALL SelectAllCustomers()}");    //callablestatement object
	   rs1 = cStmt.executeQuery();
		
		stmt=con.createStatement();                               //connection object
	     rs2=stmt.executeQuery("Select * from customers"); 
	     
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
	void test_SelectAllCustomersByCity() throws SQLException
	{
	   cStmt = con.prepareCall("{CALL SelectAllCustomerByCity(?)}");    //single input parameter for call statement
	   cStmt.setString(1,"Singapore");     //setting up the value ? input parameter
	   rs1 = cStmt.executeQuery();
		
		stmt=con.createStatement();                               //connection object
	     rs2=stmt.executeQuery("Select * from customers Where city='Singapore'"); 
	     
	     Assert.assertEquals(compareResultSets(rs1, rs2), true);                                 
	}
	
	@Test(priority = 4)
	void test_SelectAllCustomersByCityAndPincode() throws SQLException
	{
	   cStmt = con.prepareCall("{CALL SelectAllCustomerSByCityandPin(?,?)}");    //two input parameter for call statement
	   cStmt.setString(1,"Singapore"); //setting up the value ? input parameter
	   cStmt.setString(2,"07993");
	   rs1 = cStmt.executeQuery();
		
		stmt=con.createStatement();                               //connection object
	     rs2=stmt.executeQuery("Select * from customers Where city='Singapore' and postalCode='079903'"); 
	     
	     Assert.assertEquals(compareResultSets(rs1, rs2), true);  
}
	
	//this test is not executed becaue mistake in querry
	@Test(priority = 5)       
	void test_get_order_by_cust() throws SQLException
	{
	   cStmt = con.prepareCall("{CALL SelectAllCustomerByCityandPin(?,?,?,?,?)}");    //one input parameter and 4 output parameter for call statement
	   cStmt.setInt(1, 112);        //setting up the value ? input parameter
	   
	   cStmt.registerOutParameter(2,Types.INTEGER);
	   cStmt.registerOutParameter(3,Types.INTEGER);
	   cStmt.registerOutParameter(4,Types.INTEGER);
	   cStmt.registerOutParameter(5,Types.INTEGER);
	    cStmt.executeQuery();
	    
	    int shipped=cStmt.getInt(2);
	    int canceled=cStmt.getInt(3);
	    int resolved=cStmt.getInt(4);
	    int disputed=cStmt.getInt(5);
	    
	    
		
		stmt=con.createStatement();                               //connection object
	   //  rs=stmt.executeQuery(("Select count(*) as 'shipped' from orders WHERE customerNumber=141 AND status='Shipped')as shipped,(Select count(*) as 'canceled' from orders WHERE customerNumber=141 AND status='Canceled')as Canceled,(Select count(*) as 'disputed' from orders WHERE customerNumber=141 AND status='Disputed')as disputed,(Select count(*) as 'resolved' from orders WHERE customerNumber=141 AND status='Resolved')as resolved"));
	   //  rs=stmt.executeQuery("(Select count(*) as 'shipped' from orders WHERE customerNumber=141 AND status='Shipped')as shipped,Select count(*) as 'canceled' from orders WHERE customerNumber=141 AND status='Canceled' as canceled,Select count(*) as 'disputed' from orders WHERE customerNumber=141 AND status='Disputed'as disputed,Select count(*) as 'resolved' from orders WHERE customerNumber=141 AND status='Resolved'as resolved");)
	     rs.next();
	     
	     int exq_shipped=rs.getInt("shipped");
	     int exq_canceled= rs.getInt("canceled");
	     int exq_disputed=rs.getInt("disputed");
	     int exq_resolved=rs.getInt("resolved");
	     
	     if(shipped==exq_shipped && canceled==exq_canceled && resolved==exq_resolved && disputed==exq_disputed)
	     {
	    	 Assert.assertTrue(true);
	     }
	     else
	     {
	    	 Assert.assertTrue(false);
	     }
}
	
/* refer video databasetesting parameter 1 input parameter and 1 output parameter
 * @Test(priority = 6) void GetCustomerShipping() throws SQLException { cStmt =
 * con.prepareCall("{CALL GetCustomerShipping(?,?)}"); //one input parameter and
 * 4 output parameter for call statement cStmt.setInt(1, 121); //setting up the
 * value ? input parameter
 * 
 * cStmt.registerOutParameter(2,Types.VARCHAR);
 * 
 * cStmt.executeQuery(); String shippingTime= cStmt.getString(2);
 * stmt=con.createStatement();
 * 
 * stmt=con.createStatement();
 * 
 * 
 * 
 * 
 * }
 */

}
