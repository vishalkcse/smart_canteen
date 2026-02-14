<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.smartcanteen.model.MenuItem" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Place Preorder</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body class="bg-light">

<div class="container py-5">
    <h2 class="text-center mb-4">Place Your Preorder</h2>

    <c:if test="${not empty message}">
        <div class="alert alert-success text-center mx-auto" style="max-width: 500px;">
            <strong>✔️</strong> ${message}
        </div>
    </c:if>

    <%
        List<MenuItem> menu = (List<MenuItem>) request.getAttribute("menu");
        if (menu == null || menu.isEmpty()) {
    %>
        <div class="alert alert-info text-center my-5">No menu items available now.</div>
        <div class="text-center">
            <a href="<%=request.getContextPath()%>/menu" class="btn btn-secondary">Back to Menu</a>
        </div>
    <%
        } else {
    %>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="<%=request.getContextPath()%>/preorder" method="post"
                  class="card custom-card p-4 shadow-sm needs-validation" novalidate>
                <div class="mb-3">
                    <label for="menuId" class="form-label">Select Item</label>
                    <select class="form-select" name="menuId" id="menuId" required>
                        <option disabled selected value="">-- Choose an item --</option>
                        <% for (MenuItem item : menu) { %>
                            <option value="<%= item.getId() %>">
                                <%= item.getName() %> - ₹<%= item.getPrice() %>
                            </option>
                        <% } %>
                    </select>
                    <div class="invalid-feedback">Please select a menu item.</div>
                </div>

                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity</label>
                    <input type="number" class="form-control" name="quantity" id="quantity" min="1" required>
                    <div class="invalid-feedback">Please enter a valid quantity.</div>
                </div>

                <div class="mb-3">
                    <label for="pickupTime" class="form-label">Pickup Time</label>
                    <input type="datetime-local" class="form-control" name="pickupTime" id="pickupTime" required>
                    <div class="form-text">Select date & time</div>
                    <div class="invalid-feedback">Please choose a valid pickup time.</div>
                </div>

                <button type="submit" class="btn btn-success w-100">📦 Place Preorder</button>
            </form>
            <div class="mt-3 text-center">
                <a href="<%=request.getContextPath()%>/menu" class="btn btn-secondary">Back to Menu</a>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>

<script>
  (() => {
    'use strict';
    const forms = document.querySelectorAll('.needs-validation');
    Array.from(forms).forEach(form => {
      form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  })();
</script>

</body>
</html>
