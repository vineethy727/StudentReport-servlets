

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
 * Servlet implementation class AddDetails
 */
@WebServlet("/AddDetails")
public class AddDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDetails() {
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
		String sname=request.getParameter("name");
		long smobno=Long.parseLong(request.getParameter("mobno"));
		String saddr=request.getParameter("addr");
		
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","nbatch","nbatch");
			PreparedStatement ps=con.prepareStatement("insert into StudentDetails values(?,?,?,?)");
			ps.setInt(1,sid);
			ps.setString(2,sname);
			ps.setLong(4,smobno);
			ps.setString(3,saddr);
						
			int i=ps.executeUpdate();
			if(i==1)
			{
				out.println("<h1>registered Succescfully<h1>");
			}
			else {
				out.println("<h1>registered failed<h1>");
			}
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
		
	}

}
