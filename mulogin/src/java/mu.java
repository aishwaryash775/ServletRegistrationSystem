import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Mu"})
public class mu extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String relation = request.getParameter("relation");
        String motherName = request.getParameter("motherName");
        String fatherHusbandName = request.getParameter("fatherHusbandName");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");

       
        String[] mobileDigits = request.getParameterValues("mobile");
        String mobileNumber = (mobileDigits != null) ? String.join("", mobileDigits) : "";

        String[] confirmMobileDigits = request.getParameterValues("confirmMobile");
        String confirmMobileNumber = (confirmMobileDigits != null) ? String.join("", confirmMobileDigits) : "";

        String email = request.getParameter("email");

    
    String dbUrl = "jdbc:mysql://localhost:3306/mudb?useSSL=false&serverTimezone=UTC";
    String dbUser = "root";
    String dbPass = "";

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
                    String sql = "INSERT INTO users (first_name, last_name, relation_type, mother_name, father_husband_name, dob, gender, mobile_number, confirm_mobile_number, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.setString(3, relation);
                    stmt.setString(4, motherName);
                    stmt.setString(5, fatherHusbandName);
                    stmt.setString(6, dob);
                    stmt.setString(7, gender);
                    stmt.setString(8, mobileNumber);
                    stmt.setString(9, confirmMobileNumber);
                    stmt.setString(10, email);

                    int rowsInserted = stmt.executeUpdate();

                    out.println("<!DOCTYPE html>");
                    out.println("<html><head><title>Registration Result</title></head><body>");

                    if (rowsInserted > 0) {
                        out.println("<h1>Registration Successful!</h1>");
                        out.println("<script>alert('Registration Successful!');</script>");
                     out.println("<script>window.location.href='success.html';</script>");


                    } else {
                        out.println("<script>alert('Registration Failed! Please try again.');</script>");
                        out.println("<script>window.location.href='registration.html';</script>");
                    }

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

    @Override
    public String getServletInfo() {
        return "Handles user registration and stores data in MySQL";
    }
}
