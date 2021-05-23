package caesarCipher;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class CipherAlogirthm {
	//Main method
	public static void main(String[]args) throws IOException, InterruptedException {
		//Path to file to encrypt and storing that file's content for encryption
		File file = new File("/Users/Joshu/Desktop/Geogia State University/GSU FALL 2020/Cyber Security/plaintext test3.txt");
		Scanner scan = new Scanner(file);
		Scanner user = new Scanner(System.in);
		String fileContent = "";
		while(scan.hasNextLine()) {
			fileContent = fileContent.concat(scan.nextLine() + "\n");
		}
		
		//File contents before encryption
		System.out.println("The following file will be encrypted: ");
		TimeUnit.SECONDS.sleep(1);
		System.out.print(fileContent);
		System.out.println();
		
		//This is the key generation, which is left up to the user to decide what value to use
		System.out.println("Pick a number for the shift between 0-127: ");
		int shft = user.nextInt();
		//System.out.println("Random key between 0 and 25 : "  + shft);
		//Displaying the encrypted file and measuring how long it took to encrypt the file
		long startTime = System.nanoTime();
		System.out.println("Cipher text: " + encrypt(fileContent, shft));
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		
	    TimeUnit.SECONDS.sleep(1);
		
		//Path to file to decrypt and storing file's content for decryption
		File file2 = new File("/Users/Joshu/Desktop/Geogia State University/GSU FALL 2020/Cyber Security/ciphertext test1.txt");
		Scanner scan2 = new Scanner(file2);
		String fileContent2 = "";
		while(scan2.hasNextLine()) {
			fileContent2 = fileContent2.concat(scan2.nextLine() + "\n");
		}
		
		//Displaying the decrypted file and measuring how long it took to decrypt the file
		long startTime2 = System.nanoTime();
		System.out.println("Plain text: " + decrypt(fileContent2, shft));
		long endTime2 = System.nanoTime();
		long duration2 = (endTime2 - startTime2) / 1000000;
		
		System.out.println("Duration to encrypt the file: " + duration + " milliseconds");
		System.out.println("Duration to decrypt the file: " + duration2 + " milliseconds");
	}
	
	//This is the encryption method that takes in a string and the key and encrypts the string and then outputs that encrypted string to a txt document
	public static StringBuffer encrypt(String text, int shift) throws IOException {
		StringBuffer output = new StringBuffer(); 
		String space = " ";		
		for(int i = 0; i < text.length(); i++) {
			if(Character.isUpperCase(text.charAt(i))) {
				char ch = (char)(((int)text.charAt(i) + shift) % 223);
				output.append(ch);
			} else if (Character.isWhitespace(text.charAt(i))) {
				char ch = text.charAt(i);
				output.append(ch);
			} else {
				char ch = (char)(((int)text.charAt(i) + shift) % 223);
				output.append(ch);
			}
		}
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File( 
				"/Users/Joshu/Desktop/Geogia State University/GSU FALL 2020/Cyber Security/ciphertext test1.txt")));
		bwr.write(output.toString());
		bwr.flush();
		bwr.close();
		return output;
	}
	
	//This is the decryption method that takes in a string and the key and decrypts the string and then outputs that decrypted string to a txt document
	public static StringBuffer decrypt(String text, int shift) throws IOException {
		StringBuffer output = new StringBuffer(); 
		String space = " ";		
		for(int i = 0; i < text.length(); i++) {
			if(Character.isUpperCase(text.charAt(i))) {
				char ch = (char)(((int)text.charAt(i) + 223 - shift % 223) % 223);
				output.append(ch);
			} else if (Character.isWhitespace(text.charAt(i))) {
				char ch = text.charAt(i);
				output.append(ch);
			} else {
				char ch = (char)(((int)text.charAt(i) + 223 - shift % 223) % 223);
				output.append(ch);
			}
		}
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File( 
				"/Users/Joshu/Desktop/Geogia State University/GSU FALL 2020/Cyber Security/decryptedText test1.txt")));
		bwr.write(output.toString());
		bwr.flush();
		bwr.close();
		return output;
	}
}
