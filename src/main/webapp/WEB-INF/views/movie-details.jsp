<%@ page import="com.moviebooking.model.Movie" %>
<%@ include file="fragments/header.jspf" %>
<%
    Movie movie = (Movie) request.getAttribute("movie");
%>

<main>
    <% if (movie == null) { %>
        <section class="not-found-section">
            <div class="container">
                <div class="not-found-panel">
                    <h1><%= request.getAttribute("message") %></h1>
                    <p>The movie you selected is not available in the current lineup.</p>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/movies">Back to Movies</a>
                </div>
            </div>
        </section>
    <% } else { %>
        <section class="details-hero" style="background-image: linear-gradient(90deg, rgba(0,0,0,.96), rgba(0,0,0,.72), rgba(0,0,0,.35)), url('<%= movie.getBannerUrl() %>');">
            <div class="container">
                <div class="details-grid">
                    <img class="details-poster" src="<%= movie.getPosterUrl() %>" alt="<%= movie.getTitle() %> poster">
                    <div class="details-content">
                        <span class="hero-kicker"><%= movie.getGenre() %> | <%= movie.getAgeRating() %></span>
                        <h1><%= movie.getTitle() %></h1>
                        <p class="details-description"><%= movie.getDescription() %></p>
                        <div class="details-meta">
                            <span>Rating <strong><%= movie.getRating() %></strong></span>
                            <span><%= movie.getDurationMinutes() %> min</span>
                            <span>LKR <%= String.format("%.2f", movie.getPrice()) %></span>
                        </div>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/showtimes?movieId=<%= movie.getId() %>">Buy Tickets</a>
                    </div>
                </div>
            </div>
        </section>

        <section class="movie-section">
            <div class="container">
                <div class="showtime-placeholder">
                    <span class="section-kicker">Showtimes</span>
                    <h2>Available showtimes will appear here</h2>
                    <p>This area is reserved for the showtime and seat selection component.</p>
                </div>
            </div>
        </section>
    <% } %>
</main>

<%@ include file="fragments/footer.jspf" %>
