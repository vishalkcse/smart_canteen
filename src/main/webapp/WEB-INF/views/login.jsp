<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Smart Canteen</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto shadow" style="max-width:420px">
        <div class="card-body">
            <h4 class="card-title text-center mb-3">Login</h4>

            <!-- ✅ Login Form -->
            <form action="<%= request.getContextPath() %>/auth" method="post" autocomplete="off">
                <input type="hidden" name="action" value="login"/>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input class="form-control" type="email" name="email" required />
                </div>

                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input class="form-control" type="password" name="password" required />
                </div>

                <button class="btn btn-primary w-100" type="submit">Login</button>
            </form>

            <hr/>

            <!-- ✅ Register Link -->
            <p class="text-center">
                Don't have an account?
                <a href="<%= request.getContextPath() %>/auth?action=register">Register</a>
            </p>

            <!-- ✅ Success Message -->
            <%
                String registered = request.getParameter("registered");
                if ("1".equals(registered)) {
            %>
                <div class="alert alert-success mt-3">
                    ✅ Registered successfully. Please login.
                </div>
            <%
                }
            %>

            <!-- ✅ Error Message -->
            <%
                Object error = request.getAttribute("error");
                if (error != null) {
            %>
                <div class="alert alert-danger mt-3">
                    <%= error %>
                </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
