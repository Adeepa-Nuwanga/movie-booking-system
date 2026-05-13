<%@ page import="com.moviebooking.model.BookingRequest" %>
<%@ page import="java.util.List" %>
<%@ include file="fragments/header.jspf" %>
<%
    List<BookingRequest> pendingRequests = (List<BookingRequest>) request.getAttribute("pendingRequests");
    List<BookingRequest> processedRequests = (List<BookingRequest>) request.getAttribute("processedRequests");
    BookingRequest lastProcessed = (BookingRequest) request.getAttribute("lastProcessed");
%>

<main class="page-shell">
    <section class="content-section">
        <div class="container">
            <div class="admin-header">
                <div>
                    <span class="section-kicker">Admin Queue</span>
                    <h1>Booking Queue Monitor</h1>
                </div>
                <div class="queue-count">
                    Queue Length <strong><%= request.getAttribute("queueSize") %></strong>
                </div>
            </div>

            <div class="queue-grid">
                <section class="queue-panel">
                    <h2>Pending Requests</h2>
                    <% if (pendingRequests == null || pendingRequests.isEmpty()) { %>
                        <p>No pending requests.</p>
                    <% } else {
                        for (BookingRequest pending : pendingRequests) { %>
                            <div class="queue-item">
                                <strong><%= pending.getRequestId() %></strong>
                                <span><%= pending.getShowtimeId() %> | <%= String.join(", ", pending.getSelectedSeats()) %></span>
                                <em><%= pending.getStatus() %></em>
                            </div>
                        <% }
                    } %>
                </section>

                <section class="queue-panel">
                    <h2>Last Processed</h2>
                    <% if (lastProcessed == null) { %>
                        <p>No processed request yet.</p>
                    <% } else { %>
                        <div class="queue-item">
                            <strong><%= lastProcessed.getRequestId() %></strong>
                            <span><%= lastProcessed.getShowtimeId() %> | <%= String.join(", ", lastProcessed.getSelectedSeats()) %></span>
                            <em><%= lastProcessed.getStatus() %></em>
                        </div>
                    <% } %>
                </section>
            </div>

            <section class="queue-panel mt-4">
                <h2>Processed Requests</h2>
                <% if (processedRequests == null || processedRequests.isEmpty()) { %>
                    <p>No processed requests.</p>
                <% } else { %>
                    <div class="table-responsive">
                        <table class="table table-dark table-bordered queue-table">
                            <thead>
                            <tr>
                                <th>Request ID</th>
                                <th>Customer</th>
                                <th>Showtime</th>
                                <th>Seats</th>
                                <th>Total</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for (BookingRequest requestItem : processedRequests) { %>
                                <tr>
                                    <td><%= requestItem.getRequestId() %></td>
                                    <td><%= requestItem.getCustomerName() %></td>
                                    <td><%= requestItem.getShowtimeId() %></td>
                                    <td><%= String.join(", ", requestItem.getSelectedSeats()) %></td>
                                    <td>LKR <%= String.format("%.2f", requestItem.getTotalPrice()) %></td>
                                    <td><%= requestItem.getStatus() %></td>
                                </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                <% } %>
            </section>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
