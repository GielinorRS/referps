package mysql.impl;

import java.sql.*;
import java.time.ZonedDateTime;

public class InsertDono implements Runnable {

    public static final String HOST = "104.161.43.58";
    public static final String USER = "pilfirgk_store";
    public static final String PASS = "LOVj,6(wlM]k";
    public static final String DATABASE = "pilfirgk_store";

    private Connection conn;
    private Statement stmt;

    private int itemNumber, quantity;
    private String buyer, method, username;

    public InsertDono(int itemNumber, int quantity, String username, String buyer, String method) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.username = username;
        this.buyer = buyer;
        this.method = method;
    }

    public boolean connect(String host, String database, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, pass);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            ResultSet results = stmt.executeQuery(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        try {
            if (!connect("162.241.194.107", "lunite96_store", "lunite96_store", "Cbt!cE4R7F^v691dHDXE@G18CE!b2wqB")) {
                return;
            }
            ResultSet rs = executeQuery(
                    "SELECT * FROM products WHERE item_id=" + itemNumber);
            String name = "N/A";
            int totalAmount = 0;
            while (rs.next()) {
                name = rs.getString("item_name");
                totalAmount = rs.getInt("item_price");
            }

            PreparedStatement stmt2 = prepare(generateQuery());
            Timestamp timestamp = Timestamp.from(ZonedDateTime.now().toInstant());

            stmt2.setString(1, name);
            stmt2.setInt(2, itemNumber);
            stmt2.setString(3, "Completed");
            stmt2.setInt(4, totalAmount * quantity);
            stmt2.setInt(5, quantity);
            stmt2.setString(6, "USD");
            stmt2.setString(7, buyer);
            stmt2.setString(8, method);
            stmt2.setTimestamp(9, timestamp);
            stmt2.setString(10, username);
            stmt2.setInt(11, 0);

            stmt2.execute();
            System.out.println("INSERTED DONO");

            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO payments (");
        sb.append("item_name, ");
        sb.append("item_number, ");
        sb.append("status, ");
        sb.append("amount, ");
        sb.append("quantity, ");
        sb.append("currency, ");
        sb.append("buyer, ");
        sb.append("receiver, ");
        sb.append("dateline, ");
        sb.append("player_name, ");
        sb.append("claimed) ");
        sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return sb.toString();
    }


    public PreparedStatement prepare(String query) throws SQLException {
        return conn.prepareStatement(query);
    }

    public void destroy() {
        try {
            conn.close();
            conn = null;
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

