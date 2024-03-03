import java.util.List;

public class DailyReport extends Report {
    public DailyReport(List<Sale> sales) {
        super(sales);
    }

    @Override
    public void generateReport() {
        System.out.println("Daily Sales Report:");
        generateTotalQuantityReport();
    }
}
