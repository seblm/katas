package name.lemerdy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Invoice {

    private final List<InvoiceLine> allInvoiceLines = new ArrayList<>();
    private Address billToAddress;
    private String invoiceNumber;
    private InvoiceStatus status;
    private LocalDate generatedDate;
    private LocalDate dueDate;

    public void addLine(InvoiceLine invoiceLine) {
        allInvoiceLines.add(invoiceLine);
    }

    public void generate() throws InactiveAddressException {
        if (Optional.ofNullable(billToAddress).orElseThrow().getStatus() == AddressStatus.ACTIVE) {
            setStatus(InvoiceStatus.GENERATED);
            var generatedDate = LocalDate.now();
            setGeneratedDate(generatedDate);
            setDueDate(generatedDate.plusMonths(1));
            allInvoiceLines.forEach(line -> line.setStatus(InvoiceLineStatus.GENERATED));
        } else {
            throw new InactiveAddressException();
        }
    }

    public List<InvoiceLine> getAllInvoiceLines() {
        return List.copyOf(allInvoiceLines);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setBillToAddress(Address billToAddress) {
        this.billToAddress = billToAddress;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setGeneratedDate(LocalDate generatedDate) {
        this.generatedDate = generatedDate;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Money sum() {
        return allInvoiceLines.stream().reduce(InvoiceLine::add).map(InvoiceLine::getMoney).orElse(new Money("0", "USD"));
    }

}
