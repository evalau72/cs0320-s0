package edu.brown.cs32.fall22.onboarding.main;

import edu.brown.cs32.fall22.onboarding.csv.CSVParser;
import edu.brown.cs32.fall22.onboarding.csv.CSVParserBuilder;
import edu.brown.cs32.fall22.onboarding.csv.Creator;
import edu.brown.cs32.fall22.onboarding.csv.DefaultCreator;
import edu.brown.cs32.fall22.onboarding.stars.Star;
import edu.brown.cs32.fall22.onboarding.stars.StarCreator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Main class
 * Implements a "word count" utility for CSV files
 * containing a specific kind of data: Star coordinates
 */
public class Main {

    /**
     * Builds CSV parsers and prints out relevant info.
     */
    public static void main(String[] args) {
        // error checking arguments
        if (args.length != 1) {
            System.out.println("ERROR: must input CSV file path.");
            System.exit(1);
        }

        String filePath = args[0];
        try {
            // builds a CSV Parser to parse through the csv and produce a List of stars
            Creator<Star> starCreator = new StarCreator();
            CSVParserBuilder<Star> starBuilder = new CSVParserBuilder<>(new FileReader(filePath), false);
            CSVParser<Star> starParser = starBuilder.creator(starCreator).build();
            List<Star> starList = starParser.parse();
            System.out.println(countStarsInfo(starList, filePath));

            // builds a CSV Parser to parse through csv and produce and List of List of Strings (containing Star info)
            Creator<List<String>> defaultCreator = new DefaultCreator();
            CSVParserBuilder<List<String>> defaultBuilder = new CSVParserBuilder<>(new FileReader(filePath), false);
            CSVParser<List<String>> defaultParser = defaultBuilder.creator(defaultCreator).build();
            List<List<String>> defaultList = defaultParser.parse();
            System.out.println(countDefaultInfo(defaultList, filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not valid.");
            System.exit(1);
        } catch (Exception ex) {
            System.out.println("CSV Parser build error occurred.");
            System.exit(1);
        }
    }

    /**
     * Method that prints out the number of stars from the csv file.
     *
     * @param starList          A list of stars to count form
     * @param filePath          Path of original csv file
     *
     * @return                  String containing the number of stars
     */
    private static String countStarsInfo(List<Star> starList, String filePath){
        return "Loaded in " + starList.size() + " stars (from " + filePath + ").";
    }

    /**
     * Method that prints out the number of rows and characters from the csv file.
     *
     * @param defaultList       A list of list of strings to count from
     * @param filePath          Path of original csv file
     *
     * @return                  String containing the number of rows and characters
     */
    private static String countDefaultInfo(List<List<String>> defaultList, String filePath){
        int sum = 0;
        int numRows = defaultList.size();
        for(List<String> row: defaultList){
            for(String entry: row){
                sum += entry.length();
            }
        }
        return "numRows: " + numRows + ", total characters: " + sum + " (in " + filePath + ").";
    }

}