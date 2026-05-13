<%@ page import="java.util.List" %>
<%@ include file="fragments/header.jspf" %>
<%
    List<String> selectedSeats = (List<String>) request.getAttribute("selectedSeats");
    double seatPrice = (Double) request.getAttribute("seatPrice");
    double totalPrice = (Double) request.getAttribute("totalPrice");
    boolean canConfirm = selectedSeats != null && !selectedSeats.isEmpty() && request.getAttribute("showtimeId") != null;
%>

<main class="page-shell">
    <section class="content-section">
        <div class="container">
            <div class="checkout-grid">
                <div class="summary-card">
                    <span class="section-kicker">Checkout</span>
                    <h1>Confirm Booking</h1>

                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <%= request.getAttribute("error") %>
                        </div>
                    <% } %>

                    <div class="summary-row">
                        <span>Movie</span>
                        <strong><%= request.getAttribute("movieId") %></strong>
                    </div>
                    <div class="summary-row">
                        <span>Showtime</span>
                        <strong><%= request.getAttribute("showtimeId") %></strong>
                    </div>
                    <div class="summary-row">
                        <span>Seats</span>
                        <strong><%= selectedSeats == null || selectedSeats.isEmpty() ? "None" : String.join(", ", selectedSeats) %></strong>
                    </div>
                    <div class="summary-row">
                        <span>Seat Price</span>
                        <strong>LKR <%= String.format("%.2f", seatPrice) %></strong>
                    </div>
                    <div class="summary-row total">
                        <span>Total</span>
                        <strong>LKR <%= String.format("%.2f", totalPrice) %></strong>
                    </div>
                </div>

                <form class="summary-card" method="post" action="${pageContext.request.contextPath}/checkout">
                    <span class="section-kicker">Customer Details</span>
                    <h2>Your Details</h2>
                    <div class="mb-3">
                        <label for="customerName" class="form-label">Customer Name</label>
                        <input type="text" class="form-control" id="customerName" name="customerName" required>
                    </div>
                    <div class="mb-3">
                        <label for="customerEmail" class="form-label">Customer Email</label>
                        <input type="email" class="form-control" id="customerEmail" name="customerEmail" required>
                    </div>
                    <button type="submit" class="btn btn-danger w-100" <%= canConfirm ? "" : "disabled" %>>Confirm Booking</button>
                    <a class="btn btn-outline-light w-100 mt-3" href="${pageContext.request.contextPath}/movies">Back</a>
                </form>
            </div>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
