package edu.brown.cs32.fall22.onboarding.csv;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Iterator;

public class CSVParser<T> implements Parser<T> {

    private final BufferedReader reader;
    private final boolean parseHeader;
    private Creator<T> creator;

    /**
     * Constructor generic CSV parser.
     *
     * @param builder        TODO
     */
    public CSVParser(CSVParserBuilder builder) {
        this.reader = new BufferedReader(builder.getReader());
        this.parseHeader = builder.getParseHeader();
        this.creator = builder.getCreator();
    }

    @Override
    public List<T> parse()  {
        List<T> content = new ArrayList<>();

        try {
            // read header
            String entry = reader.readLine();
            if (parseHeader) {
                content.add(parseLine(entry));
            } else {
                while ((entry = reader.readLine()) != null) {
                    content.add(parseLine(entry));
                }
            }
        } catch (IOException e) {
            System.out.println("readLine failed");
            return null;
        } catch (Exception ex) {
            System.out.println("parseLine failed");
            return null;
        }

        return content;
    }

    public T parseLine(String line) throws Exception{
        return creator.create(line);
    }

    @Override
    public Iterator<T> iterator() {
        return new CSVIterator();
    }

    private class CSVIterator implements Iterator<T> {
        private T next;

        @Override
        public boolean hasNext() {
            // checks that next has been set already
            if (next != null) {
                return true;
            }

            // reads the next entry to set next
            try {
                String line = reader.readLine();
                return line != null;
            } catch (IOException e) {
                System.out.println("ERROR: readline failed");
                e.printStackTrace();
            }

            return false;
        }

        @Override
        public T next() {
            T entry = null;
            // checks that next has been set already
            if (next != null) {

                entry = next;
                next = null;
            } else {
                // nextEntry is null, so read the next entry and return it
                try {
                    String line = reader.readLine();
                    entry = CSVParser.this.parseLine(line);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return entry;
        }
    }
}