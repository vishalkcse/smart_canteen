package com.smartcanteen.servlet;

import com.smartcanteen.model.MenuItem;
import com.smartcanteen.model.PreOrder;
import com.smartcanteen.model.User;
import com.smartcanteen.service.MenuItemService;
import com.smartcanteen.service.PreOrderService;
import com.smartcanteen.service.impl.MenuItemServiceImpl;
import com.smartcanteen.service.impl.PreOrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PreOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final PreOrderService preOrderService = new PreOrderServiceImpl();
    private final MenuItemService menuItemService = new MenuItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
            return;
        }

        String action = req.getParameter("action");

        if ("view".equals(action)) {
            // Show user's past orders
            List<PreOrder> orders = preOrderService.listByUser(user.getId());
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/WEB-INF/views/preorders.jsp").forward(req, resp);
            return;
        }

        // Default: show preorder form
        req.setAttribute("menu", menuItemService.listAll());

        String success = req.getParameter("success");
        if ("true".equals(success)) {
            req.setAttribute("message", "✅ Your order has been placed successfully!");
        }

        req.getRequestDispatcher("/WEB-INF/views/preorder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
            return;
        }

        User user = (User) session.getAttribute("user");

        try {
            String menuIdStr = req.getParameter("menuId");
            String qtyStr = req.getParameter("quantity");
            String pickupStr = req.getParameter("pickupTime");

            if (menuIdStr == null || qtyStr == null || pickupStr == null ||
                menuIdStr.isEmpty() || qtyStr.isEmpty() || pickupStr.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing input data");
                return;
            }

            Long menuId = Long.parseLong(menuIdStr);
            int qty = Integer.parseInt(qtyStr);

            if (qty <= 0) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantity must be greater than zero");
                return;
            }

            MenuItem item = menuItemService.findById(menuId);
            if (item == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu item");
                return;
            }

            PreOrder order = new PreOrder();
            order.setUser(user);
            order.setMenuItem(item);
            order.setQuantity(qty);
            order.setStatus("PENDING");

            // Parse pickup time
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime pickupTime = LocalDateTime.parse(pickupStr, formatter);
                order.setPickupTime(pickupTime);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid pickup time format");
                return;
            }

            // Save order
            preOrderService.placeOrder(order);

            // Redirect to GET with success flag to show confirmation message
            resp.sendRedirect(req.getContextPath() + "/preorder?success=true");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        }
    }
}
