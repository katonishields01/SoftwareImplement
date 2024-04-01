public class Nurse {
    private ArrayList<Nurse> nurses;



    private void addNurse() {
        Nurse nurse = new Nurse();

        System.out.print("Enter Nurse ID: ");
        nurse.nurseID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter Nurse Name: ");
        nurse.nurseName = scanner.nextLine();

        System.out.print("Enter Nurse Shift: ");
        nurse.shift = scanner.nextLine();

        nurses.add(nurse);
        System.out.println("Nurse added successfully.");
    }

    private void removeNurse() {
        System.out.print("Enter Nurse ID to remove: ");
        int nurseID = scanner.nextInt();

        boolean removed = nurses.removeIf(nurse -> nurse.nurseID == nurseID);

        if (removed) {
            System.out.println("Nurse removed successfully.");
        } else {
            System.out.println("Nurse not found with the given ID.");
        }
    }

    private void viewNurseDetails() {
        System.out.print("Enter Nurse ID to view details: ");
        int nurseID = scanner.nextInt();

        for (Nurse nurse : nurses) {
            if (nurse.nurseID == nurseID) {
                System.out.println("Nurse Details:");
                System.out.println("ID: " + nurse.nurseID);
                System.out.println("Name: " + nurse.nurseName);
                System.out.println("Shift: " + nurse.shift);
                return;
            }
        }

        System.out.println("Nurse not found with the given ID.");
    }

    private static class Nurse {
        private int nurseID;
        private String nurseName;
        private String shift;
    }

}
