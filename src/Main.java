import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HelpDesk helpDesk = new HelpDesk();

        // 1. Create technicians (testing equals/hashCode deduplication)
        Technician tech1 = new Technician("Alex Smith");
        Technician tech2 = new Technician("Jordan Lee");
        Technician tech3 = new Technician("Alex Smith"); // duplicate name

        Set<Technician> techSet = new HashSet<>();
        techSet.add(tech1);
        techSet.add(tech2);
        techSet.add(tech3);

        System.out.println("HashSet size (expected 2 due to duplicate 'Alex Smith'): " + techSet.size());

        List<Technician> activeTechs = new ArrayList<>(techSet);

        // 2. Create 6 support requests (2 of each type)
        SupportRequest req1 = new PasswordResetRequest(101, "Password Expired", 10, "asmith4");
        SupportRequest req2 = new PasswordResetRequest(102, "Account Lockout", 10, "jlee92");
        SupportRequest req3 = new DeviceSetupRequest(201, "Lab Computer Setup", 15, 4);
        SupportRequest req4 = new DeviceSetupRequest(202, "Office Laptop Deployment", 15, 2);
        SupportRequest req5 = new NetworkIssueRequest(301, "Dorm Wi-Fi Outage", 25, 12);
        SupportRequest req6 = new NetworkIssueRequest(302, "Faculty Switch Offline", 30, 8);

        // 3. Add requests to HelpDesk
        helpDesk.addRequest(req1);
        helpDesk.addRequest(req2);
        helpDesk.addRequest(req3);
        helpDesk.addRequest(req4);
        helpDesk.addRequest(req5);
        helpDesk.addRequest(req6);

        // 4. Assign at least 4 requests across technicians
        helpDesk.assignRequest(tech1, 101);
        helpDesk.assignRequest(tech1, 201);
        helpDesk.assignRequest(tech2, 202);
        helpDesk.assignRequest(tech2, 301);

        // 5. Resolve at least 3 requests polymorphically
        System.out.println("\n--- Resolving Requests ---");
        int points1 = helpDesk.resolveRequest(tech1, 101); // Password: 10
        System.out.println("Resolved #101: awarded " + points1 + " points.");

        int points2 = helpDesk.resolveRequest(tech1, 201); // Device: 15 * 4 = 60
        System.out.println("Resolved #201: awarded " + points2 + " points.");

        int points3 = helpDesk.resolveRequest(tech2, 301); // Network: 25 + (12 * 2) = 49
        System.out.println("Resolved #301: awarded " + points3 + " points.");

        // 6. Print all requests and technician assignments
        helpDesk.printAllRequests();
        helpDesk.printAssignmentsFor(tech1);
        helpDesk.printAssignmentsFor(tech2);

        // 7. Print final technician service points and sumPoints
        System.out.println("\n--- Final Technician Points ---");
        HelpDeskUtil.printAll(activeTechs);

        int totalAwarded = HelpDeskUtil.sumPoints(activeTechs);
        System.out.println("Total Service Points Distributed: " + totalAwarded);

        // 8. Fail-fast demonstrations
        System.out.println("\n--- Testing Fail-Fast Validation ---");
        try {
            System.out.println("Attempting duplicate request ID addition:");
            helpDesk.addRequest(new PasswordResetRequest(101, "Duplicate Test", 10, "dup_user"));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            System.out.println("Attempting to resolve request #101 a second time:");
            helpDesk.resolveRequest(tech1, 101);
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}