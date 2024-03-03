package org.example;

import java.util.logging.Logger;

public class StudentUtil {
    private static final Logger logger = Logger.getLogger(StudentUtil.class.getName());

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        double[] result = new double[studentIdList.length];
        for (int i = 0; i < studentIdList.length; i++) {
            double num = (double) studentsGrades[i].length;
            double points = 0;
            for (char grade : studentsGrades[i]) {
                points += getPointsFromGrade(grade);
            }
            result[i] = points / num;
        }
        return result;
    }

    private static int getPointsFromGrade(char x) {
        return switch (x) {
            case 'A' -> 4;
            case 'B' -> 3;
            case 'C' -> 2;
            default -> 0;
        };
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) {
        if (lower < 0 || higher < 0 || lower > higher) return null;
        double[] gpa = calculateGPA(studentIdList, studentsGrades);
        logger.info("All GPAs:");
        for (int i = 0; i < gpa.length; i++) {
            logger.info("Student ID: " + studentIdList[i] + ", GPA: " + gpa[i]);
        }
        int count = 0;
        for (double currentGpa : gpa) {
            if (currentGpa >= lower && currentGpa <= higher) count++;
        }
        int[] students = new int[count];
        int ind = 0;
        for (int i = 0; i < gpa.length; i++) {
            if (gpa[i] >= lower && gpa[i] <= higher) students[ind++] = studentIdList[i];
        }
        return students;
    }
}
