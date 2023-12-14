package com.servlet.register;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource datasource;
	private static final String INSERT_QUERY="INSERT INTO STUDENT(SNAME,MAILID,PHONENO,GENDER,DOB,DEGREE,COURSE) VALUES(?,?,?,?,?,?,?)";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String stud_name=request.getParameter("stud_name");
		String mailid=request.getParameter("email");
		String phonenum=request.getParameter("phone");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String degree=request.getParameter("degree");
		String course=request.getParameter("course");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		pw.close();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection con=datasource.getConnection();){
			PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
			ps.setString(1, stud_name);
			ps.setString(2,mailid);
			ps.setString(3, phonenum);
			ps.setString(4, gender);
			ps.setString(5, dob);
			ps.setString(6, degree);
			ps.setString(7, course);
			int count=ps.executeUpdate();
			if(count==0) {
				pw.println("Record Stored into database");
			}else {
				pw.println("Record Stored in Database");
			}
			
		} catch (SQLException e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
