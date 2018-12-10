package pl.rynbou.weather;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Reading {
    private static List<Reading> allReadings = new ArrayList<>();

    public static void reloadReadings() {
        allReadings = new ArrayList<>();
        try {
            new DatabaseController().select("SELECT * FROM outside ORDER BY id DESC", Reading.Location.OUTSIDE);
            new DatabaseController().select("SELECT * FROM upstairs ORDER BY id DESC", Reading.Location.UPSTAIRS);
            new DatabaseController().select("SELECT * FROM downstairs ORDER BY id DESC", Reading.Location.DOWNSTAIRS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Reading> getAllReadings() {
        return allReadings;
    }

    public static List<Reading> getAllReadings(Location location, Timestamp date) {
        List<Reading> toReturn = new ArrayList<>();
        for (Reading r : allReadings) {
            if (r.getLocation().equals(location) && r.getTimestamp().equals(date))
                toReturn.add(r);
        }
        return toReturn;
    }

    public static List<Reading> getAllReadings(Location location) {
        List<Reading> toReturn = new ArrayList<>();
        for (Reading r : allReadings) {
            if (r.getLocation().equals(location))
                toReturn.add(r);
        }
        return toReturn;
    }

    public static Reading closestReading(Location location, Timestamp date) {
        List<Timestamp> dates = new ArrayList<>();
        Timestamp closest = new Timestamp(0);
        Timestamp previous = new Timestamp(0);
        for (Reading r : allReadings) {
            if (r.getLocation().equals(location))
                dates.add(r.getTimestamp());
        }
        for (Timestamp d : dates) {
            if (Math.abs(d.getTime() - date.getTime()) < Math.abs(previous.getTime() - date.getTime()))
                closest = d;
            previous = d;
        }
        List<Reading> result = getAllReadings(location, closest);
        return result.get(0);
    }

    private Location location;
    private float temp;
    private boolean hasHumidity;
    private float humidity;
    private boolean hasPressure;
    private int pressure;
    private Timestamp timestamp;

    public Reading(Location location, float temp, boolean hasHumidity, float humidity, boolean hasPressure, int pressure, Timestamp timestamp) {
        this.temp = temp;
        this.hasHumidity = hasHumidity;
        this.humidity = humidity;
        this.hasPressure = hasPressure;
        this.pressure = pressure;
        this.timestamp = timestamp;
        this.location = location;

        allReadings.add(this);
    }

    public Reading(Location location, float temp, boolean hasHumidity, float humidity, Timestamp timestamp) {
        this.temp = temp;
        this.hasHumidity = hasHumidity;
        this.humidity = humidity;
        this.timestamp = timestamp;
        this.location = location;

        allReadings.add(this);
    }

    public Reading(Location location, float temp, Timestamp timestamp) {
        this.temp = temp;
        this.timestamp = timestamp;
        this.location = location;

        allReadings.add(this);
    }

    public static void setAllReadings(List<Reading> allReadings) {
        Reading.allReadings = allReadings;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getTemperature() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public boolean hasHumidity() {
        return hasHumidity;
    }

    public void setHasHumidity(boolean hasHumidity) {
        this.hasHumidity = hasHumidity;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public boolean hasPressure() {
        return hasPressure;
    }

    public void setHasPressure(boolean hasPressure) {
        this.hasPressure = hasPressure;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public enum Location {
        DOWNSTAIRS,
        UPSTAIRS,
        OUTSIDE
    }
}
