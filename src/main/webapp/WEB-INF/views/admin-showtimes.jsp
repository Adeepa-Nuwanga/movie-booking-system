<%@ page import="com.moviebooking.model.Showtime" %>
<%@ page import="java.util.List" %>
<%@ include file="fragments/header.jspf" %>
<%
    List<Showtime> showtimes = (List<Showtime>) request.getAttribute("showtimes");
%>

<main class="admin-shell">
    <%@ include file="fragments/admin-nav.jspf" %>
    <section class="admin-content">
        <span class="section-kicker">Admin Panel</span>
        <h1>Showtimes</h1>
        <form class="admin-form" method="post" action="${pageContext.request.contextPath}/admin/showtimes">
            <input name="movieId" class="form-control" placeholder="Movie ID" required>
            <input name="cinemaHall" class="form-control" placeholder="Cinema Hall" required>
            <input name="date" class="form-control" placeholder="Date" required>
            <input name="time" class="form-control" placeholder="Time" required>
            <input name="rows" class="form-control" placeholder="Rows" type="number" value="6">
            <input name="columns" class="form-control" placeholder="Columns" type="number" value="8">
            <button class="btn btn-danger" type="submit">Add Showtime</button>
        </form>

        <div class="table-responsive">
            <table class="table table-dark table-bordered admin-table">
                <thead><tr><th>ID</th><th>Movie</th><th>Hall</th><th>Date</th><th>Time</th><th>Seats</th><th>Actions</th></tr></thead>
                <tbody>
                <% if (showtimes != null) {
                    for (Showtime showtime : showtimes) { %>
                        <tr>
                            <form method="post" action="${pageContext.request.contextPath}/admin/showtimes/update">
                                <td><input name="id" class="form-control" value="<%= showtime.getId() %>" readonly></td>
                                <td><input name="movieId" class="form-control" value="<%= showtime.getMovieId() %>"></td>
                                <td><input name="cinemaHall" class="form-control" value="<%= showtime.getCinemaHall() %>"></td>
                                <td><input name="date" class="form-control" value="<%= showtime.getDate() %>"></td>
                                <td><input name="time" class="form-control" value="<%= showtime.getTime() %>"></td>
                                <td>
                                    <input type="hidden" name="rows" value="<%= showtime.getSeatMap().getRows() %>">
                                    <input type="hidden" name="columns" value="<%= showtime.getSeatMap().getColumns() %>">
                                    <%= showtime.getSeatMap().getTotalSeats() %>
                                </td>
                                <td class="admin-actions">
                                    <button class="btn btn-outline-light btn-sm" type="submit">Edit</button>
                            </form>
                                    <form method="post" action="${pageContext.request.contextPath}/admin/showtimes/delete">
                                        <input type="hidden" name="id" value="<%= showtime.getId() %>">
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
