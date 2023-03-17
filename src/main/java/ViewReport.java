

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
 * Servlet implementation class ViewReport
 */
@WebServlet("/ViewReport")
public class ViewReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewReport() {
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
			PreparedStatement ps=con.prepareStatement("select * from studentdetails where sid=?");
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
			
			out.println("<br><br>");
			
			PreparedStatement ps1=con.prepareStatement("select * from studentmarks where sid=?");
			ps1.setInt(1,sid);
			ResultSet rs1=ps1.executeQuery();
			ResultSetMetaData rsmd1=rs1.getMetaData();
			out.print("<html><body><table border='1'><tr>");
			int m=rsmd1.getColumnCount();
			for(int i=1;i<=m;i++)
			{
				out.println("<td>"+rsmd1.getColumnName(i)+"</td>");	
			}
			out.println("</tr><tr>");			
			
			while(rs1.next()) {	
				for(int i=1;i<=m;i++)
				{
					out.println("<td>"+rs1.getString(i)+"</td>");
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
