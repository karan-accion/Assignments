//Program to Input Student Name, Roll No. Number of Subjects and Names, Marks in Each Subject and Calculate Total Marks, Average Marks and Percentage. Use Matrix and Arrays
import java.util.*;
public class studentmarks{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Roll No: ");
        int rollNo = sc.nextInt();
        System.out.print("Enter Number of Subjects: ");
        int numSubjects = sc.nextInt();
        String[] subjects = new String[numSubjects];
        int[] marks = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter Subject " + (i + 1) + " Name: ");
            subjects[i] = sc.next();
            System.out.print("Enter Marks in " + subjects[i] + ": ");
            marks[i] = sc.nextInt();
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
    }
}