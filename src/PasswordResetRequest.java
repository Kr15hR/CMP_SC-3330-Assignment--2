public class PasswordResetRequest extends AbstractSupportRequest {

    private final String accountName;

    public PasswordResetRequest(int id, String title, int basePoints, String accountName) {
        super(id, title, basePoints);
        if (accountName == null || accountName.isBlank()) {
            throw new IllegalArgumentException("Account name cannot be null or blank.");
        }
        this.accountName = accountName.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public int resolveFor(Technician technician) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }
        markResolved();
        int pointsAwarded = getBasePoints();
        technician.addPoints(pointsAwarded);
        return pointsAwarded;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Password Reset | Account: " + accountName;
    }
}
