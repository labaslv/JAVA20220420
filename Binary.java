package io;

import java.io.*;
import java.util.*;

public class Binary
{

/** Testavimui skirtas metodas.<br> Testuojant komandin�je eilut�je galite surinkti:<br>
 > java io.Binary Binary.java
*/
public static void main (String args[]) throws IOException{
 if (args==null||args.length<2){
	 System.out.println("Programos naudojimas:\r\njava io.Binary ASCIIskaiciuFailas binaryFailas"+
	 				"\r\narba :\r\n io.Binary -b binaryFailas  ASCIIskaiciuFailas\r\n");
	 System.exit(1);
 }
  // Pagal pirm�j� parametr� nusprend�iame ar reikia ASCII
  // formato skai�ius konvertuoti � binarinius ar atvirk��iai
 if (args[0].compareTo("-b")!=0)
 	FileOut.write(args[1],readASCII(args[0])); // Binarini� rezultat� �ra�ymui naudojame savo io paketo FileOut klas�s metod� write
 	else FileOut.write(args[2],readBinary(args[1])); // ASCII rezultat� �ra�ymui naudojame savo io paketo FileOut klas�s metod� write
}

/** Failo nuskaitymo metodas. Gr��ina binarini� duomen� sraut�. */
public static InputStream readASCII(String failas) throws IOException{
String[] ASCIIskaiciai = ReadFile.file(failas); // nadojame savo io paketo ReadFile klase ASCII failo nuskaitymui
Vector skaiciai; //Kadangi ne�inome skaiciu kiekio duomenims talpinti naudojame Vector klas�
OutputStream out = new ByteArrayOutputStream(); // Duomenis patartina �ra�in�ti � gana auk�to abstraktumo klas�
DataOutputStream dat = new DataOutputStream(out); // Kadangi nuskaitoma informacija bus skaitin� informacija duomen� talpinimui patogu naudoti DataOutputStream klas�
dat.writeInt(ASCIIskaiciai.length); // �simename kiek buvo faile eilu�i�
for (int n = 0; n<ASCIIskaiciai.length; n++){
	skaiciai = ascii2integer(ASCIIskaiciai[n]); // naudojame savo ascii2integer metod� apdoroti vienos eilut�s ASCII duomenis
	int visoSkaiciu = skaiciai.size();
	dat.writeInt(visoSkaiciu); // �simename kiek buvo eilut�je skai�i�
	for (int k = 0; k<visoSkaiciu; k++)
		dat.writeInt(((Integer)skaiciai.elementAt(k)).intValue()); // �ra�ome � sraut� po vien� nuskaityt� skai�i�
}
dat.flush(); out.flush(); // I�valome sraut� buferius, kad b�tume garantuti jog visi perduoti duomenys pateko � sraut�
return new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray()); // gr��iname nuskaityt� duomen� sraut�
} // readASCII

/** Konvertuoja ASCII formato skai�ius � Integer objektus gr��inamu Vector objekte*/
public static Vector ascii2integer(String data){
	Vector skaiciai = new Vector();
	StringTokenizer st = new StringTokenizer(data," \t,"); // Atskir� eilut�s skaici� nuskaitymui naudojame StringTokenizer klas�
	while (st.hasMoreTokens()){
		skaiciai.add(new Integer(Integer.parseInt(st.nextToken())));
	}
	return skaiciai;
}

/** Binarini� duomen� failo nuskaitymo metodas. Gr��ina binarini� duomen� sraut�. */
public static String[] readBinary(String failas) throws IOException{

FileInputStream fin;
fin = new FileInputStream (failas); // Atidarome duomen� failo srauta
DataInputStream in = new DataInputStream(fin); // Skaitini� duomen� nuskaitymui patogu naudoti DataInputStream
String[] ASCIIskaiciai = new String[in.readInt()]; // �ia talpinsime rezultatus
for (int n = 0; n<ASCIIskaiciai.length; n++){
	int M = in.readInt(); // Nuskaitome kiek buvo eilut�je skai�i�
	String skaiciuEilute = ""; // DArbinis kintamasis, kuriame formuosime ASCII skai�i� eilut�
	for (int m = 0; m<M; m++) skaiciuEilute += in.readInt()+"\t"; // Nuskaitome po vien�  binarin� skai�i� ir formuojame ASCII skai�i� eilut�
	ASCIIskaiciai[n] = new String(skaiciuEilute); // Patalpiname � masyv� suformuotos eilut�s kopij�.
}
return ASCIIskaiciai; // Gr��iname rezultat�
} // readBibary
} //Binary.java
