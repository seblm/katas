package name.lemerdy;

public class Address {

    private String addressLine1;
    private String city;
    private String state;
    private AddressStatus status;
    private String zip;

    public AddressStatus getStatus() {
        return status;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStatus(AddressStatus status) {
        this.status = status;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
