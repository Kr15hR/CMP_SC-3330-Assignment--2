public class NetworkIssueRequest extends AbstractSupportRequest {

    private final int affectedUsers;

    public NetworkIssueRequest(int id, String title, int basePoints, int affectedUsers) {
        super(id, title, basePoints);
        if (affectedUsers <= 0) {
            throw new IllegalArgumentException("Affected users count must be positive: " + affectedUsers);
        }
        this.affectedUsers = affectedUsers;
    }

    public int getAffectedUsers() {
        return affectedUsers;
    }

    @Override
    public int resolveFor(Technician technician) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }
        markResolved();
        // Design choice: weighted rule: basePoints + (affectedUsers * 2)
        int pointsAwarded = getBasePoints() + (affectedUsers * 2);
        technician.addPoints(pointsAwarded);
        return pointsAwarded;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Network Issue | Users Affected: " + affectedUsers;
    }
}