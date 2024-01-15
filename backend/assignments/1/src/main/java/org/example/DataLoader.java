package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataLoader {
    private DataLoader() {
    }

    public enum FileType {
        CSV, JSON
    }

    /**
     * Loads data from a specified file in the resources folder based on the file type.
     *
     * @param fileName The name of the file in the resources folder.
     * @param fileType The type of the file (CSV or JSON).
     * @return A list of CSV records if the file type is CSV, or a JsonNode if the file type is JSON.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static Object loadData(String fileName, FileType fileType) throws IOException {
        // Use ClassLoader to load the resource
        ClassLoader classLoader = DataLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (fileType == FileType.CSV) {
            // Use Apache Commons CSV to parse the CSV data
            CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new InputStreamReader(inputStream));
            // Return the list of CSV records
            return csvParser.getRecords();
        } else if (fileType == FileType.JSON) {
            // Use Jackson ObjectMapper to parse the JSON data
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(inputStream);
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }
    public static class Pair<A, B> {
        private A first;
        private B second;
        /**
         * Constructs a Pair with the specified first and second values.
         *
         * @param first  The first element of the pair.
         * @param second The second element of the pair.
         */
        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

        public void setFirst(A first) {
            this.first = first;
        }
        /**
         * Returns a string representation of the Pair.
         *
         * @return A string representation of the Pair.
         */
        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }
}

