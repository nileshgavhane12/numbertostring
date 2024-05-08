package number.numbertostring;

import javax.servlet.ServletException;
import java.io.IOException;

public class NumberToWord {
  static String string;
  static String st1[] = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
  static String st2[] = {"Hundred", "Thousand", "Lakh", "Crore", "Arab"};
  static String st3[] = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
      "Eighteen", "Nineteen"};
  static String st4[] = {"Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

  public static String getNumberToWord(double n, String currency_name) {

    double money = n;
    int dollars = (int) Math.floor(money);
    double cents = money - dollars;
    int centsAsInt = (int) Math.round((100 * cents));
    String s = "";
    if (dollars == 00) {
      s = "Zero " + currency_name;
    } else if (centsAsInt != 00) {
      s = currency_name + " " + mac(dollars) + " And " + mac(centsAsInt) + " paise only";
    } else if (dollars != 00) {
      s = currency_name + " " + mac(dollars) + " Only";
    }
    return s;
  }

  public static String mac(int n) {
    int word;
    int nu = 1;
    string = "";
    while (n != 0) {
      switch (nu) {
        case 1 :
          word = n % 100;
          pass(word);
          if (n > 100 && n % 100 != 0) {
            show("and ");
          }
          n = n / 100;
          break;
        case 2 :
          word = n % 10;
          if (word != 0) {
            show(" ");
            show(st2[0]);
            show(" ");
            pass(word);
          }
          n = n / 10;
          break;
        case 3 :
          word = n % 100;
          if (word != 0) {
            show(" ");
            show(st2[1]);
            show(" ");
            pass(word);
          }
          n = n / 100;
          break;
        case 4 :
          word = n % 100;
          if (word != 0) {
            show(" ");
            show(st2[2]);
            show(" ");
            pass(word);
          }
          n = n / 100;
          break;
        case 5 :
          word = n % 100;
          if (word != 0) {
            show(" ");
            show(st2[3]);
            show(" ");
            pass(word);
          }
          n = n / 100;
          break;
      }
      nu++;
    }
    return string;
  }

  public static void pass(int n) {
    int word, q;
    if (n > 0 && n < 10) {
      show(st1[n]);
    }
    if (n > 9 && n < 20) {
      show(st3[n - 10]);
    }
    if (n > 19) {
      word = n % 10;
      if (word == 0) {
        q = n / 10;
        show(st4[q - 2]);
      } else {
        q = n / 10;
        show(st1[word]);
        show(" ");
        show(st4[q - 2]);
      }
    }
  }

  public static void show(String s) {
    String st = string;
    string = s + st;
  }

  public static String getCheckData(String listdata) throws ServletException, IOException {
    boolean isMinus = false;
    if (listdata.startsWith("-")) {
      listdata = listdata.substring(1, listdata.length());
      isMinus = true;
    }
    String digit = null;

    String number = listdata.split("\\.")[0];

    String n = listdata;

    String n2 = n.substring(Math.max(n.length() - 2, 1));

    int l1 = number.length();

    // System.err.println("number -> " + number + " <- n -> " + n + " <- n2 -> " +
    // n2 + " <- l1 -> " + l1);

    if (l1 == 6) {
      String firstdigit = (number).substring(0, 1);
      String lastdigit = (number).substring(1, 3);
      digit = firstdigit + "." + lastdigit + " L";
    } else if (l1 == 7) {
      String firstdigit = (number).substring(0, 2);
      String lastdigit = (number).substring(2, 4);
      digit = firstdigit + "." + lastdigit + " L";
    } else if (l1 == 8) {
      String firstdigit = (number).substring(0, 1);
      String lastdigit = (number).substring(1, 3);
      digit = firstdigit + "." + lastdigit + " C";
    } else if (l1 == 9) {
      String firstdigit = (number).substring(0, 2);
      String lastdigit = (number).substring(2, 4);
      digit = firstdigit + "." + lastdigit + " C";
    } else if (l1 == 10) {
      String firstdigit = (number).substring(0, 3);
      String lastdigit = (number).substring(3, 5);
      digit = firstdigit + "." + lastdigit + " C";
    } else if (l1 == 11) {
      String firstdigit = (number).substring(0, 4);
      String lastdigit = (number).substring(4, 6);
      digit = firstdigit + "." + lastdigit + " C";
    } else if (l1 == 12 || n2.equals("E7")) {
      // String firstdigit = (number).substring(0, 1);
      // digit = firstdigit + " C";
      String formattedNumber = String.format("%.15f", Double.parseDouble(n));
      int decimalPointIndex = formattedNumber.indexOf('.');
      String firstdigit = (formattedNumber).substring(0, 1);
      String seconddigit = formattedNumber.substring(decimalPointIndex + 1,
          Math.min(decimalPointIndex + 3, formattedNumber.length()));

      // String thirddigit = (n).substring(4, 5);
      digit = firstdigit + "." + seconddigit + "C";

    } else if (l1 == 13 || n2.equals("E8")) {
      String firstdigit = (number).substring(0, 1);
      String lastdigit = (n).substring(2, 3);
      String formattedNumber = String.format("%.2f", Double.parseDouble(n));
      int decimalPointIndex = formattedNumber.indexOf('.');
      String secondDigit = formattedNumber.substring(decimalPointIndex + 1,
          Math.min(decimalPointIndex + 3, formattedNumber.length()));
      digit = firstdigit + lastdigit + "." + secondDigit + "C";
    } else if (l1 == 14 || n2.equals("E9")) {
      String firstdigit = (number).substring(0, 1);
      String seconddigit = (n).substring(2, 3);
      String thirddigit = (n).substring(4, 5);
      digit = firstdigit + seconddigit + thirddigit + "C";
    } else if (l1 == 15 || n2.equals("E10")) {
      String firstdigit = (number).substring(0, 1);
      String seconddigit = (n).substring(2, 3);
      String thirddigit = (n).substring(4, 5);
      String forthdigit = (n).substring(6, 7);
      digit = firstdigit + seconddigit + thirddigit + forthdigit + "C";
    } else if (l1 == 16 || n2.equals("E11")) {
      String firstdigit = (number).substring(0, 1);
      String seconddigit = (n).substring(2, 3);
      String thirddigit = (n).substring(4, 5);
      String forthdigit = (n).substring(6, 7);
      String fifthdigit = (n).substring(8, 9);
      digit = firstdigit + seconddigit + thirddigit + forthdigit + fifthdigit + "C";
    } else {
      digit = number;

      // digit=Double.toString(number);
    }
    if (isMinus) {
      digit = "-" + digit;
    }
    return digit;
  }
}