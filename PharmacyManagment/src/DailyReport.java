import java.time.LocalDate;
import java.util.List;

public class DailyReport extends Report {
    public DailyReport(List<Sale> sales) {
        super(sales);
    }

    @Override
    public void generateReport() {
        System.out.println("Daily Sales Report for " + LocalDate.now() + ":");
        for (Sale sale : sales) {
            if (sale.getsaleDate().isEqual(LocalDate.now())) {
                System.out.println("Item: " + sale.getitemName() + ", Total Quantity Sold: " + sale.getquantity() + " on " + sale.getsaleDate());
            }
        }
        System.out.println("***************************\n");
    }
}
