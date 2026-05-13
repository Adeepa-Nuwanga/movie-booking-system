<%@ include file="fragments/header.jspf" %>

<main class="admin-shell">
    <%@ include file="fragments/admin-nav.jspf" %>
    <section class="admin-content">
        <span class="section-kicker">Admin Panel</span>
        <h1>Dashboard</h1>
        <div class="stat-grid">
            <div class="stat-card"><span>Total Movies</span><strong><%= request.getAttribute("totalMovies") %></strong></div>
            <div class="stat-card"><span>Total Showtimes</span><strong><%= request.getAttribute("totalShowtimes") %></strong></div>
            <div class="stat-card"><span>Queue Length</span><strong><%= request.getAttribute("queueLength") %></strong></div>
            <div class="stat-card"><span>Recent Bookings</span><strong><%= request.getAttribute("recentBookingsCount") %></strong></div>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
