import java.sql.*;
public class MicroblogDatabase {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3307/db_microblog_3";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection conn;

    public MicroblogDatabase() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to db_microblog_3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertAuthor(String userName) {
        try {
            String query = "INSERT INTO messages (author) VALUES (username)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error while inserting author: " + ex.getMessage());
        }
    }

    public void insertContent( String userName, String contentMess) throws SQLException {
        String sql = "INSERT INTO messages (author, content) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, userName);
        statement.setString(2, contentMess);
        statement.executeUpdate();
    }


    public String getAuthor(long idMess) throws SQLException {
        String query = "SELECT author FROM messages WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setLong(1, idMess);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("author");
        } else {
            throw new SQLException("No author found for id " + idMess);
        }
    }




    public void close() throws SQLException {
        conn.close();
        System.out.println("Connection closed");
    }

    public static void main(String[] args) {
        try {
            MicroblogDatabase db = new MicroblogDatabase();

            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
