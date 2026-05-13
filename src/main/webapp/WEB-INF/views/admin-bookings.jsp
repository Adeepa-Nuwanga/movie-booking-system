<%@ page import="com.moviebooking.model.AdminBookingSummary" %>
<%@ page import="java.util.List" %>
<%@ include file="fragments/header.jspf" %>
<%
    List<AdminBookingSummary> bookings = (List<AdminBookingSummary>) request.getAttribute("bookings");
%>

<main class="admin-shell">
    <%@ include file="fragments/admin-nav.jspf" %>
    <section class="admin-content">
        <span class="section-kicker">Admin Panel</span>
        <h1>Bookings</h1>
        <p class="admin-note">Queue length: <strong><%= request.getAttribute("queueLength") %></strong>. Queue monitor can be linked when Component 04 is merged.</p>
        <div class="table-responsive">
            <table class="table table-dark table-bordered admin-table">
                <thead><tr><th>Booking ID</th><th>Customer</th><th>Movie</th><th>Seats</th><th>Status</th><th>Total</th></tr></thead>
                <tbody>
                <% if (bookings != null) {
                    for (AdminBookingSummary booking : bookings) { %>
                        <tr>
                            <td><%= booking.getBookingId() %></td>
                            <td><%= booking.getCustomerName() %></td>
                            <td><%= booking.getMovieTitle() %></td>
                            <td><%= String.join(", ", booking.getSeats()) %></td>
                            <td><%= booking.getStatus() %></td>
                            <td>LKR <%= String.format("%.2f", booking.getTotalPrice()) %></td>
                        </tr>
                    <% }
                } %>
                </tbody>
            </table>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
