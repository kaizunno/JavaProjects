public abstract class Building {
    protected String name;
    protected String address;
    protected int numberOfFloors;
    protected BuildingStatus status;

    public Building(String name, String address, int numberOfFloors, BuildingStatus status)
            throws InvalidDataException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Building name must not be empty.");
        }

        if (numberOfFloors <= 0) {
            throw new InvalidDataException("Number of floors must be greater than 0.");
        }

        this.name = name;
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.status = status;
    }

    public abstract void showBuildings();

    public String getName()         { return name; }
    public String getAddress()      { return address; }
    public int getNumberOfFloors()  { return numberOfFloors; }
    public BuildingStatus getStatus() { return status; }
}
