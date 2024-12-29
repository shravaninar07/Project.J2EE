package com.abc;


import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ForgetPassword")
public class ForgetPassServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email1");
		String fpass =req.getParameter("forgetpass");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c =DriverManager.getConnection("jdbc:mysql://localhost:3306/college?useSSL=false","root","root");
			PreparedStatement ps=c.prepareStatement("update register set pass= ? where email=?;");
	        ps.setString(1, fpass);
	        ps.setString(2, email);
			ps.executeUpdate();
			System.out.println("pass updated");
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
