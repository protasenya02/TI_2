package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    rsaEncryption rsa = new rsaEncryption();

    // input p and q
    rsa.inputP();
    rsa.inputQ();

    // autogenerate p and q
//    System.out.println("Intut max value:");
//    Scanner input = new Scanner(System.in);
//    long maxValue = input.nextLong();
//    rsa.generateP(maxValue);
//    rsa.generateQ(maxValue);
//    System.out.println("p: "+ rsa.getP());
//    System.out.println("q: "+ rsa.getQ());

    rsa.calculateKeys();

    System.out.println();
    rsa.outputOpenKey();
    rsa.outputPrivateKey();
    System.out.println();

    String openText = rsa.inputOpenText();
    ArrayList<Long> enctyptArray = rsa.encrypt(openText);

    StringBuilder encryptText = new StringBuilder();
    for (Long numb: enctyptArray) {
      encryptText.append(numb.toString());
    }

    System.out.println("\nEncypt text: " + enctyptArray.toString());
    System.out.println("Encypt text: " + encryptText);

    String decryptText = rsa.decipher(enctyptArray);
    System.out.println("Decrypt text : " + decryptText);
  }

}