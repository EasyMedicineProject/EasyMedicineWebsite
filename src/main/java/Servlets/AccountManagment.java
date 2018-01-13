package Servlets;

import Models.PasswordChange;
import Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class AccountManagment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getHeader("Referer");

        if (url.equals("http://localhost:8080/registration.jsp")) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String username = request.getParameter("username");
            System.out.println("PARAMETRITE OT REGISTER FORMATA SA VZETI");
            User user = new User();
            try {
                user.CreateRegistration(email, password, username);
                response.sendRedirect("/login.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("/registration.jsp");
            }
        }


        else if(url.equals("http://localhost:8080/password_change.jsp")) {
            HttpSession session = request.getSession(true);
            String username = (String) session.getAttribute("username");
            System.out.println(username);
            String currentPassword = request.getParameter("currentPassword");
            System.out.println(currentPassword);
            String newPassword = request.getParameter("newPassword");
            System.out.println(newPassword);
            PasswordChange password_change = new PasswordChange();
            password_change.changePassword(username, currentPassword, newPassword);
            response.sendRedirect("/login.jsp");
        }
    }
}
