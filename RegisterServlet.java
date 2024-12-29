package com.abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/regform")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname=req.getParameter("fname");
		String lname=req.getParameter("lname");
		String email=req.getParameter("email1");
		
		String phone=req.getParameter("telephone");
		long phone1=Long.parseLong(phone);
		
		
		String dob=req.getParameter("DOB1");
		
		String country=req.getParameter("country");
		String  pass=req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/college?useSSL=false","root","root");
		    PreparedStatement st = c.prepareStatement("insert into register(fname,lname,email,phone,dob,country,pass) values (?,?,?,?,?,?,?);");
		    st.setString(1, fname);
		    st.setString(2, lname);
		    st.setString(3, email);
		    st.setLong(4, phone1);		   
		    st.setString(5, dob);
		    st.setString(6, country);
		    st.setString(7, pass);
		    st.executeUpdate();
			System.out.println("data inserted");
			c.close();
			PrintWriter p = resp.getWriter();
			p.print("Data inserted successfully");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*System.out.println(fname);
		System.out.println(lname);
		System.out.println(email);
		System.out.println(phone);
		System.out.println(dob);
		System.out.println(country);
		System.out.println(pass);*/
		
		/*PrintWriter p = resp.getWriter();
		p.print(fname+" ");
		p.print(lname+" ");
		p.print(email+" ");
		p.print(phone+" ");
		p.print(dob+" ");
		p.print(country+" ");
		p.print(pass+" ");*/
	}

}
