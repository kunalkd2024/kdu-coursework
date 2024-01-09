package org.example3;

import java.util.logging.Logger;

public class APIResponseParser {
    private static final Logger logger = Logger.getLogger(APIResponseParser.class.getName());
    public static Book parse(String response) {
        Book book = new Book();
        String endRule = "<";
        String startRule = "<title>";
        String title = parseSingle(response, startRule, endRule);
        book.setTitle(title);

        // Other fields parsing and setting logic similar to title
        String author = parseSingle(response, "<author>", "<");
        book.setAuthor(author);

        String publicationYear = parseSingle(response, "<original_publication_year type=\"integer\">", "<");
        book.setPublicationYear(Integer.parseInt(publicationYear.replaceAll(",", ""))); // Remove commas and parse as integer

        String averageRating = parseSingle(response, "<average_rating>", "<");
        book.setAverageRating(Double.parseDouble(averageRating));

        String ratingsCount = parseSingle(response, "<ratings_count type=\"integer\">", "<");
        book.setRatingsCount(Integer.parseInt(ratingsCount.replaceAll(",", ""))); // Remove commas and parse as integer

        String imageUrl = parseSingle(response, "<image_url>", "<");
        book.setImageUrl(imageUrl);

        logger.info("Details of book object:");
        logger.info("Book title: " + book.getTitle());
        logger.info("Author name: " + book.getAuthor());
        logger.info("Publication_year: " + book.getPublicationYear());
        logger.info("Average Rating: " + book.getAverageRating());
        logger.info("Rating Count: " + book.getRatingsCount());
        logger.info("Image Url: " + book.getImageUrl());

        return book;
    }


    private static String parseSingle(String response, String startRule, String endRule){
        int pos=response.indexOf(startRule);
        int start=pos+startRule.length();
        int end=response.indexOf(endRule,start);
        return response.substring(start,end);

    }

    public static void main(String[] args) {
        String response = "<work>\n" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>\n" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>\n" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count> " +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/> " +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>\n" +
                "<best_book type=\"Book\">\n" +
                "<id type=\"integer\">16902</id> <title>Walden</title> <author>\n" +
                "<id type=\"integer\">10264</id>\n" +
                "<name>Henry David Thoreau</name> </author>\n" +
                "<image_url>http://images.gr-assets.com/books/1465675526m/16902.jpg</image_url>\n" +
                "<small_image_url>http://images.gr-assets.com/books/1465675526s/16902.jpg</small\n" +
                "</small_image_url>\n" +
                "</best_book>\n" +
                "</work>";

        APIResponseParser.parse(response);
    }
}
