import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklyReport extends Report {
    LocalDate today = LocalDate.now();

    LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
    LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);



    public WeeklyReport(List<Sale> sales,LocalDate startOfWeek , LocalDate endOfWeek) {
        super(sales);
        this.startOfWeek = startOfWeek;
        this.endOfWeek = endOfWeek;
    }

    @Override
    public void generateReport() {
     
        System.out.println("Weekly Report from " + startOfWeek + " to " + endOfWeek + ":");
        for (Sale sale : sales) {
            LocalDate saleDate = sale.getsaleDate();
            if (saleDate != null && saleDate.isAfter(startOfWeek.minusDays(1)) && saleDate.isBefore(endOfWeek.plusDays(1))) {
                System.out.println(sale);
        }
    }
    }
}

