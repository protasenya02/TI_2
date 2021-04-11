package com.company;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class rsaEncryption {

  private long p, q, r, f, e, d;


  public String inputOpenText() {

    String openText;
    Scanner input = new Scanner(System.in);
    System.out.println("Input open text:");
    openText = input.nextLine();
    openText = openText.replaceAll("\s", "");

    return openText;
  }

  public void inputP() {

    System.out.println("Input simple number p:");
    Scanner input = new Scanner(System.in);
    long inputNumber = input.nextLong();

    if (!isPrime(inputNumber)) {

      System.out.println("Incorrect input. Try again!");
      inputP();

    } else {

      p = inputNumber;

    }
  }

  public void inputQ() {

    System.out.println("Input simple number q:");
    Scanner input = new Scanner(System.in);
    long inputNumber = input.nextLong();

    if (!isPrime(inputNumber)) {

      System.out.println("Incorrect input. Try again!");
      inputP();

    } else {

      q = inputNumber;

    }
  }

  public ArrayList<Long> encrypt(String openText) {

    ArrayList<Long> cipherText = new ArrayList<>();

    for (int i=0; i<openText.length(); i++){

      long index = openText.charAt(i);
      long number  = quickPower(index, e, r);
      cipherText.add(number);

    }

    return cipherText;
  }

  public String decipher(ArrayList<Long> cipherText) {

    StringBuilder openText = new StringBuilder();

    for (long number : cipherText) {

      int symbol = (int) quickPower(number, d, r);
      openText.append((char) symbol);

    }

    return openText.toString();
  }

  private void calculateR() {

    r = p*q;

  }

  private void calculateF() {

    f = (p-1)*(q-1);

  }

  private void calculateE() {

     boolean wasFound = false;

     while (!wasFound) {
         
         long randomNumber = ThreadLocalRandom.current().nextLong(2,f);

         if ( isPrime(randomNumber) && (isCoprime(randomNumber, f))) {

           wasFound = true;
           e = randomNumber;

         }

     }
  }

  private void calculateD() {

    Triple temp = extendedEuclid(f, e);
    d = temp.getY();

    if (d < 0) {
      d += f;
    }

  }

  public void calculateKeys() {

    calculateR();
    calculateF();
    calculateE();
    calculateD();

  }

  // check number is prime
  private boolean isPrime(long numb) {

    for (long i=2; i <= Math.sqrt(numb); i++) {

      if (numb % i == 0) {
        return false;
      }

    }

    return true;
  }

  // check is two numbers are mutually prime numbers
  private boolean isCoprime(long n1, long n2) {

    return (gcdByEuclid(n1, n2) == 1);

  }

  // search greatest common divisor
  private long gcdByEuclid(long n1, long n2) {

    if (n2 == 0) {
      return n1;
    }

    return gcdByEuclid(n2, n1 % n2);
  }

  private Triple extendedEuclid(long a, long b) {

    if (b == 0) {

      return new Triple(a,1,0);

    } else {

      Triple temp = extendedEuclid(b, a % b);

      long d = temp.getD();
      long x = temp.getY();
      long y = temp.getX() - temp.getY() * (a / b);

      return new Triple(d,x,y);
    }

  }

  private long quickPower(long index, long y, long n) {

    if (y == 0) return 1;

    long z = quickPower(index, y / 2, n);

    if (y % 2 == 0) {

      return (z * z) % n;

    } else {

      return (index * z * z) % n;

    }

  }

  public void outputOpenKey() {

    System.out.println("Open key: {"+e+","+r+"}" );

  }

  public void outputPrivateKey() {

    System.out.println("Private key: {"+d+","+r+"}" );

  }

  public void generateP(long maxValue) {

    p = randomPrimeNumber(maxValue);

  }

  public void generateQ(long maxValue) {

     q = randomPrimeNumber(maxValue);

  }

  private long randomPrimeNumber(long maxValue) {

    boolean wasFound = false;
    long result = 0;

    while (!wasFound) {

      long randomNumber = ThreadLocalRandom.current().nextLong(2, maxValue);

      if ( isPrime(randomNumber)) {

        wasFound = true;
        result = randomNumber;

      }

    }

    return result;
  }

  public long getP() {
    return p;
  }

  public long getQ() {
    return q;
  }
}
