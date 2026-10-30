import java.util.List;

public class HelpDeskUtil {

    public static <T> void printAll(List<T> items) {
        if (items == null) {
            return;
        }
        for (T item : items) {
            System.out.println(item);
        }
    }

    public static int sumPoints(List<? extends Technician> technicians) {
        if (technicians == null) {
            return 0;
        }
        int total = 0;
        for (Technician tech : technicians) {
            total += tech.getServicePoints();
        }
        return total;
    }
}