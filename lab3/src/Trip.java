import java.util.ArrayList;
import java.util.List;

/**
 * Represents a trip to a city during a specific time period, with a list of visitable attractions.
 */
class Trip {
    private String cityName;
    private String timePeriod;
    private List<Visitable> attractions;

    /**
     * Constructs a Trip object with the given city name and time period.
     *
     * @param cityName   The name of the city.
     * @param timePeriod The time period of the trip.
     */
    public Trip(String cityName, String timePeriod) {
        this.cityName = cityName;
        this.timePeriod = timePeriod;
        this.attractions = new ArrayList<>();
    }

    /**
     * Adds a visitable attraction to the trip.
     *
     * @param attraction The visitable attraction to add.
     */
    public void addAttraction(Visitable attraction) {
        attractions.add(attraction);
    }

    /**
     * Retrieves the list of visitable attractions for the trip.
     *
     * @return The list of visitable attractions.
     */
    public List<Visitable> getAttractions() {
        return attractions;
    }

    /**
     * Retrieves the name of the city for the trip.
     *
     * @return The name of the city.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the name of the city for the trip.
     *
     * @param cityName The name of the city.
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Retrieves the time period of the trip.
     *
     * @return The time period of the trip.
     */
    public String getTimePeriod() {
        return timePeriod;
    }

    /**
     * Sets the time period of the trip.
     *
     * @param timePeriod The time period of the trip.
     */
    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    // No need for getter and setter for the list of attractions

    @Override
    public String toString() {
        return "Trip{" +
                "cityName='" + cityName + '\'' +
                ", timePeriod='" + timePeriod + '\'' +
                ", attractions=" + attractions +
                '}';
    }
}
