package io;

import java.io.*;

public class FileOut
{

/** Testavimui skirtas metodas. Testuojant komandinëje eilutëje galite surinkti:
 > java io.FileOut rezult.txt vienas du "du su puse" trys keturi penki
*/
public static void main (String args[]){
if (args==null||args.length<1){
	 System.out.println("Programos naudojimas:\r\njava io.FileOut rezultatuFailas eilute1 eilute2 ...");
	 System.exit(1);
 } String[] S = new String[args.length-1];
 for (int i=1; i<args.length; i++) S[i-1]=args[i];
 FileOut.write(args[0],S);
}

/** Duomenø failo sukûrimo metodas. Sukuria failà ir áraðo jame eiluèiø masyvà. */
public static boolean write(String failas, String[] data)
{
// Strautas áraðymui
FileOutputStream fout;
try{
 fout = new FileOutputStream (failas); // Atidarome srauta
 PrintStream f = new PrintStream(fout);

// Áraðome visas eilutes
 for (int i=0; i<data.length; i++) f.println(data[i]);
 f.close(); fout.close(); // uþdarome srautus
 }
 catch (IOException e) // apdorojame klaidas
 {
 System.err.println("Negaliu yrasyti i "+failas);
 return false;
 }
 return true;
} // write

/** Duomenø failo sukûrimo metodas. Sukuria failà ir data sraute esanchius duomenis. */
public static boolean write(String failas, InputStream data)
{
// Strautas áraðymui
FileOutputStream fOut;
try{
 fOut = new FileOutputStream (failas); // Atidarome srauta
 while (data.available()>0){
	 byte[] b = new byte[data.available()];
	 data.read(b);
	 fOut.write(b);
 }
 fOut.close(); // failo srauta
 }
 catch (IOException e) // apdorojame klaidas
 {
 System.err.println("Negaliu yrasyti i "+failas);
 return false;
 }
 return true;
} // write
} //FileOut
