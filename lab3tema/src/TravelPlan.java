import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TravelPlan {
    private final List<AttractionEntry> plan;

    public TravelPlan() {
        plan = new ArrayList<>();
    }

    public void addVisit(LocalDate date, Attraction attraction) {
        plan.add(new AttractionEntry(date, attraction));
    }

    public void printPlan(boolean includeConcert) {
        System.out.println("Travel Plan:");
        //parcurge fiecare intrare din planul de calatorie
        for (AttractionEntry entry : plan) {
            //obtin data si atractia din fiecare intrare
            LocalDate date = entry.getDate();
            Attraction attraction = entry.getAttraction();
            String formattedDate = date.toString();
            //orar de vizitare si are informatii despre data respectiva
            //program de vizitare obtinut
            Map<LocalDate, TimeInterval<LocalTime>> visitingTimetable = attraction.getVisitingTimetable();
            if (visitingTimetable != null && visitingTimetable.containsKey(date)) {
                TimeInterval<LocalTime> interval = visitingTimetable.get(date);
                String visitTime = String.format("%02d:%02d - %02d:%02d",
                        interval.getStart().getHour(), interval.getStart().getMinute(),
                        interval.getEnd().getHour(), interval.getEnd().getMinute());
                String visitableStatus = attraction.isVisitable() ? "(Visitable)" : "(Not Visitable)";
                String payableStatus = attraction.isPayable() ? "(Payable)" : "(Not Payable)";
                //afiseaza informatiile despre vizita
                if (attraction instanceof Concert && includeConcert) {
                    Concert concert = (Concert) attraction;
                    System.out.printf("%s: Visit %s %s %s (Visit Time: %s) (Price: %.2f)%n",
                            formattedDate, attraction.getName(), visitableStatus, payableStatus, visitTime, concert.getPrice());
                } else if (!(attraction instanceof Concert)) {
                    System.out.printf("%s: Visit %s %s %s (Visit Time: %s)%n",
                            formattedDate, attraction.getName(), visitableStatus, payableStatus, visitTime);
                }
            } else {
                System.out.println(formattedDate + ": No visiting timetable available for " + attraction.getName());
            }
        }
    }

    private static class AttractionEntry {
        private final LocalDate date;
        private final Attraction attraction;

        public AttractionEntry(LocalDate date, Attraction attraction) {
            this.date = date;
            this.attraction = attraction;
        }

        public LocalDate getDate() {
            return date;
        }

        public Attraction getAttraction() {
            return attraction;
        }
    }
}
