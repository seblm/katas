package name.lemerdy;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    @Test
    void testInvoiceSum() {
        var invoice = ObjectMother.createNewInvoice();
        ObjectMother.attachInvoiceLineAsCharge(invoice, new Money("199.95", "USD"));
        ObjectMother.attachInvoiceLineAsCharge(invoice, new Money("100", "USD"));
        ObjectMother.attachInvoiceLineAsCharge(invoice, new Money("20", "USD"));

        assertEquals(new Money("5319.90", "USD"), invoice.sum());
    }

    @Test
    void testInvoiceStatusIsGENERATEDWhenGenerated() throws Exception {
        var invoice = ObjectMother.createNewInvoice();

        invoice.generate();

        assertEquals(InvoiceStatus.GENERATED, invoice.getStatus());
    }

    @Test
    void testInvoiceGeneratedDateIsDefinedWhenGenerated() throws Exception {
        var invoice = ObjectMother.createNewInvoice();

        invoice.generate();

        assertNotNull(invoice.getGeneratedDate());
    }

    @Test
    void testInvoiceDueDateIsOneMonthAfterNowWhenGenerated() throws Exception {
        var invoice = ObjectMother.createNewInvoice();

        invoice.generate();

        assertEquals(invoice.getGeneratedDate().plusMonths(1), invoice.getDueDate());
    }

    @Test
    void testInvoiceLinesStatusesAreGENERATEDWhenGenerated() throws Exception {
        var invoice = ObjectMother.createNewInvoice();
        ObjectMother.attachInvoiceLineAsCharge(invoice, new Money("199.95", "USD"));
        ObjectMother.attachInvoiceLineAsCharge(invoice, new Money("100", "USD"));
        ObjectMother.attachInvoiceLineAsCharge(invoice, new Money("20", "USD"));

        invoice.generate();

        assertIterableEquals(Collections.nCopies(4, InvoiceLineStatus.GENERATED),
                invoice.getAllInvoiceLines().stream().map(InvoiceLine::getStatus).toList());
    }

    @Test
    void testInvoiceCanTBeGeneratedIfAddressIsInactive() {
        var invoice = ObjectMother.createNewInvoice();
        invoice.setBillToAddress(ObjectMother.createInactiveAddress());

        assertThrows(InactiveAddressException.class, invoice::generate);
    }

}
