<%@ page import="com.moviebooking.model.Movie" %>
<%@ page import="java.util.List" %>
<%@ include file="fragments/header.jspf" %>
<%
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
    String selectedSort = (String) request.getAttribute("selectedSort");
    if (selectedSort == null) {
        selectedSort = "";
    }
%>

<main>
    <section class="movie-hero">
        <div class="container">
            <div class="hero-content">
                <span class="hero-kicker">Now Showing</span>
                <h1>Big screen stories, sorted your way.</h1>
                <p>Explore the latest CineFlex lineup and choose your next theatre night.</p>
                <a class="btn btn-danger" href="#movie-list">Browse Movies</a>
            </div>
        </div>
    </section>

    <section class="movie-section" id="movie-list">
        <div class="container">
            <div class="section-toolbar">
                <div>
                    <span class="section-kicker">Cinema Lineup</span>
                    <h2>Movies</h2>
                </div>
                <form class="sort-form" method="get" action="${pageContext.request.contextPath}/movies">
                    <label for="sort" class="form-label">Sort by</label>
                    <select class="form-select" id="sort" name="sort" onchange="this.form.submit()">
                        <option value="" <%= "".equals(selectedSort) ? "selected" : "" %>>Featured</option>
                        <option value="rating" <%= "rating".equals(selectedSort) ? "selected" : "" %>>Rating</option>
                        <option value="price" <%= "price".equals(selectedSort) ? "selected" : "" %>>Price</option>
                        <option value="duration" <%= "duration".equals(selectedSort) ? "selected" : "" %>>Duration</option>
                        <option value="title" <%= "title".equals(selectedSort) ? "selected" : "" %>>Title</option>
                    </select>
                </form>
            </div>

            <div class="row g-4">
                <% if (movies != null) {
                    for (Movie movie : movies) { %>
                        <div class="col-12 col-sm-6 col-lg-4">
                            <article class="movie-card">
                                <img class="movie-poster" src="<%= movie.getPosterUrl() %>" alt="<%= movie.getTitle() %> poster">
                                <div class="movie-card-body">
                                    <div class="movie-badge"><%= movie.getAgeRating() %></div>
                                    <h3><%= movie.getTitle() %></h3>
                                    <p class="movie-genre"><%= movie.getGenre() %></p>
                                    <div class="movie-meta">
                                        <span>Rating <strong><%= movie.getRating() %></strong></span>
                                        <span><%= movie.getDurationMinutes() %> min</span>
                                        <span>LKR <%= String.format("%.2f", movie.getPrice()) %></span>
                                    </div>
                                    <div class="movie-actions">
                                        <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/movie-details?id=<%= movie.getId() %>">View Details</a>
                                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/showtimes?movieId=<%= movie.getId() %>">Buy Tickets</a>
                                    </div>
                                </div>
                            </article>
                        </div>
                    <% }
                } %>
            </div>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
