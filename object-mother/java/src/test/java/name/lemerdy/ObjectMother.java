package name.lemerdy;

import java.time.LocalDate;

public final class ObjectMother {

    public static void attachInvoiceLineAsCharge(Invoice invoice, Money money) {
        InvoiceLine invoiceLine = createInvoiceLineAsCharge(money);
        invoiceLine.setInvoice(invoice);
    }

    public static Address createAddress() {
        return createAddress("Chicago", "IL", "60647");
    }

    public static Address createAddress(String city, String state, String zip) {
        var address = new Address();
        address.setAddressLine1("1011 Bit Lane");
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        address.setStatus(AddressStatus.ACTIVE);
        return address;
    }

    public static Address createInactiveAddress() {
        var address = createAddress();
        address.setStatus(AddressStatus.INACTIVE);
        return address;
    }

    private static InvoiceLine createInvoiceLineAsCharge(Money money) {
        return new InvoiceLine(InvoiceLineType.CHARGE, money);
    }

    public static Invoice createNewInvoice() {
        var invoice = new Invoice();
        invoice.setInvoiceNumber("InvTest001");
        Address address = createAddress();
        invoice.setBillToAddress(address);
        attachInvoiceLineAsCharge(invoice, new Money("4999.95", "USD"));
        invoice.setStatus(InvoiceStatus.NEW);
        return invoice;
    }

    public static void makeGenerated(Invoice invoice) {
        invoice.setGeneratedDate(LocalDate.parse("2001-01-10"));
        invoice.setDueDate(LocalDate.parse("2001-02-10"));
        invoice.setStatus(InvoiceStatus.GENERATED);
        invoice.getAllInvoiceLines().forEach(line -> line.setStatus(InvoiceLineStatus.GENERATED));
    }

}
