package edu.brown.cs32.fall22.onboarding.csv;

import java.util.List;

/**
 * Parser interface. Implementation classes define how to parse.
 */
public interface Parser<T> extends Iterable<T> {
    List<T> parse();
}