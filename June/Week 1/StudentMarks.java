// Program to input student name, roll no., subjects, and marks, then calculate totals.
import java.util.Scanner;

class StudentMarks {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter Roll No: ");
            if (!sc.hasNextInt()) {
                System.out.println("Error: Invalid roll number.");
                return;
            }
            int rollNo = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter Number of Subjects: ");
            if (!sc.hasNextInt()) {
                System.out.println("Error: Invalid number of subjects.");
                return;
            }
            int numSubjects = sc.nextInt();
            sc.nextLine();

            if (numSubjects <= 0) {
                System.out.println("Error: Number of subjects must be greater than 0.");
                return;
            }

            String[] subjects = new String[numSubjects];
            int[] marks = new int[numSubjects];
            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Enter Subject " + (i + 1) + " Name: ");
                subjects[i] = sc.nextLine().trim();
                System.out.print("Enter Marks in " + subjects[i] + ": ");
                if (!sc.hasNextInt()) {
                    System.out.println("Error: Invalid marks input. Aborting.");
                    return;
                }
                int mark = sc.nextInt();
                sc.nextLine();
                if (mark < 0 || mark > 100) {
                    System.out.println("Error: Marks must be between 0 and 100.");
                    return;
                }
                marks[i] = mark;
            }

            int totalMarks = 0;
            for (int mark : marks) {
                totalMarks += mark;
            }
            double averageMarks = (double) totalMarks / numSubjects;
            double percentage = (double) totalMarks / (numSubjects * 100) * 100;
            System.out.println("\nStudent Name: " + name);
            System.out.println("Roll No: " + rollNo);
            System.out.println("Total Marks: " + totalMarks);
            System.out.println("Average Marks: " + averageMarks);
            System.out.println("Percentage: " + percentage + "%");
        } catch (Exception ex) {
            System.err.println("Unexpected error in StudentMarks: " + ex.getMessage());
        }
    }
}