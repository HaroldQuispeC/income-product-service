package com.bootcamp.incomeproductservice.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

  private Util(){}

  public static class Random {

    /**
     * Method getNumericString.
     *
     * @param randomLength int
     * @return String
     */
    public String getNumericString(int randomLength) {
      List<String> numbers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
      long poolSize = numbers.stream().count();

      if (randomLength <= 0) {
        return "";
      }

      String[] newNumber;
      newNumber = new String[randomLength];

      return Arrays.stream(newNumber).map(item -> {
        int index = (int) (poolSize * Math.random());
        item = numbers.get(index);
        return item;
      }).map(Object::toString).collect(Collectors.joining());
    }

    /**
     * Method getNumericString with spliting separator.
     *
     * @param randomLength int
     * @param separator    String
     * @return String
     */
    public String getNumericString(int randomLength, int numberBlocks, String separator) {
      List<String> numbers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
      String[] blocks;
      blocks = new String[numberBlocks];

      if (randomLength <= 0) {
        return "";
      }

      for (int i = 0; i < numberBlocks; i++) {
        long poolSize = numbers.stream().count();
        String[] newNumber;
        newNumber = new String[randomLength];
        blocks[i] = Arrays.stream(newNumber).map(item -> {
          int index = (int) (poolSize * Math.random());
          item = numbers.get(index);
          return item;
        }).map(Object::toString).collect(Collectors.joining());
      }

      return Arrays.stream(blocks).map(Object::toString).collect(Collectors.joining(separator));
    }
  }
}
