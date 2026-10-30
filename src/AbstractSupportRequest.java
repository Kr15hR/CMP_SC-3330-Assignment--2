public abstract class AbstractSupportRequest implements SupportRequest {

    private final int id;
    private final String title;
    private final int basePoints;
    private boolean resolved;

    public AbstractSupportRequest(int id, String title, int basePoints) {
        if (id <= 0) {
            throw new IllegalArgumentException("Request ID must be positive: " + id);
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
        if (basePoints <= 0) {
            throw new IllegalArgumentException("Base points must be positive: " + basePoints);
        }
        this.id = id;
        this.title = title.trim();
        this.basePoints = basePoints;
        this.resolved = false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getBasePoints() {
        return basePoints;
    }

    @Override
    public boolean isResolved() {
        return resolved;
    }

    // Design choice: Requests cannot be resolved twice.
    protected void markResolved() {
        if (this.resolved) {
            throw new IllegalStateException("Request #" + id + " has already been resolved.");
        }
        this.resolved = true;
    }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + title + " | Base Points: " + basePoints + " | Resolved: " + resolved;
    }
}