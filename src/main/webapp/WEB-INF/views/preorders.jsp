<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.smartcanteen.model.PreOrder" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Preorders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body class="bg-light">

<div class="container py-5">
    <h2 class="text-center mb-4">My Orders</h2>

    <%
        List<PreOrder> orders = (List<PreOrder>) request.getAttribute("orders");
        if (orders == null || orders.isEmpty()) {
    %>
        <div class="alert alert-info text-center my-5">You have no preorders yet.</div>
        <div class="text-center">
            <a href="<%=request.getContextPath()%>/preorder" class="btn btn-primary">Place Pre-Order</a>
        </div>
    <%
        } else {
    %>
        <div class="table-responsive">
            <table class="table table-striped align-middle">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Item</th>
                        <th>Qty</th>
                        <th>Pickup Time</th>
                        <th>Status</th>
                        <th>Created At</th>
                    </tr>
                </thead>
                <tbody>
                <% for (PreOrder p : orders) { %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getMenuItem().getName() %></td>
                        <td><%= p.getQuantity() %></td>
                        <td><%= p.getPickupTime() %></td>
                        <td><%= p.getStatus() %></td>
                        <td><%= p.getCreatedAt() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="text-center mt-4">
            <a href="<%=request.getContextPath()%>/menu" class="btn btn-secondary">Back to Menu</a>
        </div>
    <%
        }
    %>
</div>

</body>
</html>
