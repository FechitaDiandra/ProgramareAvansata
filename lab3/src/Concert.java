/**
 * Represents a concert event that requires payment for entrance.
 */
class Concert implements Payable {
    private String name;
    private double ticketPrice;

    /**
     * Constructs a Concert object with the given name and ticket price.
     *
     * @param name        The name of the concert.
     * @param ticketPrice The price of a ticket for the concert.
     */
    public Concert(String name, double ticketPrice) {
        this.name = name;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public double getEntranceFee() {
        return ticketPrice;
    }

    /**
     * Retrieves the name of the concert.
     *
     * @return The name of the concert.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the concert.
     *
     * @param name The name of the concert.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the ticket price for the concert.
     *
     * @return The ticket price for the concert.
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets the ticket price for the concert.
     *
     * @param ticketPrice The ticket price for the concert.
     */
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
