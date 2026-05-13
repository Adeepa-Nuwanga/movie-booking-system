<%@ page import="com.moviebooking.model.BookingRequest" %>
<%@ page import="com.moviebooking.model.BookingStatus" %>
<%@ include file="fragments/header.jspf" %>
<%
    BookingRequest bookingRequest = (BookingRequest) request.getAttribute("bookingRequest");
%>

<main class="page-shell">
    <section class="content-section">
        <div class="container">
            <div class="result-card">
                <% if (bookingRequest == null) { %>
                    <span class="section-kicker">Booking Result</span>
                    <h1>Request not found</h1>
                    <p><%= request.getAttribute("message") %></p>
                <% } else if (BookingStatus.CONFIRMED.equals(bookingRequest.getStatus())) { %>
                    <span class="section-kicker">Booking Confirmed</span>
                    <h1>Your seats are confirmed.</h1>
                    <p>Request ID: <strong><%= bookingRequest.getRequestId() %></strong></p>
                    <p>Seats: <strong><%= String.join(", ", bookingRequest.getSelectedSeats()) %></strong></p>
                    <p>Total: <strong>LKR <%= String.format("%.2f", bookingRequest.getTotalPrice()) %></strong></p>
                <% } else { %>
                    <span class="section-kicker">Booking Rejected</span>
                    <h1>We could not confirm this booking.</h1>
                    <p>Request ID: <strong><%= bookingRequest.getRequestId() %></strong></p>
                    <p>Reason: <strong><%= bookingRequest.getRejectionReason() %></strong></p>
                <% } %>

                <div class="result-actions">
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/my-bookings">My Bookings</a>
                    <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/movies">Movies</a>
                </div>
            </div>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
