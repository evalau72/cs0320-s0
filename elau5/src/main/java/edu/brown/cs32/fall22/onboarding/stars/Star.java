package edu.brown.cs32.fall22.onboarding.stars;

/**
 * A **RECORD** class. Java automatically creates equals,
 * toString, etc. Immutable data, so we get getters, but not
 * setters.
 *
 * This requires Java 16 or higher (and might need setting
 * in language level as well as project structure)
 */
public record Star(long id, String name, double x, double y, double z) {}
