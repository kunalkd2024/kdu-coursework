package org.example2;

import java.util.Arrays;

public class SentimentAnalyzer {
    private static String removeSpecialCharacters(String review) {
        return review.replaceAll("[^a-zA-Z0-9\\s]", "");
    }
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];

        String finalReview = removeSpecialCharacters(review).toLowerCase();
        for (int i = 0; i < featureSet.length; i++) {
            featureOpinions[i] = 0;
            for(int j=0;j < featureSet[i].length;j++){
                int temp = getOpinionOnFeature(finalReview, featureSet[i][j].toLowerCase(), posOpinionWords, negOpinionWords);
                if(temp == 1 || temp == -1){
                    featureOpinions[i] = temp;
                    break;
                }
            }
        }
        return featureOpinions;
    }

    private static int getOpinionOnFeature(String review, String feature,
                   String[] posOpinionWords, String[] negOpinionWords){
        int val = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        if(val == 0) return checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
        return val;
    }

    private static int checkForWasPhrasePattern(String review, String
            feature, String[] posOpinionWords, String[] negOpinionWords){
        int opinion = 0;
        String pattern = feature + " was ";

        if (review.contains(pattern)) {
            int startIndex = review.indexOf(pattern);
            if (startIndex != -1) {
                int endIndex = review.indexOf(" ", startIndex + pattern.length());
                if (endIndex == -1) {
                    endIndex = review.length();
                }
                String nextWord = review.substring(startIndex + pattern.length(), endIndex).toLowerCase();

                if (Arrays.asList(posOpinionWords).contains(nextWord)) {
                    opinion = 1;
                } else if (Arrays.asList(negOpinionWords).contains(nextWord)) {
                    opinion = -1;
                }
            }
        }
        return opinion;
    }

    private static int checkForOpinionFirstPattern(String review, String
            feature, String[] posOpinionWords, String[] negOpinionWords){
        String[] sentences = review.split("\\.");
        int opinion = 0;
        for (String sentence : sentences) {
            if (sentence.contains(feature)) {
                String[] words = sentence.trim().split(" ");

                for (int i = 0; i < words.length - 1; i++) {
                    if (words[i].equalsIgnoreCase(feature)) {
                        if (Arrays.asList(posOpinionWords).contains(words[i - 1].toLowerCase())) {
                            opinion = 1;
                            break;
                        } else if (Arrays.asList(negOpinionWords).contains(words[i - 1].toLowerCase())) {
                            opinion = -1;
                            break;
                        }
                    }
                }
            }

            if (opinion != 0) break;
        }
        return opinion;
    }
}
