import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SearchOfferResult.fromOfferIds(
                Arrays.asList("offer_id_1", "offer_id_2", "offer_id_3")
        );

        Driver plainDriver = Driver.getPlainDriver(new Plain("Самолет №5"));
        Driver carDriver = Driver.getCarDriver(new Car("A781CT"));
        Driver trolleyDriver = Driver.getTrolleyDriver(new Trolley("Троллейбус №6"));
    }
}

class SearchOfferResult {
    private final List<String> offerIds;

    private SearchOfferResult(List<String> offerIds) {
        this.offerIds = offerIds;
    }

    public static SearchOfferResult fromOfferIds(final List<String> offerIds) {
        return new SearchOfferResult(offerIds);
    }

    public List<String> getOfferIds() {
        return offerIds;
    }
}

class Driver {
    private String managementTransportLicense;

    private Driver(final String managementTransportLicense) {
        this.managementTransportLicense = managementTransportLicense;
    }

    public static Driver getCarDriver(Car car) {
        return new Driver("License to drive a car. " + "Number the car: " + car.getCarNumber());
    }

    public static Driver getPlainDriver(Plain plain) {
        return new Driver("License to fly an airplane. " + "Number the plain: " + plain.getPlainNumber());
    }

    public static Driver getTrolleyDriver(Trolley trolley) {
        return new Driver("License to drive a trolley. " + "Number thr trolley: " + trolley.getTrolleyNumber());
    }

    public String getManagementTransportLicense() {
        return managementTransportLicense;
    }
}

class Car  {
    private String number;

    public Car(final String carNumber) {
        number = carNumber;
    }

    public String getCarNumber() {
        return number;
    }
}

class Plain {
    private String number;

    public Plain(final String plainNumber) {
        number = plainNumber;
    }

    public String getPlainNumber() {
        return number;
    }
}

class Trolley {
    private String number;

    public Trolley(final String trolleyNumber) {
        number = trolleyNumber;
    }

    public String getTrolleyNumber() {
        return number;
    }
}