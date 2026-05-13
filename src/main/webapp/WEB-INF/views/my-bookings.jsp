<%@ page import="com.moviebooking.model.Booking" %>
<%@ page import="java.util.List" %>
<%@ include file="fragments/header.jspf" %>
<%
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>

<main class="page-shell">
    <section class="content-section">
        <div class="container">
            <div class="page-heading">
                <span class="section-kicker">Booking History</span>
                <h1>My Bookings</h1>
            </div>

            <% if (bookings == null || bookings.isEmpty()) { %>
                <div class="empty-panel">
                    <h2>No bookings found</h2>
                    <p>Your confirmed bookings will appear here.</p>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/movies">Browse Movies</a>
                </div>
            <% } else { %>
                <div class="booking-list">
                    <% for (Booking booking : bookings) { %>
                        <article class="booking-card">
                            <div>
                                <span class="booking-id"><%= booking.getBookingId() %></span>
                                <h2><%= booking.getMovieTitle() %></h2>
                                <p><%= booking.getShowtimeDate() %> at <%= booking.getShowtimeTime() %> | <%= booking.getCinemaHall() %></p>
                            </div>
                            <div class="booking-meta">
                                <span>Seats <strong><%= String.join(", ", booking.getSeats()) %></strong></span>
                                <span>Status <strong><%= booking.getStatus() %></strong></span>
                                <span>Total <strong>LKR <%= String.format("%.2f", booking.getTotalPrice()) %></strong></span>
                            </div>
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/ticket?bookingId=<%= booking.getBookingId() %>">View Ticket</a>
                        </article>
                    <% } %>
                </div>
            <% } %>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
