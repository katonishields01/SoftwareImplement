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

}
