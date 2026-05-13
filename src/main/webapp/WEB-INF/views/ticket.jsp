<%@ page import="com.moviebooking.model.Booking" %>
<%@ include file="fragments/header.jspf" %>
<%
    Booking booking = (Booking) request.getAttribute("booking");
%>

<main class="page-shell">
    <section class="content-section">
        <div class="container">
            <% if (booking == null) { %>
                <div class="empty-panel">
                    <h1><%= request.getAttribute("message") %></h1>
                    <p>The ticket you requested is not available.</p>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/my-bookings">Back to My Bookings</a>
                </div>
            <% } else { %>
                <div class="ticket-actions">
                    <button class="btn btn-danger" type="button" onclick="window.print()">Print Ticket</button>
                    <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/my-bookings">My Bookings</a>
                    <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/movies">Movies</a>
                </div>

                <article class="ticket">
                    <div class="ticket-header">
                        <div>
                            <span class="section-kicker">E-Ticket</span>
                            <h1><%= booking.getMovieTitle() %></h1>
                        </div>
                        <div class="ticket-code"><%= booking.getBookingId() %></div>
                    </div>

                    <div class="ticket-grid">
                        <div>
                            <span>Customer</span>
                            <strong><%= booking.getCustomerName() %></strong>
                            <small><%= booking.getCustomerEmail() %></small>
                        </div>
                        <div>
                            <span>Date</span>
                            <strong><%= booking.getShowtimeDate() %></strong>
                        </div>
                        <div>
                            <span>Time</span>
                            <strong><%= booking.getShowtimeTime() %></strong>
                        </div>
                        <div>
                            <span>Cinema Hall</span>
                            <strong><%= booking.getCinemaHall() %></strong>
                        </div>
                        <div>
                            <span>Seats</span>
                            <strong><%= String.join(", ", booking.getSeats()) %></strong>
                        </div>
                        <div>
                            <span>Total Price</span>
                            <strong>LKR <%= String.format("%.2f", booking.getTotalPrice()) %></strong>
                        </div>
                    </div>

                    <div class="ticket-footer">
                        <span>Request ID: <%= booking.getRequestId() %></span>
                        <span>Confirmed: <%= booking.getConfirmedAt() %></span>
                        <span>Status: <%= booking.getStatus() %></span>
                    </div>
                </article>
            <% } %>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
