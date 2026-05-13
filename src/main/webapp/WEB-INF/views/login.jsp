<%@ include file="fragments/header.jspf" %>

<main class="login-page">
    <div class="login-card">
        <div class="login-brand">CineFlex</div>
        <h1>Sign in</h1>

        <% if ("success".equals(request.getParameter("registered"))) { %>
            <div class="alert alert-success" role="alert">
                Account created successfully. Please sign in.
            </div>
        <% } %>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="mb-3">
                <label for="username" class="form-label">Username or email</label>
                <input type="text"
                       class="form-control"
                       id="username"
                       name="username"
                       value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>"
                       required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-danger w-100">Login</button>
        </form>

        <p class="auth-link">
            New to CineFlex?
            <a href="${pageContext.request.contextPath}/signup">Sign up now</a>
        </p>

        <div class="demo-credentials">
            <div>Demo credentials</div>
            <span>admin: admin / admin123</span>
            <span>user: user / user123</span>
        </div>
    </div>
</main>

<%@ include file="fragments/footer.jspf" %>
