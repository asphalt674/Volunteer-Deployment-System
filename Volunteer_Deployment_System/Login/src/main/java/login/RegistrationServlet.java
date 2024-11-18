package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String  LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/volunteerdb";
    public static String PASSWORD="983223@Raj";
    public static String USERNAME = "root";
    Connection connection;
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userNameString = request.getParameter("username");
		String passwordString = request.getParameter("password");
		String nameString = request.getParameter("name");
		String age = request.getParameter("age");
		String addressString = request.getParameter("address");
		String aadhaarString = request.getParameter("aadhaar");
		String emailIDString = request.getParameter("email");
		String phoneString = request.getParameter("phone");
		
		PrintWriter pWriter = response.getWriter();
		pWriter.println("<html><body><center>");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO volunteerinfo VALUES (?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, userNameString);
			preparedStatement.setString(2, passwordString);
			preparedStatement.setString(3, nameString);
			preparedStatement.setString(4, age);
			preparedStatement.setString(5, addressString);
			preparedStatement.setString(6, aadhaarString);
			preparedStatement.setString(7, emailIDString);
			preparedStatement.setString(8, phoneString);
			preparedStatement.executeUpdate();
			
			pWriter.println("<h1>Regitration Successfully done</h1>");
			pWriter.println("<a href=index.html>Login</a>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pWriter.println("<h3>Error: " + e.getMessage() + "</h3>");
		}
		pWriter.println("</center></body></html>");
	}

}
