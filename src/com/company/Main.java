package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

      rsaEncryption rsa = new rsaEncryption();

      rsa.inputP();
      rsa.inputQ();
      rsa.calculateR();
      rsa.calculateF();
      rsa.calculateE();
      rsa.calculateD();

      System.out.println("r " + rsa.getR());
      System.out.println("f " + rsa.getF());
      System.out.println("e " + rsa.getE());
      System.out.println("d " + rsa.getD());

      String openText;
      Scanner input = new Scanner(System.in);
      System.out.println("Input open text:");
      openText = input.nextLine();
      openText = openText.replaceAll("\s", "");

      ArrayList<Long> enctyptArray = rsa.encrypt(openText);
      System.out.println(enctyptArray.toString());
      String openTextX = rsa.decipher(enctyptArray);
      System.out.println(openTextX);

    }
}
