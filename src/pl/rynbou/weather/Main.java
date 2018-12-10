package pl.rynbou.weather;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Datetime t = new Datetime();

        Reading.reloadReadings();
        List<Reading> lastReadings = new ArrayList<>();
        lastReadings.add(Reading.closestReading(Reading.Location.DOWNSTAIRS, t.getDate("UTC")));
        lastReadings.add(Reading.closestReading(Reading.Location.OUTSIDE, t.getDate("UTC")));
        lastReadings.add(Reading.closestReading(Reading.Location.UPSTAIRS, t.getDate("UTC")));

        for (Reading r : lastReadings) {
            System.out.println(r.getLocation());
            System.out.println(r.getTimestamp());
            System.out.println("T: " + r.getTemperature());
            if (r.getLocation().equals(Reading.Location.DOWNSTAIRS) || r.getLocation().equals(Reading.Location.UPSTAIRS))
                System.out.println("H: " + r.getHumidity());
            if (r.getLocation().equals(Reading.Location.UPSTAIRS))
                System.out.println("P: " + r.getPressure());
        }

        System.out.println(t.getDate(ZoneId.systemDefault()));

        //MainWindow.launch();
    }
}
