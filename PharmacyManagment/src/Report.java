import java.util.List;

public abstract class Report {
    protected List<Sale> sales;

    public Report(List<Sale> sales) {
        this.sales = sales;
    }
    public abstract void generateReport();
    public void generateTotalQuantityReport() {
        for (Sale sale : sales) {
            System.out.println("Item: " + sale.getitemName() + ", Total Quantity Sold: " + sale.getquantity() + " on " + sale.getsaleDate());
        }
        System.out.println("***************************\n");
    }
}
