import java.util.Objects;

/**
 * Represents an IT technician who is assigned and resolves support requests.
 */
public class Technician {

    private final String name;
    private int servicePoints;

    public Technician(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Technician name cannot be null or blank.");
        }
        this.name = name.trim();
        this.servicePoints = 0;
    }

    public String getName() {
        return name;
    }

    public int getServicePoints() {
        return servicePoints;
    }

    public void addPoints(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add negative service points: " + amount);
        }
        this.servicePoints += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technician that = (Technician) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Technician [name=" + name + ", servicePoints=" + servicePoints + "]";
    }
}