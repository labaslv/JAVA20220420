package io;

// Skaitymas i� standartinio �vedimo �renginio (klaviat�ros).
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
    // Tu��ia eilut� nutraukia �vedim�
  }
}