public class DeviceSetupRequest extends AbstractSupportRequest {

    private final int deviceCount;

    public DeviceSetupRequest(int id, String title, int basePoints, int deviceCount) {
        super(id, title, basePoints);
        if (deviceCount <= 0) {
            throw new IllegalArgumentException("Device count must be positive: " + deviceCount);
        }
        this.deviceCount = deviceCount;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    @Override
    public int resolveFor(Technician technician) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }
        markResolved();
        int pointsAwarded = getBasePoints() * deviceCount;
        technician.addPoints(pointsAwarded);
        return pointsAwarded;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Device Setup | Devices: " + deviceCount;
    }
}