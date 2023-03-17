

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
 * Servlet implementation class AddMarks
 */
@WebServlet("/AddMarks")
public class AddMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMarks() {
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
		double tel=Double.parseDouble(request.getParameter("tel"));
		double hin=Double.parseDouble(request.getParameter("hin"));
		double eng=Double.parseDouble(request.getParameter("eng"));
		double mat=Double.parseDouble(request.getParameter("mat"));
		double sci=Double.parseDouble(request.getParameter("sci"));
		double soc=Double.parseDouble(request.getParameter("soc"));
		
		double total=tel+hin+eng+mat+sci+soc;
		double per=(total/600)*100;
		String grade="";
		if(per>=90)
			grade="A";
		else if(per>=70&&per<90)
			grade="B";
		else if(per>=50&&per<70)
			grade="C";
		else
			grade="D";
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","nbatch","nbatch");
			PreparedStatement ps=con.prepareStatement("insert into StudentMarks values(?,?,?,?,?,?,?)");
			ps.setInt(1,sid);
			ps.setDouble(2,tel);
			ps.setDouble(3,hin);
			ps.setDouble(4,eng);
			ps.setDouble(5,mat);
			ps.setDouble(6,sci);
			ps.setDouble(7,soc);
					
			PreparedStatement ps1=con.prepareStatement("insert into StudentGrades values(?,?,?,?)");
			ps1.setInt(1, sid);
			ps1.setDouble(2, total);
			ps1.setDouble(3, per);
			ps1.setString(4, grade);
			
			int i=ps.executeUpdate();
			int l=ps1.executeUpdate();
			if(i==1&&l==1)
			{
				out.println("<h1>marks entered success fully Succescfully<h1>");
			}
			else {
				out.println("<h1>marks not entered failed<h1>");
			}
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
