package org.example.q1;

public class StudentUtil {
    /**
     * Calculates and returns the GPA for each student based on their grades.
     * Throws a MissingGradeException if a student has a missing grade (' ').
     * @param studentIdList Array of student IDs
     * @param studentsGrades 2D array representing grades of students
     * @return Array of calculated GPAs for each student
     * @throws MissingGradeException If a student has a missing grade
     */
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) throws MissingGradeException {
        if(studentIdList.length != studentsGrades.length){
            String formattedMessage = String.format("studentIdList & studentsGrades are out-of-sync. " +
                    "studentIdList.length: %d, studentsGrades.length: %d" , studentIdList.length, studentsGrades.length);
            throw new IllegalArgumentException(formattedMessage);
        }
        double[] gpaList = new double[studentIdList.length];

        for (int i = 0; i < studentsGrades.length; i++) {
            double gpa = 0.0;

            for (int j = 0; j < studentsGrades[i].length; j++) {
                if (studentsGrades[i][j] == 'A') {
                    gpa += 4.0;
                } else if (studentsGrades[i][j] == 'B') {
                    gpa += 3.0;
                } else if (studentsGrades[i][j] == 'C') {
                    gpa += 2.0;
                } else if (studentsGrades[i][j] == ' ') {
                    throw new MissingGradeException(studentIdList[i]);
                }
            }

            gpaList[i] = gpa/studentsGrades[i].length;
        }

        return gpaList;
    }


    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if (lower < 0 || higher < 0 || lower > higher) {
            return new int[0];
        }

        double[] gpaList = new double[studentIdList.length];
        // Your code: catch MissingGradeException and re-throw runtime exception InvalidDataException initialized with the cause MissingGradeException
        try {
            gpaList = calculateGPA(studentIdList, studentsGrades);
        } catch (MissingGradeException e) {
            throw new InvalidDataException("Missing grade. Throwing exception.", e);
        }


        int count = 0;
        for (double gpa : gpaList) {
            if (gpa >= lower && gpa <= higher) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i <  gpaList.length; i++) {
            if (gpaList[i] >= lower && gpaList[i] <= higher) {
                result[index++] = studentIdList[i];
            }
        }

        return result;
    }


}