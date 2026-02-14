<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Smart Canteen</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto shadow" style="max-width:480px">
        <div class="card-body">
            <h4 class="card-title mb-3 text-center">Register</h4>

            <!-- ✅ Registration Form -->
            <form action="<%= request.getContextPath() %>/auth" method="post" autocomplete="off">
                <input type="hidden" name="action" value="register"/>

                <div class="mb-3">
                    <label class="form-label">Full Name</label>
                    <input class="form-control" name="name" required/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input class="form-control" type="email" name="email" required/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input class="form-control" type="password" name="password" required/>
                </div>

                <button class="btn btn-success w-100" type="submit">Register</button>
            </form>

            <!-- ✅ Redirect to Login -->
            <p class="mt-3 text-center">
                Already have an account? 
                <a href="<%= request.getContextPath() %>/auth?action=login">Login</a>
            </p>

            <!-- ✅ Show Error -->
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

            <!-- ✅ Optional Success (not usually needed here, but handled) -->
            <%
                Object success = request.getAttribute("success");
                if (success != null) {
            %>
                <div class="alert alert-success mt-3">
                    <%= success %>
                </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
