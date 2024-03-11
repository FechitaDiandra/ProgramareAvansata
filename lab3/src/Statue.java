/**
 * Represents a statue that can be visited.
 */
class Statue implements Visitable {
    private String name;

    /**
     * Constructs a Statue object with the given name.
     *
     * @param name The name of the statue.
     */
    public Statue(String name) {
        this.name = name;
    }

    /**
     * Visits the statue.
     */
    @Override
    public void visit() {
        System.out.println("Visited Statue: " + name);
    }

    /**
     * Retrieves the name of the statue.
     *
     * @return The name of the statue.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the statue.
     *
     * @param name The name of the statue.
     */
    public void setName(String name) {
        this.name = name;
    }
}
