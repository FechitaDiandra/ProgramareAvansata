/**
 * Represents a church that can be visited and may require an entrance fee.
 */
class Church implements Visitable, Payable {
    private String name;
    private double entranceFee;

    /**
     * Constructs a Church object with the given name and entrance fee.
     *
     * @param name        The name of the church.
     * @param entranceFee The entrance fee for the church.
     */
    public Church(String name, double entranceFee) {
        this.name = name;
        this.entranceFee = entranceFee;
    }

    @Override
    public void visit() {
        System.out.println("Visited Church: " + name);
    }

    @Override
    public double getEntranceFee() {
        return entranceFee;
    }

    /**
     * Retrieves the name of the church.
     *
     * @return The name of the church.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the church.
     *
     * @param name The name of the church.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the entrance fee for the church.
     *
     * @param entranceFee The entrance fee for the church.
     */
    public void setEntranceFee(double entranceFee) {
        this.entranceFee = entranceFee;
    }
}
