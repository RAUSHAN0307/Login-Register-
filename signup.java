package loginProject;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final String url = "jdbc:mysql://localhost:3306/register";
    private static final String userName = "root";
    private static final String password = "Raushan@8051";
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String Full_Name = req.getParameter("fullname");
		String Email = req.getParameter("email");
		String phone_number  = req.getParameter("phone");
		String passwords = req.getParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String query = "Insert into register(Full_Name,Email,phone_number , password)Values(?,?,?,?)";
			String query1 = "select * from register where Email = ? ";
			Connection connection = DriverManager.getConnection(url, userName, password);
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			PreparedStatement prepareStatement1 = connection.prepareStatement(query1);
			prepareStatement1.setString(1,Email);
			ResultSet resultSet = prepareStatement1.executeQuery();
			if(resultSet.next()) {
				res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                out.println("<h2 style='color: red;'>Email is already exist.</h2>");
                RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
                rd.include(req, res);
			}
			prepareStatement.setString(1, Full_Name);
			prepareStatement.setString(2,Email);
			prepareStatement.setString(3, phone_number);
			prepareStatement.setString(4,passwords);
			
			
			int rowAffected = prepareStatement.executeUpdate();
			 if(rowAffected > 0 ){
	                System.out.println("inserted");
	                RequestDispatcher rd = req.getRequestDispatcher("login.html");
	                rd.forward(req, res);
	            }
	            else {
	                System.out.println("not inserted");
	            }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
