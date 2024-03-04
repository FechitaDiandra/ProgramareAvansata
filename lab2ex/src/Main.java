

class Vehicle {
    private String licensePlate;
    private String type;
    private int capacity;
   // constructor
    public Vehicle(String licensePlate, String type, int capacity) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.capacity = capacity;
    }
    //definesc metodele get si set pentru fiecare atribut al clasei
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //suprascriu clasa toString pt a fi mai usor de citit
    @Override
    public String toString() {
        return "Vehicle{" + "licensePlate='" + licensePlate + '\'' + ", type='" + type + '\'' + ", capacity=" + capacity + '}';
    }



}

class Depot {
    private String name;
    private String location;

    public Depot(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Depot{" + "name='" + name + '\'' + ", location='" + location + '\'' + '}';
    }


}

class  Client {
    private String name;
    private String address;
    private ClientType type;

    public Client(String name, String address, ClientType type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Client{" + "name='" + name + '\'' + ", address='" + address + '\'' + ", type=" + type + '}';
    }

}

public class Main {
    static public void main(String[] args) {
        Depot depot = new Depot("Tatarasi", "Rozelor 123");
        System.out.println(depot);

        Vehicle vehicle = new Vehicle("ABC123", "Camioneta", 5000);
        System.out.println(vehicle);

        Client client1 = new Client("Dalida", "456 Fundatura Rozelor", ClientType.REGULAR);
        System.out.println(client1);

        Client client2 = new Client("Vlad", "34 Anton Pan", ClientType.PREMIUM);
        System.out.println(client2);
    }
}