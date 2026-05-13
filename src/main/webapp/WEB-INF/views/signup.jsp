<%@ include file="fragments/header.jspf" %>

<main class="login-page">
    <div class="login-card">
        <div class="login-brand">CineFlex</div>
        <h1>Create account</h1>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form method="post" action="${pageContext.request.contextPath}/signup">
            <div class="mb-3">
                <label for="name" class="form-label">Full name</label>
                <input type="text"
                       class="form-control"
                       id="name"
                       name="name"
                       value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>"
                       required>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text"
                       class="form-control"
                       id="username"
                       name="username"
                       value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>"
                       required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email"
                       class="form-control"
                       id="email"
                       name="email"
                       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                       required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirm password</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>
            <button type="submit" class="btn btn-danger w-100">Sign Up</button>
        </form>

        <p class="auth-link">
            Already have an account?
            <a href="${pageContext.request.contextPath}/login">Sign in</a>
        </p>
    </div>
</main>

<%@ include file="fragments/footer.jspf" %>
