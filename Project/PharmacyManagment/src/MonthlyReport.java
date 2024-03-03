import java.util.List;

public class MonthlyReport extends Report {
    public MonthlyReport(List<Sale> sales) {
        super(sales);
    }

    @Override
    public void generateReport() {
        System.out.println("Monthly Sales Report:");
        generateTotalQuantityReport();
    }
}
