import java.util.List;

public class WeeklyReport extends Report {
    public WeeklyReport(List<Sale> sales) {
        super(sales);
    }

    @Override
    public void generateReport() {
        System.out.println("Weekly Sales Report:");
        generateTotalQuantityReport();
    }
}
