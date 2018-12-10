package pl.rynbou.weather;

import java.sql.*;

public class DatabaseController {
    private Connection conn;

    private synchronized void openConnection() {
        if (!isConnected()) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://192.168.0.170:3306/weather?user=view-only&password=letmein");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void closeConnection() {
        if (isConnected()) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        try {
            if (conn == null) return false;
            if (conn.isClosed()) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void select(String query, Reading.Location location) throws SQLException {
        openConnection();
        ResultSet rs = conn.createStatement().executeQuery(query);
        while (rs.next()) {
            float temp = rs.getFloat("temperature");
            Timestamp date = rs.getTimestamp("datetime");
            if (location.equals(Reading.Location.OUTSIDE)) {
                new Reading(location, temp, date);
                continue;
            }
            int hum = rs.getInt("humidity");
            if (location.equals(Reading.Location.DOWNSTAIRS)) {
                new Reading(location, temp, true, hum, date);
                continue;
            }
            int pressure = rs.getInt("pressure");
            new Reading(location, temp, true, hum, true, pressure, date);
        }
        closeConnection();
    }
}
