package edu.brown.cs32.fall22.onboarding.csv;

/**
 * Creator interface for CSV Parser. Implementation classes
 * define what gets created from a row of a CSV file.
 *
 */
public interface Creator<T> {
    T create(String row) throws Exception;
}