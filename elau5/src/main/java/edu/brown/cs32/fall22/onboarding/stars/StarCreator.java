package edu.brown.cs32.fall22.onboarding.stars;
import edu.brown.cs32.fall22.onboarding.csv.Creator;

import java.util.List;
import java.util.Arrays;

/**
 * StarCreator class creates Star objects from a row of a CSV file.
 */
public class StarCreator implements Creator<Star> {

    /**
     * Creates Star objects by splitting the input string into different properties of a Star.
     *
     * @param row       Line from a CSV file
     * @return          Star object created from the contents of the line
     */
    @Override
    public Star create(String row) throws Exception{
        List<String> splitRow = Arrays.asList(row.split(","));

        // error checking row contains all info needed to create Star
        if(splitRow.size() != 5) {
            throw new Exception("Row must contain 5 entries.");
        };

        try {
            long id = Long.parseLong(splitRow.get(0));
            String name = splitRow.get(1);
            double x = Double.parseDouble(splitRow.get(2));
            double y = Double.parseDouble(splitRow.get(3));
            double z = Double.parseDouble(splitRow.get(4));
            return new Star(id, name, x, y, z);
        } catch(NumberFormatException e) {
            throw new Exception("Row entries must be integers or doubles");
        }
    }
}
