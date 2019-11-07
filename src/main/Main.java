/*
 * Vitalii Chernetskyi
 * Encryption combining Transposition and Substitution methods
 * 07.11.2019
 * CS-522a
 * Faculty of Cybersecurity, Computer and Software Engineering
 * National Aviation University
 */

package main;

import encryption.*;
import io.*;

public class Main {

    public static void main(String[] args) {

        Read read = new Read();
        Write write = new Write();
        TranspCipher firstEnc = new TranspCipher();
        HronsfeldCode secondEnc = new HronsfeldCode();

        String com, result;

        System.out.println("Input number of command\n1 - Code\n2 - Decode");
        com = read.readString();
        if(com.equals("1")){
            System.out.println("Input file name with text to code:");
            String fileName = read.readString();
            String text = read.readStringFile(fileName);
            System.out.println("Input number key (in range 0 - 9):");
            String key = read.readString();
            result = firstEnc.transpCipherEnc(text, key, "code");
            result = secondEnc.codeHronsfeld(result, key, "code");
            System.out.println("Result:\n" + result + "\nResult will be written to the file.");
            write.writeToFile("Result.txt", result);
        }
        else if(com.equals("2")){
            System.out.println("Input file name with text to decode:");
            String fileName = read.readString();
            String text = read.readStringFile(fileName);
            System.out.println("Input number key (in range 0 - 9):");
            String key = read.readString();
            result = secondEnc.codeHronsfeld(text, key, "decode");
            result = firstEnc.transpCipherEnc(result, key, "decode");
            System.out.println("Result:\n" + result + "\nResult will be written to the file.");
            write.writeToFile("Result.txt", result);
        }

    }
}
