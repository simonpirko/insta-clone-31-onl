package by.tms.instaclone31onl.authorization;

import com.opencsv.CSVWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.UUID;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/authorization.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String[] content = {request.getParameter("aName"), request.getParameter("aPass"), UUID.randomUUID().toString()};
        checkingForCompletion(request, response, out, content);
        out.close();
    }

    private static void recordingData(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                                      String[] content, PrintWriter out) throws IOException, ServletException {
        if (content[1].equals(request.getParameter("aPassCopy"))) {
            try (FileWriter fileWriter = new FileWriter("C:\\Users\\user\\insta-clone-31-onl\\src\\main\\resources\\users.csv", true)) {
                CSVWriter writer = new CSVWriter(fileWriter, ';',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
                writer.writeNext(content);
                writer.close();
                session.setAttribute("user", content[0]);
                response.sendRedirect("pages/welcome.jsp?name=" + content[0]);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("pages/authorization.jsp");
            out.println("<div align='center' style='color: red'>Пароли не совпадают</div>");
            rd.include(request, response);
        }
    }

    private static void checkingForCompletion(HttpServletRequest request, HttpServletResponse response,
                                              PrintWriter out, String[] content) throws IOException, ServletException {
        if (request.getParameter("aPass").isEmpty() || request.getParameter("aPassCopy").isEmpty() ||
                request.getParameter("aName").isEmpty()) {
            RequestDispatcher rd = request.getRequestDispatcher("pages/authorization.jsp");
            out.println("<div align='center' style='color: red'>Заполните все поля</div>");
            rd.include(request, response);
        } else {
            checkUser(request, response, out, content);
        }
    }

    private static void checkUser(HttpServletRequest request, HttpServletResponse response,
                                  PrintWriter out, String[] content)
            throws IOException, ServletException {
        String csvFile = "C:\\Users\\user\\insta-clone-31-onl\\src\\main\\resources\\users.csv";
        String line = "";
        String cvsSplitBy = ";";
        boolean bool = false;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                String username = data[0];
                if (username.equals(content[0])) {
                    RequestDispatcher rd = request.getRequestDispatcher("pages/authorization.jsp");
                    out.println("<div align='center' style='color: red'>Такое имя пользователя уже существует</div>");
                    rd.include(request, response);
                    bool = true;
                    break;
                }
            }
            if (!bool) {
                recordingData(request, request.getSession(), response, content, out);
            }
        }
    }
}