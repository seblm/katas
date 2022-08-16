package name.lemerdy;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    @Test
    void testInvoiceSum() {
        var invoice = new Invoice();
        invoice.setInvoiceNumber("InvTest001");
        var address = new Address();
        address.setAddressLine1("1011 Bit Lane");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60647");
        address.setStatus(AddressStatus.ACTIVE);
        invoice.setBillToAddress(address);
        var invoiceLine1 = new InvoiceLine(InvoiceLineType.CHARGE, new Money("4999.95", "USD"));
        invoiceLine1.setInvoice(invoice);
        invoice.setStatus(InvoiceStatus.NEW);
        var invoiceLine2 = new InvoiceLine(InvoiceLineType.CHARGE, new Money("199.95", "USD"));
        invoiceLine2.setInvoice(invoice);
        var invoiceLine3 = new InvoiceLine(InvoiceLineType.CHARGE, new Money("100", "USD"));
        invoiceLine3.setInvoice(invoice);
        var invoiceLine4 = new InvoiceLine(InvoiceLineType.CHARGE, new Money("20", "USD"));
        invoiceLine4.setInvoice(invoice);

        assertEquals(new Money("5319.90", "USD"), invoice.sum());
    }

    @Test
    void testInvoiceStatusIsGENERATEDWhenGenerated() throws Exception {
        var invoice = new Invoice();
        invoice.setInvoiceNumber("InvTest001");
        var address = new Address();
        address.setAddressLine1("1011 Bit Lane");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60647");
        address.setStatus(AddressStatus.ACTIVE);
        invoice.setBillToAddress(address);
        var invoiceLine = new InvoiceLine(InvoiceLineType.CHARGE, new Money("4999.95", "USD"));
        invoiceLine.setInvoice(invoice);
        invoice.setStatus(InvoiceStatus.NEW);

        invoice.generate();

        assertEquals(InvoiceStatus.GENERATED, invoice.getStatus());
    }

    @Test
    void testInvoiceGeneratedDateIsDefinedWhenGenerated() throws Exception {
        var invoice = new Invoice();
        invoice.setInvoiceNumber("InvTest001");
        var address = new Address();
        address.setAddressLine1("1011 Bit Lane");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60647");
        address.setStatus(AddressStatus.ACTIVE);
        invoice.setBillToAddress(address);
        var invoiceLine = new InvoiceLine(InvoiceLineType.CHARGE, new Money("4999.95", "USD"));
        invoiceLine.setInvoice(invoice);
        invoice.setStatus(InvoiceStatus.NEW);

        invoice.generate();

        assertNotNull(invoice.getGeneratedDate());
    }

    @Test
    void testInvoiceDueDateIsOneMonthAfterNowWhenGenerated() throws Exception {
        var invoice = new Invoice();
        invoice.setInvoiceNumber("InvTest001");
        var address = new Address();
        address.setAddressLine1("1011 Bit Lane");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60647");
        address.setStatus(AddressStatus.ACTIVE);
        invoice.setBillToAddress(address);
        var invoiceLine = new InvoiceLine(InvoiceLineType.CHARGE, new Money("4999.95", "USD"));
        invoiceLine.setInvoice(invoice);
        invoice.setStatus(InvoiceStatus.NEW);

        invoice.generate();

        assertEquals(invoice.getGeneratedDate().plusMonths(1), invoice.getDueDate());
    }

    @Test
    void testInvoiceLinesStatusesAreGENERATEDWhenGenerated() throws Exception {
        var invoice = new Invoice();
        invoice.setInvoiceNumber("InvTest001");
        var address = new Address();
        address.setAddressLine1("1011 Bit Lane");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60647");
        address.setStatus(AddressStatus.ACTIVE);
        invoice.setBillToAddress(address);
        var invoiceLine1 = new InvoiceLine(InvoiceLineType.CHARGE, new Money("4999.95", "USD"));
        invoiceLine1.setInvoice(invoice);
        invoice.setStatus(InvoiceStatus.NEW);
        var invoiceLine2 = new InvoiceLine(InvoiceLineType.CHARGE, new Money("199.95", "USD"));
        invoiceLine2.setInvoice(invoice);
        var invoiceLine3 = new InvoiceLine(InvoiceLineType.CHARGE, new Money("100", "USD"));
        invoiceLine3.setInvoice(invoice);
        var invoiceLine4 = new InvoiceLine(InvoiceLineType.CHARGE, new Money("20", "USD"));
        invoiceLine4.setInvoice(invoice);

        invoice.generate();

        assertIterableEquals(Collections.nCopies(4, InvoiceLineStatus.GENERATED),
                invoice.getAllInvoiceLines().stream().map(InvoiceLine::getStatus).toList());
    }

    @Test
    void testInvoiceCanTBeGeneratedIfAddressIsInactive() {
        var invoice = new Invoice();
        invoice.setInvoiceNumber("InvTest001");
        var address = new Address();
        address.setAddressLine1("1011 Bit Lane");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60647");
        address.setStatus(AddressStatus.ACTIVE);
        invoice.setBillToAddress(address);
        var invoiceLine = new InvoiceLine(InvoiceLineType.CHARGE, new Money("4999.95", "USD"));
        invoiceLine.setInvoice(invoice);
        invoice.setStatus(InvoiceStatus.NEW);
        var address2 = new Address();
        address2.setAddressLine1("1011 Bit Lane");
        address2.setCity("Chicago");
        address2.setState("IL");
        address2.setZip("60647");
        address2.setStatus(AddressStatus.INACTIVE);
        invoice.setBillToAddress(address2);

        assertThrows(InactiveAddressException.class, invoice::generate);
    }

}
