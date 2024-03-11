public class Main {
    public static void main(String[] args) {
        // Crearea unei călătorii
        Trip trip = new Trip("Paris", "Vara");

        // Adăugarea atracțiilor în călătorie
        Statue eiffelTower = new Statue("Turnul Eiffel");
        trip.addAttraction(eiffelTower);

        Church notreDame = new Church("Notre Dame", 10.0);
        trip.addAttraction(notreDame);

        Concert concert = new Concert("Concert Rock", 25.0);

        // Vizitarea atracțiilor din călătorie
        for (Visitable attraction : trip.getAttractions()) {
            attraction.visit();
        }

        // Obținerea prețului de intrare pentru un concert
        System.out.println("Pretul pentru " + concert.getName() + ": $" + concert.getEntranceFee());
    }
}
