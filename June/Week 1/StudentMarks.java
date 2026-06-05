import java.util.Scanner;

class StudentMarks {
    private static final int MIN_MARK = 0;
    private static final int MAX_MARK = 100;
    private static final int MAX_SUBJECTS = 12;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Student Marks Calculator");
            String name = readNonEmptyLine(sc, "Enter Student Name: ");
            int rollNo = readIntInRange(sc, "Enter Roll No: ", 1, Integer.MAX_VALUE);
            int numSubjects = readIntInRange(sc, "Enter Number of Subjects (1-" + MAX_SUBJECTS + "): ", 1, MAX_SUBJECTS);

            String[] subjects = new String[numSubjects];
            int[] marks = new int[numSubjects];
            int totalMarks = 0;

            for (int i = 0; i < numSubjects; i++) {
                subjects[i] = readNonEmptyLine(sc, "Enter Subject " + (i + 1) + " Name: ");
                marks[i] = readIntInRange(sc, "Enter Marks in " + subjects[i] + " (0-100): ", MIN_MARK, MAX_MARK);
                totalMarks += marks[i];
            }

            double averageMarks = (double) totalMarks / numSubjects;
            double percentage = (double) totalMarks / (numSubjects * MAX_MARK) * 100.0;

            System.out.println();
            System.out.println("Student Details");
            System.out.println("Student Name: " + name);
            System.out.println("Roll No: " + rollNo);

            System.out.println();
            System.out.println("Subject-wise Marks");
            for (int i = 0; i < numSubjects; i++) {
                System.out.println("Subject: " + subjects[i] + " - Marks: " + marks[i]);
            }

            System.out.println();
            System.out.println("Total Marks: " + totalMarks);
            System.out.printf("Average Marks: %.2f%n", averageMarks);
            System.out.printf("Percentage: %.2f%%%n", percentage);
        } catch (IllegalStateException ex) {
            System.err.println("Input stream error: " + ex.getMessage());
        }
    }

    private static String readNonEmptyLine(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Error: Value cannot be empty.");
        }
    }

    private static int readIntInRange(Scanner sc, String prompt, int minValue, int maxValue) {
        while (true) {
            System.out.print(prompt);
            String token = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(token);
                if (value < minValue || value > maxValue) {
                    System.out.println("Error: Enter a value between " + minValue + " and " + maxValue + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Error: Invalid number format.");
            }
        }
    }
}