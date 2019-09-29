package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;

public class Model 
{

	public Model()
	{
		try
		{
			DriverManager.registerDriver(new OracleDriver());
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	private String name;
	private String accno;
	private String balance;
	private String custid;
	private String pwd;
	private String email;
	private PreparedStatement pstmt;
	private ResultSet res;
	Connection con;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getAccno() 
	{
		return accno;
	}
	
	public void setAccno(String accno)
	{
		this.accno = accno;
	}
	
	public String getBalance()
	{
		return balance;
	}
	
	public void setBalance(String balance) 
	{
		this.balance = balance;
	}
	
	public String getCustid()
	{
		return custid;
	}
	
	public void setCustid(String custid) 
	{
		this.custid = custid;
	}
	
	public String getPwd() 
	{
		return pwd;
	}
	
	public void setPwd(String pwd) 
	{
		this.pwd = pwd;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public boolean login()
	{
		try
		{
			pstmt=con.prepareStatement("SELECT * FROM BANK_APP WHERE CUSTID=? AND PWD=?");
			pstmt.setString(1,custid);
			pstmt.setString(2,pwd);
			res=pstmt.executeQuery();
			if (res.next()==true)
			{
				accno=res.getString("ACCNO");
				return true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkBalance()
	{
		try
		{
			pstmt=con.prepareStatement("SELECT * FROM BANK_APP WHERE ACCNO=?");
			pstmt.setString(1, accno);
			res=pstmt.executeQuery();
			if (res.next()==true)
			{
				balance=res.getString("balance");
				return true;
			}
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changePwd()
	{
		try
		{
			pstmt=con.prepareStatement("UPDATE BANK_APP SET PWD=?  WHERE ACCNO=?");
			pstmt.setString(1, pwd);
			pstmt.setString(2, accno);
			int rows = pstmt.executeUpdate();
			if (rows==0)
			{
				return false;
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return true;
	}
	
	
	public boolean transfer(String tamt)
	{
		try
		{
			pstmt=con.prepareStatement("UPDATE BANK_APP SET BALANCE=BALANCE-? WHERE ACCNO=?");
			pstmt.setString(1, tamt);
			pstmt.setString(2, accno);
			int rows =pstmt.executeUpdate();
			System.out.println("BANK_APP table updated");
			
			pstmt=con.prepareStatement("INSERT INTO BANK_STATEMENT VALUES(?,?)");
			pstmt.setString(1, accno);
			pstmt.setString(2, tamt);
			pstmt.executeUpdate();
			System.out.println("BANK_STATEMENT table updated");
			if (rows==0)
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	public ArrayList getStatement()
	{
		ArrayList al=new ArrayList();
		try
		{
			pstmt=con.prepareStatement("SELECT * FROM BANK_STATEMENT WHERE ACCNO=?");
			pstmt.setString(1, accno);
			res=pstmt.executeQuery();
			while(res.next()==true)
			{
				String temp=res.getString("AMOUNT");
				al.add(temp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return al;
	}
	
	
	public boolean resetPwd()
	{
		try
		{
			pstmt=con.prepareStatement("UPDATE BANK_APP SET PWD=? WHERE EMAIL=?");
			pstmt.setString(1, pwd);
			pstmt.setString(2, email);
			int rows =pstmt.executeUpdate();
			
			if (rows==0)
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return true;
	}
}