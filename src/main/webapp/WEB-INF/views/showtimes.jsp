<%@ page import="com.moviebooking.model.Showtime" %>
<%@ page import="java.util.List" %>
<%@ include file="fragments/header.jspf" %>
<%
    List<Showtime> showtimes = (List<Showtime>) request.getAttribute("showtimes");
    String movieTitle = (String) request.getAttribute("movieTitle");
    String movieId = (String) request.getAttribute("movieId");
%>

<main class="page-shell">
    <section class="showtime-hero">
        <div class="container">
            <span class="section-kicker">Choose a Showtime</span>
            <h1><%= movieTitle != null ? movieTitle : "Selected Movie" %></h1>
            <p>Select a screening time, then pick your seats in the next step.</p>
        </div>
    </section>

    <section class="content-section">
        <div class="container">
            <% if (showtimes == null || showtimes.isEmpty()) { %>
                <div class="empty-panel">
                    <h2>No showtimes available</h2>
                    <p>There are no current screenings for <%= movieId != null ? movieId : "this movie" %>.</p>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/movies">Back to Movies</a>
                </div>
            <% } else { %>
                <div class="row g-4">
                    <% for (Showtime showtime : showtimes) { %>
                        <div class="col-12 col-md-6 col-lg-4">
                            <article class="showtime-card">
                                <div class="showtime-date"><%= showtime.getDate() %></div>
                                <h2><%= showtime.getTime() %></h2>
                                <p><%= showtime.getCinemaHall() %></p>
                                <a class="btn btn-danger w-100" href="${pageContext.request.contextPath}/seats?showtimeId=<%= showtime.getId() %>">
                                    Select Seats
                                </a>
                            </article>
                        </div>
                    <% } %>
                </div>
            <% } %>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
