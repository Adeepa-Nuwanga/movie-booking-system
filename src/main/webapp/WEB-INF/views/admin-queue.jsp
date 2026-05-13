<%@ include file="fragments/header.jspf" %>

<main class="admin-shell">
    <%@ include file="fragments/admin-nav.jspf" %>
    <section class="admin-content">
        <span class="section-kicker">Admin Panel</span>
        <h1>Queue Monitor</h1>
        <div class="stat-grid">
            <div class="stat-card"><span>Queue Length</span><strong><%= request.getAttribute("queueLength") %></strong></div>
        </div>
        <p class="admin-note mt-4">Booking queue details will appear here when Component 04 is merged.</p>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
