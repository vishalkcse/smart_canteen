package com.smartcanteen.servlet;

import com.smartcanteen.model.User;
import com.smartcanteen.service.MenuItemService;
import com.smartcanteen.service.impl.MenuItemServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MenuServlet extends HttpServlet {
    private final MenuItemService menuService = new MenuItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(); // ✅ changed from false to true
        User user = (User) session.getAttribute("user");

        // ✅ Debugging output
        System.out.println(">>> Session ID: " + session.getId());
        System.out.println(">>> User in session: " + user);

        if (user == null) {
            // Session exists but no user — redirect to login
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
            return;
        }

        // ✅ Fetch and display all menu items
        req.setAttribute("menu", menuService.listAll());
        req.getRequestDispatcher("/WEB-INF/views/menu.jsp").forward(req, resp);
    }
}
