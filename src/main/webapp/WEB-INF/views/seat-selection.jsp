<%@ page import="com.moviebooking.model.Seat" %>
<%@ page import="com.moviebooking.model.SeatStatus" %>
<%@ page import="com.moviebooking.model.Showtime" %>
<%@ include file="fragments/header.jspf" %>
<%
    Showtime showtime = (Showtime) request.getAttribute("showtime");
    String movieTitle = (String) request.getAttribute("movieTitle");
    double seatPrice = 1500.00;
%>

<main class="page-shell">
    <% if (showtime == null) { %>
        <section class="content-section">
            <div class="container">
                <div class="empty-panel">
                    <h1><%= request.getAttribute("message") %></h1>
                    <p>The selected showtime is not available.</p>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/movies">Back to Movies</a>
                </div>
            </div>
        </section>
    <% } else { %>
        <section class="content-section">
            <div class="container">
                <div class="seat-header">
                    <div>
                        <span class="section-kicker">Seat Selection</span>
                        <h1><%= movieTitle %></h1>
                        <p><%= showtime.getDate() %> at <%= showtime.getTime() %> | <%= showtime.getCinemaHall() %></p>
                    </div>
                </div>

                <div class="booking-steps">
                    <span>Pick a Movie</span>
                    <span>Your Details</span>
                    <span class="active">Pick a Seat</span>
                    <span>Summary</span>
                </div>

                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("error") %>
                    </div>
                <% } %>

                <form method="post" action="${pageContext.request.contextPath}/seats" class="seat-layout">
                    <input type="hidden" name="showtimeId" value="<%= showtime.getId() %>">

                    <div class="seat-map-panel">
                        <div class="screen">SCREEN</div>
                        <div class="seat-grid">
                            <% for (Seat seat : showtime.getSeatMap().getAllSeats()) {
                                boolean unavailable = SeatStatus.BOOKED.equals(seat.getStatus()) || SeatStatus.HELD.equals(seat.getStatus());
                                String cssClass = unavailable ? "seat unavailable" : "seat available";
                            %>
                                <label class="<%= cssClass %>">
                                    <input type="checkbox"
                                           name="selectedSeats"
                                           value="<%= seat.getSeatCode() %>"
                                           data-price="<%= seatPrice %>"
                                           <%= unavailable ? "disabled" : "" %>>
                                    <span><%= seat.getSeatCode() %></span>
                                </label>
                            <% } %>
                        </div>

                        <div class="seat-legend">
                            <span><i class="legend-box available"></i> Available</span>
                            <span><i class="legend-box selected"></i> Selected</span>
                            <span><i class="legend-box unavailable"></i> Booked/Held</span>
                        </div>
                    </div>

                    <aside class="summary-panel">
                        <span class="section-kicker">Summary</span>
                        <h2>Your Seats</h2>
                        <p id="selectedSeatText">No seats selected</p>
                        <div class="summary-row">
                            <span>Seat count</span>
                            <strong id="seatCount">0</strong>
                        </div>
                        <div class="summary-row">
                            <span>Total</span>
                            <strong>LKR <span id="totalPrice">0.00</span></strong>
                        </div>
                        <button type="submit" class="btn btn-danger w-100">Proceed</button>
                    </aside>
                </form>
            </div>
        </section>
    <% } %>
</main>

<script>
    (function () {
        var checkboxes = document.querySelectorAll('input[name="selectedSeats"]');
        var selectedSeatText = document.getElementById('selectedSeatText');
        var seatCount = document.getElementById('seatCount');
        var totalPrice = document.getElementById('totalPrice');

        function updateSummary() {
            var selected = [];
            var total = 0;

            checkboxes.forEach(function (checkbox) {
                var seatLabel = checkbox.closest('.seat');
                if (checkbox.checked) {
                    selected.push(checkbox.value);
                    total += Number(checkbox.dataset.price || 0);
                    seatLabel.classList.add('selected');
                } else {
                    seatLabel.classList.remove('selected');
                }
            });

            selectedSeatText.textContent = selected.length ? selected.join(', ') : 'No seats selected';
            seatCount.textContent = selected.length;
            totalPrice.textContent = total.toFixed(2);
        }

        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener('change', updateSummary);
        });
    })();
</script>

<%@ include file="fragments/footer.jspf" %>
