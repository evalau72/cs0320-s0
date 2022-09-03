package edu.brown.cs32.fall22.onboarding.csv;

import java.io.Reader;

/**
 * Builder class for CSVParser which allows for CSV Parsers to be safely
 * built with optional properties.
 *
 */
public class CSVParserBuilder<T>{

    private final Reader reader; // required
    private final boolean parseHeader; // required
    private Creator<T> creator; // optional

    /**
     * constructor; requires mandatory fields
     *
     * @param reader        Reader for CSVParser file
     * @param parseHeader   Whether or not the header should be considered in the parsing
     */
    public CSVParserBuilder(Reader reader, boolean parseHeader){
        this.reader = reader;
        this.parseHeader = parseHeader;
    }

    /**
     * Setter for Creator property.
     *
     * @param creator       Creator being set
     * @return              Updated builder with newly set creator
     */
    public CSVParserBuilder creator(Creator<T> creator){
        this.creator = creator;
        return this;
    }

    /**
     * Builds the CSV Parser with specified properties of this builder.
     *
     * @return              CSVParser with specified properties of this builder
     */
    public CSVParser<T> build() throws Exception {
        if ((this.reader == null)){
            throw new Exception("Builder must have reader and parse header indicator set.");
        }

        return new CSVParser<T>(this);
    }

    /**
     * Getter for reader property.
     *
     * @return              builder's reader object
     */
    public Reader getReader(){
        return this.reader;
    }

    /**
     * Getter for parseHeading property.
     *
     * @return              builder's parseHeading setting
     */
    public boolean getParseHeader(){
        return this.parseHeader;
    }

    /**
     * Getter for creator property.
     *
     * @return              builder's creator object
     */
    public Creator<T> getCreator(){
        return this.creator;
    }
}