<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">Smart Canteen - Staff</a>
        <div>
            <a class="btn btn-outline-light" href="<%=request.getContextPath()%>/auth?action=logout">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h3>Staff Dashboard</h3>
    <p class="text-muted">List preorders here for staff to mark <strong>READY</strong> or <strong>PICKED</strong>.</p>
</div>

</body>
</html>
