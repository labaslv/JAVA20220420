package io;

import java.io.*;
import java.util.*;

public class Binary
{

/** Testavimui skirtas metodas.<br> Testuojant komandinëje eilutëje galite surinkti:<br>
 > java io.Binary Binary.java
*/
public static void main (String args[]) throws IOException{
 if (args==null||args.length<2){
	 System.out.println("Programos naudojimas:\r\njava io.Binary ASCIIskaiciuFailas binaryFailas"+
	 				"\r\narba :\r\n io.Binary -b binaryFailas  ASCIIskaiciuFailas\r\n");
	 System.exit(1);
 }
  // Pagal pirmàjá parametrà nusprendþiame ar reikia ASCII
  // formato skaièius konvertuoti á binarinius ar atvirkðèiai
 if (args[0].compareTo("-b")!=0)
 	FileOut.write(args[1],readASCII(args[0])); // Binariniø rezultatø áraðymui naudojame savo io paketo FileOut klasës metodà write
 	else FileOut.write(args[2],readBinary(args[1])); // ASCII rezultatø áraðymui naudojame savo io paketo FileOut klasës metodà write
}

/** Failo nuskaitymo metodas. Gràþina binariniø duomenø srautà. */
public static InputStream readASCII(String failas) throws IOException{
String[] ASCIIskaiciai = ReadFile.file(failas); // nadojame savo io paketo ReadFile klase ASCII failo nuskaitymui
Vector skaiciai; //Kadangi neþinome skaiciu kiekio duomenims talpinti naudojame Vector klasæ
OutputStream out = new ByteArrayOutputStream(); // Duomenis patartina áraðinëti á gana aukðto abstraktumo klasæ
DataOutputStream dat = new DataOutputStream(out); // Kadangi nuskaitoma informacija bus skaitinë informacija duomenø talpinimui patogu naudoti DataOutputStream klasæ
dat.writeInt(ASCIIskaiciai.length); // ásimename kiek buvo faile eiluèiø
for (int n = 0; n<ASCIIskaiciai.length; n++){
	skaiciai = ascii2integer(ASCIIskaiciai[n]); // naudojame savo ascii2integer metodà apdoroti vienos eilutës ASCII duomenis
	int visoSkaiciu = skaiciai.size();
	dat.writeInt(visoSkaiciu); // ásimename kiek buvo eilutëje skaièiø
	for (int k = 0; k<visoSkaiciu; k++)
		dat.writeInt(((Integer)skaiciai.elementAt(k)).intValue()); // Áraðome á srautà po vienà nuskaitytà skaièiø
}
dat.flush(); out.flush(); // Iðvalome srautø buferius, kad bûtume garantuti jog visi perduoti duomenys pateko á srautà
return new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray()); // gràþiname nuskaitytø duomenø srautà
} // readASCII

/** Konvertuoja ASCII formato skaièius á Integer objektus gràþinamu Vector objekte*/
public static Vector ascii2integer(String data){
	Vector skaiciai = new Vector();
	StringTokenizer st = new StringTokenizer(data," \t,"); // Atskirø eilutës skaiciø nuskaitymui naudojame StringTokenizer klasæ
	while (st.hasMoreTokens()){
		skaiciai.add(new Integer(Integer.parseInt(st.nextToken())));
	}
	return skaiciai;
}

/** Binariniø duomenø failo nuskaitymo metodas. Gràþina binariniø duomenø srautà. */
public static String[] readBinary(String failas) throws IOException{

FileInputStream fin;
fin = new FileInputStream (failas); // Atidarome duomenø failo srauta
DataInputStream in = new DataInputStream(fin); // Skaitiniø duomenø nuskaitymui patogu naudoti DataInputStream
String[] ASCIIskaiciai = new String[in.readInt()]; // Èia talpinsime rezultatus
for (int n = 0; n<ASCIIskaiciai.length; n++){
	int M = in.readInt(); // Nuskaitome kiek buvo eilutëje skaièiø
	String skaiciuEilute = ""; // DArbinis kintamasis, kuriame formuosime ASCII skaièiø eilutæ
	for (int m = 0; m<M; m++) skaiciuEilute += in.readInt()+"\t"; // Nuskaitome po vienà  binariná skaièiø ir formuojame ASCII skaièiø eilutæ
	ASCIIskaiciai[n] = new String(skaiciuEilute); // Patalpiname á masyvà suformuotos eilutës kopijà.
}
return ASCIIskaiciai; // Gràþiname rezultatà
} // readBibary
} //Binary.java
