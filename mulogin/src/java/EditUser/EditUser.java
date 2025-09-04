import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditUser")
public class EditUser extends HttpServlet {

    String dbUrl = "jdbc:mysql://localhost:3306/mudb?useSSL=false&serverTimezone=UTC";
    String dbUser = "root";
    String dbPass = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
                String sql = "SELECT * FROM users WHERE id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    out.println("<html><body>");
                    out.println("<h2>Edit User</h2>");
                    out.println("<form method='post' action='EditUser'>");
                    out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'/>");

                    out.println("First Name: <input type='text' name='first_name' value='" + rs.getString("first_name") + "'/><br/>");
                    out.println("Last Name: <input type='text' name='last_name' value='" + rs.getString("last_name") + "'/><br/>");
                    out.println("Relation Type: <input type='text' name='relation_type' value='" + rs.getString("relation_type") + "'/><br/>");
                    out.println("Mother Name: <input type='text' name='mother_name' value='" + rs.getString("mother_name") + "'/><br/>");
                    out.println("Father/Husband Name: <input type='text' name='father_husband_name' value='" + rs.getString("father_husband_name") + "'/><br/>");
                    out.println("DOB: <input type='date' name='dob' value='" + rs.getString("dob") + "'/><br/>");
                    out.println("Gender: <input type='text' name='gender' value='" + rs.getString("gender") + "'/><br/>");
                    out.println("Mobile: <input type='text' name='mobile_number' value='" + rs.getString("mobile_number") + "'/><br/>");
                    out.println("Email: <input type='email' name='email' value='" + rs.getString("email") + "'/><br/>");

                    out.println("<input type='submit' value='Update'/>");
                    out.println("</form>");
                    out.println("</body></html>");
                } else {
                    out.println("<h3>User not found!</h3>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String relationType = request.getParameter("relation_type");
        String motherName = request.getParameter("mother_name");
        String fatherHusbandName = request.getParameter("father_husband_name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile_number");
        String email = request.getParameter("email");

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
                String sql = "UPDATE users SET first_name=?, last_name=?, relation_type=?, mother_name=?, father_husband_name=?, dob=?, gender=?, mobile_number=?, email=? WHERE id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, relationType);
                stmt.setString(4, motherName);
                stmt.setString(5, fatherHusbandName);
                stmt.setString(6, dob);
                stmt.setString(7, gender);
                stmt.setString(8, mobile);
                stmt.setString(9, email);
                stmt.setString(10, id);

                int updated = stmt.executeUpdate();

                if (updated > 0) {
                    out.println("<script>alert('User updated successfully!');</script>");
                    out.println("<script>window.location.href='ViewUsers';</script>");
                } else {
                    out.println("<script>alert('Update failed!');</script>");
                    out.println("<script>window.location.href='ViewUsers';</script>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
