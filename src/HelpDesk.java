import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpDesk {

    private final Map<Integer, SupportRequest> requestsById;
    private final Map<Technician, List<SupportRequest>> assignments;

    public HelpDesk() {
        this.requestsById = new HashMap<>();
        this.assignments = new HashMap<>();
    }

    public void addRequest(SupportRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null.");
        }
        if (requestsById.containsKey(request.getId())) {
            throw new IllegalArgumentException("Duplicate request ID: " + request.getId());
        }
        requestsById.put(request.getId(), request);
    }

    public SupportRequest findRequest(int id) {
        return requestsById.get(id);
    }

    public void assignRequest(Technician technician, int requestId) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }
        SupportRequest request = findRequest(requestId);
        if (request == null) {
            throw new IllegalArgumentException("Request with ID " + requestId + " not found.");
        }

        List<SupportRequest> techAssignments = assignments.computeIfAbsent(technician, k -> new ArrayList<>());

        // Design choice: prevent assigning the same request multiple times to one technician
        if (techAssignments.contains(request)) {
            throw new IllegalStateException("Request #" + requestId + " is already assigned to " + technician.getName());
        }

        techAssignments.add(request);
    }

    public int resolveRequest(Technician technician, int requestId) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }
        List<SupportRequest> techAssignments = assignments.get(technician);
        if (techAssignments == null || techAssignments.isEmpty()) {
            throw new IllegalStateException("Technician " + technician.getName() + " has no assignments.");
        }

        SupportRequest targetRequest = null;
        for (SupportRequest req : techAssignments) {
            if (req.getId() == requestId) {
                targetRequest = req;
                break;
            }
        }

        if (targetRequest == null) {
            throw new IllegalStateException("Request #" + requestId + " is not assigned to " + technician.getName());
        }

        return targetRequest.resolveFor(technician);
    }

    public void printAllRequests() {
        System.out.println("\n--- All Support Requests ---");
        for (SupportRequest req : requestsById.values()) {
            System.out.println(req);
        }
    }

    public void printAssignmentsFor(Technician technician) {
        System.out.println("\n--- Assignments for " + technician.getName() + " ---");
        List<SupportRequest> list = assignments.get(technician);
        if (list == null || list.isEmpty()) {
            System.out.println("No assigned requests.");
        } else {
            for (SupportRequest req : list) {
                System.out.println(" -> " + req);
            }
        }
    }

    public List<SupportRequest> getAssignments(Technician technician) {
        List<SupportRequest> list = assignments.get(technician);
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }
}