package com.smartcanteen.servlet;

import com.smartcanteen.model.User;
import com.smartcanteen.service.UserService;
import com.smartcanteen.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else if ("register".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("register".equals(action)) {
            register(req, resp);
        } else if ("login".equals(action)) {
            login(req, resp);
        } else if ("logout".equals(action)) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (userService.findByEmail(email) != null) {
            req.setAttribute("error", "User already exists");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // TODO: hash passwords in production
        user.setRole("customer");

        userService.register(user);

        // Redirect to login with a success message using query param
        resp.sendRedirect(req.getContextPath() + "/auth?action=login&registered=1");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            req.setAttribute("error", "Invalid email or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/menu");
    }
}
