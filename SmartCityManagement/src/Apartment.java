public class Apartment extends Building implements Taxable {
    private int numberOfUnits;
    private double monthlyRevenue;

    public Apartment(String name, String address, int numberOfFloors,
                     BuildingStatus status, int numberOfUnits, double monthlyRevenue)
            throws InvalidDataException {
        super(name, address, numberOfFloors, status);

        if (numberOfUnits <= 0) {
            throw new InvalidDataException("Number of units must be greater than 0.");
        }
        if (monthlyRevenue < 0) {
            throw new InvalidDataException("Monthly revenue must not be negative.");
        }

        this.numberOfUnits = numberOfUnits;
        this.monthlyRevenue = monthlyRevenue;
    }

    @Override
    public void showBuildings() {
        System.out.println("========== APARTMENT ==========");
        System.out.println("Name             : " + name);
        System.out.println("Address          : " + address);
        System.out.println("Number of Floors : " + numberOfFloors);
        System.out.println("Status           : " + status);
        System.out.println("Number of Units  : " + numberOfUnits);
        System.out.println("Monthly Revenue  : Rp " + monthlyRevenue);
        System.out.println("Tax (10%)        : Rp " + calculateTax());
        System.out.println("===============================");
    }

    @Override
    public double calculateTax() {
        return monthlyRevenue * BASE_TAX_RATE;
    }
}
