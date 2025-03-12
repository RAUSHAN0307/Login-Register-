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

@WebServlet("/Login")
public class login extends HttpServlet {
    private static final String url = "jdbc:mysql://localhost:3306/register";
    private static final String userName1 = "root";
    private static final String password1 = "Raushan@8051";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            connection = DriverManager.getConnection(url, userName1, password1);

            // Define the SQL query
            String query = "SELECT * FROM register WHERE Email = ? AND password = ?";

            // Prepare the statement
            prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, userName);
            prepareStatement.setString(2, password);

            // Execute the query
            resultSet = prepareStatement.executeQuery();

            // Check if the result is found
            if (resultSet.next()) {
                // If login is successful, forward to the welcome page
                RequestDispatcher rd = req.getRequestDispatcher("Welcome.jsp");
                rd.forward(req, res);
            } else {
                // If login fails, show an error message and forward to the signup page
                res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                out.println("<h2 style='color: red;'>Email ID and password do not match.</h2>");
                RequestDispatcher rd = req.getRequestDispatcher("login.html"); // Redirect to login page, not signup.
                rd.include(req, res);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                // Close resources to avoid memory leaks
                if (resultSet != null) {
                    resultSet.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
