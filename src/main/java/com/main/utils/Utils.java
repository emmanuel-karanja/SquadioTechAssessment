package com.main.utils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

	private static final DecimalFormat df = new DecimalFormat("0.00");
	public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
	    long startEpochDay = startInclusive.toEpochDay();
	    long endEpochDay = endExclusive.toEpochDay();
	    long randomDay = ThreadLocalRandom
	      .current()
	      .nextLong(startEpochDay, endEpochDay);

	    return LocalDate.ofEpochDay(randomDay);
	}
	
	public static Double randomAmount(Double rangeMin,Double rangeMax) {
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		String result=df.format(randomValue);
		return Double.parseDouble(result);
	}
	
	public static int randomAccNo(int min, int max) {
		 return (int) ((Math.random() * (max - min)) + min);
	}
}
