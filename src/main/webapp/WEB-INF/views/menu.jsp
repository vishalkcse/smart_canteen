
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.smartcanteen.model.MenuItem" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu - Smart Canteen</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    
    
    
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-dark">Menu</h2>
        <form action="<%= request.getContextPath() %>/auth" method="post">
            <input type="hidden" name="action" value="logout">
            <button type="submit" class="btn btn-outline-danger">Logout</button>
        </form>
    </div>

    <%
        List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menu");
        if (menuItems != null && !menuItems.isEmpty()) {
    %>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <% for (MenuItem item : menuItems) { %>
                <div class="col">
                    <div class="card custom-card h-100">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title"><%= item.getName() %></h5>
                            <p class="card-text flex-grow-1"><%= item.getDescription() %></p>
                            <div class="mt-3">
                                <span class="fw-bold">₹ <%= item.getPrice() %></span>
                            </div>
                        </div>
                    </div>
                </div>
            <% } %>
        </div>

        <div class="mt-5 text-center">
            <a href="<%= request.getContextPath() %>/preorder" class="btn btn-success me-3">Place Pre‑Order</a>
            <a href="<%= request.getContextPath() %>/preorder?action=view" class="btn btn-primary">View My Orders</a>
        </div>
    <%
        } else {
    %>
        <div class="alert alert-info text-center my-5">No menu items available.</div>
    <%
        }
    %>
</div>

</body>
</html>
