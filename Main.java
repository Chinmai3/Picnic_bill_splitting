import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of friends and activities
        System.out.print("Enter the number of friends: ");
        int numFriends = scanner.nextInt();
        System.out.print("Enter the number of activities: ");
        int numActivities = scanner.nextInt();

        // Create a map to store the cost of each activity
        Map<String, Double> activityCosts = new HashMap<>();
        for (int i = 0; i < numActivities; i++) {
            System.out.print("Enter the name of activity #" + (i+1) + ": ");
            String activityName = scanner.next();
            System.out.print("Enter the cost of " + activityName + ": ");
            double activityCost = scanner.nextDouble();
            activityCosts.put(activityName, activityCost);
        }

        // Create a map to store the list of friends who participated in each activity
        Map<String, List<String>> activityParticipants = new HashMap<>();
        for (String activityName : activityCosts.keySet()) {
            System.out.print("Enter the names of friends who participated in " + activityName + " (comma-separated): ");
            String[] participantNames = scanner.next().split(",");
            activityParticipants.put(activityName, Arrays.asList(participantNames));
        }

        // Create a map to store the amount each person paid
        Map<String, Double> paidAmounts = new HashMap<>();
        for (int i = 0; i < numFriends; i++) {
            System.out.print("Enter the name of friend #" + (i+1) + ": ");
            String friendName = scanner.next();
            System.out.print("Enter the amount paid by " + friendName + ": ");
            double paidAmount = scanner.nextDouble();
            paidAmounts.put(friendName, paidAmount);
        }

        // Create a map to store the amount each person owes or is owed
        Map<String, Double> balanceAmounts = new HashMap<>();
        for (String friendName : paidAmounts.keySet()) {
            double balanceAmount = 0;
            for (String activityName : activityCosts.keySet()) {
                if (activityParticipants.get(activityName).contains(friendName)) {
                    balanceAmount += activityCosts.get(activityName) / activityParticipants.get(activityName).size();
                }
            }
            balanceAmount -= paidAmounts.get(friendName);
            balanceAmounts.put(friendName, balanceAmount);
        }

        // Print the result
        System.out.println("The final balance amounts are:");
        for (String friendName : balanceAmounts.keySet()) {
            System.out.println(friendName + ": " + balanceAmounts.get(friendName));
        }
    }
}

