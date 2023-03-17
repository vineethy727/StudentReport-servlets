

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int sid=Integer.parseInt(request.getParameter("sid"));
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","nbatch","nbatch");
			PreparedStatement ps=con.prepareStatement("delete from studentdetails where sid=?");
			ps.setInt(1,sid);
			int i=ps.executeUpdate();
			
			PreparedStatement ps1=con.prepareStatement("delete from studentmarks where sid=?");
			ps1.setInt(1,sid);
			int j=ps1.executeUpdate();
			
			PreparedStatement ps2=con.prepareStatement("delete from studentgrades where sid=?");
			ps2.setInt(1,sid);
			int k=ps2.executeUpdate();
			
			if(i==1&&j==1&&k==1)
			{
				out.println("<h1>student record deleted succefully<h1>");
			}
			else {
				out.println("<h1>student record not deleted<h1>");
			}
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
	}

}
