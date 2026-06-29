public class Cinema extends Building implements Taxable {
    private int numberOfScreens;
    private double monthlyRevenue;

    public Cinema(String name, String address, int numberOfFloors,
                  BuildingStatus status, int numberOfScreens, double monthlyRevenue)
            throws InvalidDataException {
        super(name, address, numberOfFloors, status);

        if (numberOfScreens <= 0) {
            throw new InvalidDataException("Number of screens must be greater than 0.");
        }
        if (monthlyRevenue < 0) {
            throw new InvalidDataException("Monthly revenue must not be negative.");
        }

        this.numberOfScreens = numberOfScreens;
        this.monthlyRevenue = monthlyRevenue;
    }

    @Override
    public void showBuildings() {
        System.out.println("========== CINEMA ==========");
        System.out.println("Name              : " + name);
        System.out.println("Address           : " + address);
        System.out.println("Number of Floors  : " + numberOfFloors);
        System.out.println("Status            : " + status);
        System.out.println("Number of Screens : " + numberOfScreens);
        System.out.println("Monthly Revenue   : Rp " + monthlyRevenue);
        System.out.println("Tax (10%)         : Rp " + calculateTax());
        System.out.println("============================");
    }

    @Override
    public double calculateTax() {
        return monthlyRevenue * BASE_TAX_RATE;
    }
}
