package org.example;

import java.util.Scanner;
import static org.example.StudentUtil.getStudentsByGPA;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        logger.info("Enter the number of students:");
        int numStudents = scanner.nextInt();

        int[] studentIdList = new int[numStudents];
        char[][] studentsGrades = new char[numStudents][];

        for (int i = 0; i < numStudents; i++) {
            logger.info("Enter student ID for student " + (i + 1) + ":");
            studentIdList[i] = scanner.nextInt();

            logger.info("Enter number of courses for student " + (i + 1) + ":");
            int numCourses = scanner.nextInt();
            studentsGrades[i] = new char[numCourses];

            for (int j = 0; j < numCourses; j++) {
                logger.info("Enter grade for course " + (j + 1) + " for student " + (i + 1) + " (A/B/C):");
                studentsGrades[i][j] = scanner.next().charAt(0);
            }
        }

        logger.info("Enter lower limit for GPA range:");
        double lower = scanner.nextDouble();
        logger.info("Enter higher limit for GPA range:");
        double higher = scanner.nextDouble();

        int[] studentsInRange = getStudentsByGPA(lower, higher, studentIdList, studentsGrades);
        if (studentsInRange != null) {
            logger.info("\nStudents with GPAs between " + lower + " and " + higher + ":");
            for (int studentId : studentsInRange) {
                logger.info("Student ID : " + studentId);
            }
        } else {
            logger.info("Invalid range or parameters");
        }
        scanner.close();
    }
}
