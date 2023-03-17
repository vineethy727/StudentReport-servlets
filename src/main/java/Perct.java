

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Perct
 */
@WebServlet("/Perct")
public class Perct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Perct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int sid=Integer.parseInt(request.getParameter("sid"));
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","nbatch","nbatch");
			PreparedStatement ps=con.prepareStatement("select * from studentgrades where sid=?");
			ps.setInt(1,sid);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			out.print("<html><body><table border='1'><tr>");
			int n=rsmd.getColumnCount();
			for(int i=1;i<=n;i++)
			{
				out.println("<td>"+rsmd.getColumnName(i)+"</td>");	
			}
			out.println("</tr><tr>");			
			
			while(rs.next()) {	
				for(int i=1;i<=n;i++)
				{
					out.println("<td>"+rs.getString(i)+"</td>");
				}
			}
			out.println("</tr>");
			out.println("</table></body></html>");
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
	}

}
