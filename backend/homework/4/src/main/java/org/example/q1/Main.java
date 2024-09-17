package org.example.q1;

import static org.example.q1.StudentUtil.calculateGPA;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main (String[] args) {
        try {
            int[] studentIdList1 = {1001, 1002};
            char[][] studentsGrades1 = {{'A', 'A', 'A', 'B'}};
            calculateGPA(studentIdList1, studentsGrades1);
        } catch (IllegalArgumentException | MissingGradeException exception) {
            logger.info("IllegalArgumentException caught: " + exception.getMessage());
        }
        try {
            int[] studentIdList1 = {1001};
            char[][] studentsGrades2 = {{'A', ' ', 'B'}};
            calculateGPA(studentIdList1, studentsGrades2);
        } catch (MissingGradeException exception) {
            String formattedMessage = String.format("Missing grade for student: %d", exception.getStudentId());
            throw new InvalidDataException(formattedMessage, exception);
        }
    }
}