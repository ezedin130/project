import java.time.LocalDate;
import java.util.List;

public class MonthlyReport extends Report {
    LocalDate today = LocalDate.now();

    LocalDate startOfMonth = today.withDayOfMonth(1);
    LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());


    public MonthlyReport(List<Sale> sales,LocalDate startOfMonth , LocalDate endOfMonth) {
        super(sales);
        this.startOfMonth = startOfMonth;
        this.endOfMonth = endOfMonth;
    }

    @Override
    public void generateReport() {
        System.out.println("Monthly Sales Report:");
        System.out.println("Weekly Report from " + startOfMonth + " to " + endOfMonth + ":");
        for (Sale sale : sales) {
            if (sale.getsaleDate() != null &&sale.getsaleDate().getMonth().equals(startOfMonth.getMonth())) {
                System.out.println(sale);
            }
        }
    }
}
