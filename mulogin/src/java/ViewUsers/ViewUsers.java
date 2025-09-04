import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ViewUsers"})
public class ViewUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dbUrl = "jdbc:mysql://localhost:3306/mudb?useSSL=false&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "";

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {

                    String sql = "SELECT id, first_name, last_name, relation_type, mother_name, father_husband_name, dob, gender, mobile_number, email FROM users";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    out.println("<!DOCTYPE html>");
                    out.println("<html><head><title>Registered Users</title>");
                    out.println("<style>");
                    out.println("body{font-family:Arial, sans-serif;margin:20px}");
                    out.println("table{width:100%;border-collapse:collapse}");
                    out.println("th,td{border:1px solid #ccc;padding:8px;text-align:left}");
                    out.println("th{background:#f2f2f2}");
                    out.println("</style>");
                    out.println("</head><body>");
                    out.println("<h1>All Registered Users</h1>");
                    out.println("<table>");
                    out.println("<tr>"
                            + "<th>ID</th>"
                            + "<th>First Name</th>"
                            + "<th>Last Name</th>"
                            + "<th>Relation</th>"
                            + "<th>Mother Name</th>"
                            + "<th>Father/Husband</th>"
                            + "<th>DOB</th>"
                            + "<th>Gender</th>"
                            + "<th>Mobile</th>"
                            + "<th>Email</th>"
                            + "<th>Action</th>" 
                            + "</tr>");

                    boolean hasData = false;
                    while (rs.next()) {
                        hasData = true;
                        out.println("<tr>");
                        out.println("<td>" + rs.getInt("id") + "</td>");
                        out.println("<td>" + safe(rs.getString("first_name")) + "</td>");
                        out.println("<td>" + safe(rs.getString("last_name")) + "</td>");
                        out.println("<td>" + safe(rs.getString("relation_type")) + "</td>");
                        out.println("<td>" + safe(rs.getString("mother_name")) + "</td>");
                        out.println("<td>" + safe(rs.getString("father_husband_name")) + "</td>");
                        out.println("<td>" + safe(rs.getString("dob")) + "</td>");
                        out.println("<td>" + safe(rs.getString("gender")) + "</td>");
                        out.println("<td>" + safe(rs.getString("mobile_number")) + "</td>");
                        out.println("<td>" + safe(rs.getString("email")) + "</td>");
                        out.println("<td>");
                        out.println("<a href='EditUser?id=" + rs.getInt("id") + "'>Edit</a> | ");
                        out.println("<a href='DeleteUser?id=" + rs.getInt("id") + "' onclick=\"return confirm('Are you sure?')\">Delete</a>");
                        out.println("</td>");
   
                        out.println("</tr>");
                    }

                    if (!hasData) {
                        out.println("<tr><td colspan='10'>No users found.</td></tr>");
                    }

                    out.println("</table>");
                    out.println("<br><a href='index.html'>&larr; Back to Registration</a>");
                    out.println("</body></html>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                try (PrintWriter outError = response.getWriter()) {
                    outError.println("<h1>Error occurred: " + e.getMessage() + "</h1>");
                }
            }
        }
    }

    private static String safe(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }

    @Override
    public String getServletInfo() {
        return "Displays all registered users from MySQL";
    }
}
