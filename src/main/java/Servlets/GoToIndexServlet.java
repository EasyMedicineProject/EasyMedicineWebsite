package Servlets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.AccountDelete;
import Models.LoginModel;
import Models.User;
import com.sun.org.apache.xpath.internal.SourceTree;

@WebServlet("/index")
public class GoToIndexServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String url=request.getHeader("Referer");


        System.out.println(url);
        if (url.equals("http://localhost:8080/login.jsp")/*TOZI LOGIN E OT INDEXA*/|| url.equals("http://localhost:8080/login")/*TOZI LINK E OT LOGIN SLED SMQNA NA PASS*/ ) {
            String email = request.getParameter("email");
            System.out.println("vzeh emaila");
            String password = request.getParameter("password");
            System.out.println("vzeh parolata");
            System.out.println("Variables: " + email + password);
            LoginModel model = new LoginModel();
            String type = model.authenticateLogin(email, password);
            System.out.println("LOGIN message: " + type);
            if (type.equals("SUCCESS")) {
                HttpSession session = request.getSession();
                session.setAttribute("isLogged", true);
                session.setAttribute("username", email);
                session.setAttribute("password", password);
                response.sendRedirect("/index.jsp");

            } else if (type.equals("FAIL")) {
                HttpSession session = request.getSession();
                session.setAttribute("isLogged", false);
                response.sendRedirect("/login.jsp");
            }
        }


        else if(url.equals("http://localhost:8080/manage_account.jsp")/*TOZI E OT ACCOUNT CHANGE KYM INDEX*/){
            HttpSession session = request.getSession(true);
            String inputedPassword = request.getParameter("password");
            System.out.println(inputedPassword);
            String currentPassword = (String) session.getAttribute("password");
            System.out.println(currentPassword);
            String username= (String) session.getAttribute("username");
            if (inputedPassword.equals(currentPassword)){
                AccountDelete accountDelete= new AccountDelete();
                accountDelete.deleteAccount(username);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("/index.jsp");
            }
            else{
                System.out.println("spira na proverka na parolata");
            }
        }

        else if (url.equals("http://localhost:8080/index.jsp" )){
            System.out.println("predi if-a sym");
            HttpSession session = request.getSession(false);
            System.out.println("oshte po blizko do if-a");
            if (session != null) {
                session.invalidate();}
                response.sendRedirect("/index.jsp");

        }


    }


}

