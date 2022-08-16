package name.lemerdy;

public class InvoiceLine {

    private final InvoiceLineType invoiceLineType;
    private final Money money;
    private InvoiceLineStatus status;

    public InvoiceLine(InvoiceLineType invoiceLineType, Money money) {
        this.invoiceLineType = invoiceLineType;
        this.money = money;
    }

    public InvoiceLine add(InvoiceLine line) {
        return new InvoiceLine(invoiceLineType, money.add(line.money));
    }

    public Money getMoney() {
        return money;
    }

    public InvoiceLineStatus getStatus() {
        return status;
    }

    public void setInvoice(Invoice invoice) {
        invoice.addLine(this);
    }

    public void setStatus(InvoiceLineStatus status) {
        this.status = status;
    }

}
