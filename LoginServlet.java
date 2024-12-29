package com.abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/loginform")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email1");
		String pass=req.getParameter("pass1");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/college?useSSL=false","root","root");
		    PreparedStatement st = c.prepareStatement("select * from register where email=? and pass=?");
		    st.setString(1, email);
		    st.setString(2, pass);
		     ResultSet rs=st.executeQuery();
		     if(rs.next()) {
		    	 resp.setContentType("text/html");
		    	 req.setAttribute("email", rs.getString(1));
		    	 req.setAttribute("pass", rs.getString(2));
		    	 RequestDispatcher rd=req.getRequestDispatcher("/profile.jsp");
		    	 rd.include(req, resp);
		     }
		     else {
		    	 resp.setContentType("text/html");
		    	 PrintWriter ps=resp.getWriter();
		    	 ps.println("you are not existing user");
		    	 RequestDispatcher rd=req.getRequestDispatcher("/Login.html");
		    	 rd.include(req, resp);
		    	
		     }
	}catch(Exception e) {
		e.printStackTrace();
		}
	
	}

}
