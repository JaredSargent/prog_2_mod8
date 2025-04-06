package studentData;
import java.io.*;
import java.util.*;

public class StudentDataProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Student> students = new LinkedList<>();

        // Loop to collect student data
        while (true) {
            System.out.print("Enter student name (or 'quit' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("quit")) break;

            System.out.print("Enter student address: ");
            String address = scanner.nextLine();

            double gpa = 0;
            boolean validGPA = false;
            while (!validGPA) {
                System.out.print("Enter student GPA (0.0-4.0): ");
                try {
                    gpa = Double.parseDouble(scanner.nextLine());
                    if (gpa >= 0.0 && gpa <= 4.0) validGPA = true;
                    else System.out.println("GPA must be between 0.0 and 4.0.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a numeric GPA.");
                }
            }

            students.add(new Student(name, address, gpa));
        }

        // Sort by name
        Collections.sort(students, Comparator.comparing(Student::getName));

        // Write to file
        try (PrintWriter writer = new PrintWriter("students.txt")) {
            for (Student s : students) {
                writer.println("Name: " + s.getName());
                writer.println("Address: " + s.getAddress());
                writer.println("GPA: " + s.getGpa());
                writer.println();
            }
            System.out.println("Data written to students.txt successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        scanner.close();
    }
}

// Student class with private fields
class Student {
    private String name;
    private String address;
    private double gpa;

    public Student(String name, String address, double gpa) {
        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public double getGpa() { return gpa; }
}