package edu.brown.cs32.fall22.onboarding.csv;

import java.util.List;
import java.util.Arrays;

/**
 * DefaultCreator class creates a List of Strings from a row of a CSV file.
 */
public class DefaultCreator implements Creator<List<String>> {

    /**
     * Creates a List of Strings by splitting the input string by commas.
     *
     * @param row       Line from a CSV file
     * @return          List of Strings created from the contents of the line
     */
    @Override
    public List<String> create(String row) throws Exception {
        List<String> splitRow = Arrays.asList(row.split(","));
        return splitRow;
    }
}