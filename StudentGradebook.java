
/*
Author: Brandon Ayton
Date: 10-13-2025
Description: A student gradebook management system that demonstrates core Java concepts 
including variables, ArrayLists, loops, conditionals, classes, methods, and user input 
handling through a menu-driven interface.
*/

import java.util.ArrayList;
import java.util.Scanner;


public class StudentGradebook {
    
    public static void main(String[] args) {
        // Create list to store students
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Student Gradebook ===");
        
        boolean running = true;
        while (running) {
            // Display menu options
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View All Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer
            
            // Handle user choice
            if (choice == 1) {
                addNewStudent(students, scanner);
            } else if (choice == 2) {
                addStudentGrade(students, scanner);
            } else if (choice == 3) {
                displayAllStudents(students);
            } else if (choice == 4) {
                running = false;
                System.out.println("Thank you for using Student Gradebook!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    /**
     * Add a new student to the gradebook
     */
    public static void addNewStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        // Create student object and add to list
        Student student = new Student(name, id);
        students.add(student);
        
        System.out.println("Student added successfully!");
    }
    
    /**
     * Add a grade to an existing student
     */
    public static void addStudentGrade(ArrayList<Student> students, Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("No students in gradebook. Please add a student first.");
            return;
        }
        
        System.out.print("Enter student ID: ");
        String searchId = scanner.nextLine();
        
        // Search for student by ID
        Student foundStudent = null;
        for (Student student : students) {
            if (student.getId().equals(searchId)) {
                foundStudent = student;
                break;
            }
        }
        
        if (foundStudent == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.print("Enter grade (0-100): ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); 
        
        // Add grade to student
        foundStudent.addGrade(grade);
        System.out.println("Grade added successfully!");
    }
    
    /**
     * Display all students and their grades
     */
    public static void displayAllStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students in gradebook.");
            return;
        }
        
        System.out.println("\n=== All Students ===");
        // Loop through all students
        for (Student student : students) {
            student.displayStudentInfo();
        }
    }
}

/**
 * Student class to store student information and grades
 */
class Student {
    private String name;
    private String id;
    private ArrayList<Double> grades;
    
    // Constructor
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.grades = new ArrayList<>();
    }
    
    /**
     * Add a grade to student's record
     */
    public void addGrade(double grade) {
        // Validate grade range
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            System.out.println("Invalid grade! Must be between 0 and 100.");
        }
    }
    
    /**
     * Calculate average grade
     */
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double total = 0;
        // Sum all grades
        for (double grade : grades) {
            total += grade;
        }
        return total / grades.size();
    }
    
    /**
     * Get letter grade based on average
     */
    public String getLetterGrade() {
        double average = calculateAverage();
        
        // Determine letter grade
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
    
    /**
     * Display student information
     */
    public void displayStudentInfo() {
        System.out.println("\nName: " + name);
        System.out.println("ID: " + id);
        System.out.println("Grades: " + grades);
        
        if (!grades.isEmpty()) {
            System.out.printf("Average: %.2f\n", calculateAverage());
            System.out.println("Letter Grade: " + getLetterGrade());
        } else {
            System.out.println("No grades recorded.");
        }
        System.out.println("-------------------");
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public String getId() {
        return id;
    }
    
    public ArrayList<Double> getGrades() {
        return grades;
    }
}
