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
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;


public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String  LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/volunteerdb";
    public static String PASSWORD="983223@Raj";
    public static String USERNAME = "root";
    Connection connection;
    public loginServlet() {
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
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from volunteerinfo where username=?");
			preparedStatement.setString(1, userNameString);
			ResultSet rSet = preparedStatement.executeQuery();
			PrintWriter out = response.getWriter();
//			pWriter.println("<html><body><center>");
			if(rSet.next()) {
				if(rSet.getString("password").equals(passwordString)) {
					String name = rSet.getString("name");
	                String age = rSet.getString("age");
	                String address = rSet.getString("address");
	                String aadhaar = rSet.getString("aadhaarNumber");
	                String email = rSet.getString("emailId");
	                String phone = rSet.getString("phoneNumber");

	                out.println("<html><head>");
	                out.println("<title>Welcome Page</title>");
	                out.println("<style>");
	                out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; }");
	                out.println(".container { max-width: 600px; margin: 50px auto; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
	                out.println(".heading { text-align: center; color: #333; margin-bottom: 20px; }");
	                out.println(".details { margin-bottom: 10px; }");
	                out.println("h4 { color: #007bff; }");
	                out.println("</style>");
	                out.println("</head><body>");
	                
	                out.println("<div class='container'>");
	                out.println("<h2 class='heading'>Welcome " + name + "</h2>");
	                
	                out.println("<div class='details'><h4>Age:</h4><p>" + age + "</p></div>");
	                out.println("<div class='details'><h4>Address:</h4><p>" + address + "</p></div>");
	                out.println("<div class='details'><h4>Aadhaar Number:</h4><p>" + aadhaar + "</p></div>");
	                out.println("<div class='details'><h4>Email ID:</h4><p>" + email + "</p></div>");
	                out.println("<div class='details'><h4>Phone Number:</h4><p>" + phone + "</p></div>");
	                
	                out.println("</div></body></html>");
				}else {
					out.println("<html><body><center><h3>Incorrect Password</h3></center></body></html>");
				}
				
			}else {
				out.println("<html><body><center><h1>User not Registered</h1></center></body></html>");
			}
//			out.println("</center></body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			out.println("<h3>Error: " + e.getMessage() + "</h3>");
		}
	}

}
