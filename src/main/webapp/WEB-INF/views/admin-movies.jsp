<%@ page import="com.moviebooking.model.Movie" %>
<%@ page import="java.util.List" %>
<%@ include file="fragments/header.jspf" %>
<%
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
%>

<main class="admin-shell">
    <%@ include file="fragments/admin-nav.jspf" %>
    <section class="admin-content">
        <span class="section-kicker">Admin Panel</span>
        <h1>Movies</h1>

        <form class="admin-form" method="post" action="${pageContext.request.contextPath}/admin/movies">
            <input name="title" class="form-control" placeholder="Title" required>
            <input name="genre" class="form-control" placeholder="Genre" required>
            <input name="rating" class="form-control" placeholder="Rating" type="number" step="0.1">
            <input name="durationMinutes" class="form-control" placeholder="Duration" type="number">
            <input name="price" class="form-control" placeholder="Price" type="number" step="0.01">
            <input name="posterUrl" class="form-control" placeholder="Poster URL">
            <input name="bannerUrl" class="form-control" placeholder="Banner URL">
            <input name="ageRating" class="form-control" placeholder="Age Rating">
            <textarea name="description" class="form-control" placeholder="Description"></textarea>
            <button class="btn btn-danger" type="submit">Add Movie</button>
        </form>

        <div class="table-responsive">
            <table class="table table-dark table-bordered admin-table">
                <thead><tr><th>ID</th><th>Title</th><th>Genre</th><th>Rating</th><th>Duration</th><th>Price</th><th>Actions</th></tr></thead>
                <tbody>
                <% if (movies != null) {
                    for (Movie movie : movies) { %>
                        <tr>
                            <form method="post" action="${pageContext.request.contextPath}/admin/movies/update">
                                <td><input name="id" class="form-control" value="<%= movie.getId() %>" readonly></td>
                                <td><input name="title" class="form-control" value="<%= movie.getTitle() %>"></td>
                                <td><input name="genre" class="form-control" value="<%= movie.getGenre() %>"></td>
                                <td><input name="rating" class="form-control" type="number" step="0.1" value="<%= movie.getRating() %>"></td>
                                <td><input name="durationMinutes" class="form-control" type="number" value="<%= movie.getDurationMinutes() %>"></td>
                                <td><input name="price" class="form-control" type="number" step="0.01" value="<%= movie.getPrice() %>"></td>
                                <td class="admin-actions">
                                    <input type="hidden" name="description" value="<%= movie.getDescription() %>">
                                    <input type="hidden" name="posterUrl" value="<%= movie.getPosterUrl() %>">
                                    <input type="hidden" name="bannerUrl" value="<%= movie.getBannerUrl() %>">
                                    <input type="hidden" name="ageRating" value="<%= movie.getAgeRating() %>">
                                    <button class="btn btn-outline-light btn-sm" type="submit">Edit</button>
                            </form>
                                    <form method="post" action="${pageContext.request.contextPath}/admin/movies/delete">
                                        <input type="hidden" name="id" value="<%= movie.getId() %>">
                                        <button class="btn btn-danger btn-sm" type="submit">Delete</button>
                                    </form>
                                </td>
                        </tr>
                    <% }
                } %>
                </tbody>
            </table>
        </div>
    </section>
</main>

<%@ include file="fragments/footer.jspf" %>
