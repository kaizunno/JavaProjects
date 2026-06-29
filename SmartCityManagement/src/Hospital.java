public class Hospital extends Building {
    private int numberOfBeds;

    public Hospital(String name, String address, int numberOfFloors,
                    BuildingStatus status, int numberOfBeds)
            throws InvalidDataException {
        super(name, address, numberOfFloors, status);

        if (numberOfBeds <= 0) {
            throw new InvalidDataException("Number of beds must be greater than 0.");
        }

        this.numberOfBeds = numberOfBeds;
    }

    @Override
    public void showBuildings() {
        System.out.println("========== HOSPITAL ==========");
        System.out.println("Name             : " + name);
        System.out.println("Address          : " + address);
        System.out.println("Number of Floors : " + numberOfFloors);
        System.out.println("Status           : " + status);
        System.out.println("Number of Beds   : " + numberOfBeds);
        System.out.println("==============================");
    }
}
