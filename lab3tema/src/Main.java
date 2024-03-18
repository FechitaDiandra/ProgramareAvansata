import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Creăm atracții
        Attraction statue = new Attraction("Statue") {
            @Override
            public <U> U getOpeningHour(LocalDate now) {
                return null;
            }

            @Override
            public void pay(double amount) {

            }

            @Override
            public Map<LocalDate, TimeInterval<LocalTime>> getVisitingTimetable() {
                Map<LocalDate, TimeInterval<LocalTime>> timetable = new HashMap<>();
                timetable.put(LocalDate.of(2024, 3, 17), new TimeInterval<>(LocalTime.of(9, 0), LocalTime.of(17, 0)));
                return timetable;
            }

            @Override
            public LocalTime getOpeningHoursByDate(LocalDate date) {
                return super.getOpeningHoursByDate(date);
            }
        };

        Attraction church = new Church("Church") {
            @Override
            public <U> U getOpeningHour(LocalDate now) {
                return null;
            }

            @Override
            public void pay(double amount) {

            }

            @Override
            public Map<LocalDate, TimeInterval<LocalTime>> getVisitingTimetable() {
                Map<LocalDate, TimeInterval<LocalTime>> timetable = new HashMap<>();
                timetable.put(LocalDate.of(2024, 3, 18), new TimeInterval<>(LocalTime.of(10, 0), LocalTime.of(20, 0)));
                return timetable;
            }
        };

        Attraction concert = new Concert("Concert", 50.0) {
            @Override
            public void pay(double amount) {

            }

            @Override
            public Map<LocalDate, TimeInterval<LocalTime>> getVisitingTimetable() {
                Map<LocalDate, TimeInterval<LocalTime>> timetable = new HashMap<>();
                timetable.put(LocalDate.of(2024, 3, 19), new TimeInterval<>(LocalTime.of(20, 0), LocalTime.of(4, 0)));
                return timetable;
            }

            @Override
            public boolean isPayable() {
                return true; // Concertul este payable
            }

            @Override
            public <U> U getOpeningHour(LocalDate now) {
                return null;
            }
        };

        // Creăm un plan de călătorie
        TravelPlan travelPlan = new TravelPlan();
        travelPlan.addVisit(LocalDate.of(2024, 3, 17), statue);
        travelPlan.addVisit(LocalDate.of(2024, 3, 18), church);
        travelPlan.addVisit(LocalDate.of(2024, 3, 19), concert);

        // Afișăm planul de călătorie pe ecran pentru prima afișare (inclusiv concertul)
        travelPlan.printPlan(true);

        // Afișăm planul de călătorie pe ecran pentru a doua afișare (fără concert)
        travelPlan.printPlan(false);
    }
}
