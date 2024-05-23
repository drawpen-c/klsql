/* Copyright 2024 drawmoon */

package io.github.drawmoon.saber.common;

import java.util.Collection;
import java.util.LinkedList;

public final class Preconditions {

  private Preconditions() {}

  /**
   * Ensures that an object reference passed as a parameter to the calling method is null.
   *
   * @param <T> the type of the reference
   * @param obj the object reference to check for nullity
   */
  public static <T> void ensureNull(T obj) {
    if (obj != null) throw new UnsupportedOperationException();
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is null.
   *
   * @param <T> the type of the reference
   * @param obj the object reference to check for nullity
   * @param errorMessage the exception message to use if the check fails
   */
  public static <T> void ensureNull(T obj, String errorMessage) {
    if (obj != null) throw new UnsupportedOperationException(errorMessage);
  }

  /**
   * Ensure that the string is not null and is not an empty string.
   *
   * @param text the string to check
   */
  public static void checkNullOrWhiteSpace(CharSequence text) {
    checkNullOrWhiteSpace(text, null);
  }

  /**
   * Ensure that the string is not null and is not an empty string.
   *
   * @param text the string to check
   * @param errorMessage the exception message to use if the check fails
   */
  public static void checkNullOrWhiteSpace(CharSequence text, String errorMessage) {
    if (text == null) throw new NullPointerException();

    boolean hasNotNullElement = false;
    for (int i = 0; i < text.length(); i++) {
      try {
        checkWhiteSpace(text.charAt(i));
        hasNotNullElement = true;
        break;
      } catch (NullPointerException e) {
      }
    }

    if (!hasNotNullElement) throw new NullPointerException();
  }

  /**
   * Ensures that the character is not a whitespace character.
   *
   * @param c the character to check
   */
  public static void checkWhiteSpace(char c) {
    if (Character.isWhitespace(c)
        || Character.isSpaceChar(c)
        || c == '\ufeff'
        || c == '\u202a'
        || c == '\u0000'
        || c == '\u3164'
        || c == '\u2800'
        || c == '\u180e') throw new NullPointerException();
  }

  /**
   * Ensures that the collection is not null and that the collection elements are not null.
   *
   * @param <T> the type of the reference
   * @param coll the collection to check
   */
  public static <T> void checkCollectionNotNull(Collection<? extends T> coll) {
    if (coll == null) throw new NullPointerException();

    for (T e : coll) {
      if (e == null) throw new NullPointerException();
    }
  }

  /**
   * Ensures that the collection is not null and that the collection elements are not null.
   *
   * @param <T> the type of the reference
   * @param coll the collection to check
   * @return the cleaned collection
   */
  public static <T> LinkedList<T> collectionNullClean(Collection<? extends T> coll) {
    return collectionNullClean(coll, null);
  }

  /**
   * Ensures that the collection is not null and that the collection elements are not null.
   *
   * @param <T> the type of the reference
   * @param coll the collection to check
   * @param errorMessage the exception message to use if the check fails
   * @return the cleaned collection
   */
  public static <T> LinkedList<T> collectionNullClean(
      Collection<? extends T> coll, String errorMessage) {
    if (coll == null) throw new NullPointerException(errorMessage);

    LinkedList<T> list = new LinkedList<>();
    for (T e : coll) {
      if (e == null) throw new NullPointerException(errorMessage);
      list.add(e);
    }

    return list;
  }
}