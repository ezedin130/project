import java.util.List;

public abstract class Report {
    protected List<Sale> sales;

    public Report(List<Sale> sales) {
        this.sales = sales;
    }
    public abstract void generateReport();
}
