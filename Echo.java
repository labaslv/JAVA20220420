package io;

// Skaitymas ið standartinio ávedimo árenginio (klaviatûros).
import java.io.*;

public class Echo {
 public static void main(String[] args) throws IOException {
    System.out.println(" Iveskite eilute ir paspauskite Enter\n"+
	" Ivedimas uzbaigiamas tushcia eilute");
 
    BufferedReader in =
        new BufferedReader(new InputStreamReader(System.in));
    String s;
    while((s = in.readLine()).length() != 0)
      System.out.println(s);
    // Tuðèia eilutë nutraukia ávedimà
  }
}