package by.tms.instaclone31onl.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String user = request.getParameter("usName");
        String password = request.getParameter("usPass");
        checkRegistration(response, request, out, user, password, session);
    }

    protected void checkRegistration(HttpServletResponse response, HttpServletRequest request,PrintWriter out ,
                                     String user, String password, HttpSession session) throws ServletException, IOException {
        String csvFile = "C:\\Users\\user\\insta-clone-31-onl\\src\\main\\resources\\users.csv";
        String line = "";
        String cvsSplitBy = ";";
        boolean bool = false;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                String username = data[0];
                String pass = data[1];
                if (username.equals(user) && pass.equals(password)) {
                    session.setAttribute("user", user);
                    response.sendRedirect("pages/welcome.jsp?name=" + user);
                    bool = true;
                    break;
                }
            }
            if  (!bool) {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                out.println("<div align='center' style='color: red'>Неправильный логин или пароль</div>");
                rd.include(request, response);
            }
        }
    }
}