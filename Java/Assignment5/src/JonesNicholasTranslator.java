/**
 @Title: JonesNicholasTranslator
 @Purpose: To make an alphabetic telephone number translator
 @Author: (Jones, Nicholas)
 @Date: (11/30/2017)
 @Version 1.0
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JonesNicholasTranslator {

    public static void main(String[] args) throws IOException {

        List<String> alphabeticPhoneNumbers = new ArrayList<>();
        List<String> equivalentPhoneNumber = new ArrayList<>();
        List<String> errorProneNumbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Whats the path to the file with Numbers?");
        //My file was "C:\\Users\\armyj\\IdeaProjects\\Assignment5\\src\\PhoneNumbers.txt"
        String Filepath = sc.nextLine();

        Scanner file = new Scanner(new File(Filepath));

        while (file.hasNext()) {

            String line = file.next();

            //Checks for Invalid Lengths
            if((line.length() > 14) || (line.length() < 12))
                errorProneNumbers.add(line);
            //Checks for Symbols
            else if(checkSymbols(line))
                errorProneNumbers.add(line);
            //Checks for Alphabetical Characters
           else if(line.matches(".*[A-Za-z].*"))
                alphabeticPhoneNumbers.add(line);
           //Sends rest to the Numbers only category
           else
                equivalentPhoneNumber.add(line);
        }
        file.close();

        boolean test = false;

        for(int i = 0; i < alphabeticPhoneNumbers.size(); i++){

            String alphaList = alphabeticPhoneNumbers.get(i);

            for(int j = 0; j < equivalentPhoneNumber.size(); j++){

                String equivList = equivalentPhoneNumber.get(j);

                for(int k = 0; k < equivList.length(); k++){

                    if(checkChar(alphaList, equivList, k)){
                        test = true;
                    }
                    else{
                        test = false;
                        k = equivList.length();
                    }
                }

                if(test){
                    equivalentPhoneNumber.add(i, equivList);
                    equivalentPhoneNumber.remove(j);
                }

            }
        }

        //Prints all the Phone Numbers by Group
        System.out.println("Alphabetic Phone Numbers");

        for (String a : alphabeticPhoneNumbers) {
            System.out.print("[" + a + "]");
        }

        System.out.println("\nEquivalent Phone Numbers");

        for (String b : equivalentPhoneNumber) {
            System.out.print("[" + b + "]");
        }

        System.out.println("\nError Prone Phone Numbers");

        for (String c : errorProneNumbers) {
            System.out.print("[" + c + "]");
        }
    }
    //Method to check for Symbols
    private static boolean checkSymbols(String check) {
        if (check.contains("!"))
            return true;
        else if (check.contains("@"))
            return true;
        else if (check.contains("#"))
            return true;
        else if(check.contains("$"))
            return true;
        else if(check.contains("%"))
            return true;
        else if(check.contains("^"))
            return true;
        else if(check.contains("&"))
            return true;
        else if(check.contains("*"))
            return true;
        else if(check.contains("+"))
            return true;
        else if(check.contains("="))
            return true;
        else if(check.contains("~"))
            return true;
        else if(check.contains("`"))
            return true;
        else if(check.contains("?"))
            return true;
        else if(check.contains("<"))
            return true;
        else if(check.contains(">"))
            return true;
        else if(check.contains(","))
            return true;
        else if(check.contains("."))
            return true;
        else if(check.contains("/"))
            return true;
        else if(check.contains(";"))
            return true;
        else if(check.contains(":"))
            return true;
        else if(check.contains("'"))
            return true;
        else if(check.contains("|"))
            return true;
        else if(check.contains("_"))
            return true;
        else
            return false;
    }
    //Method to check if Char is the same between Alphabetic and its Equivalent
    private static boolean checkChar(String alphaList, String equivList, int k){

        if(equivList.charAt(k) == alphaList.charAt(k))
            return true;

        else if(equivList.charAt(k) == '2'){
            if(alphaList.charAt(k) == 'a')
                return true;
            else if(alphaList.charAt(k) == 'A')
                return true;
            else if(alphaList.charAt(k) == 'b')
                return true;
            else if(alphaList.charAt(k) == 'B')
                return true;
            else if(alphaList.charAt(k) == 'c')
                return true;
            else if(alphaList.charAt(k) == 'C')
                return true;
            else
                return false;
        }

        else if(equivList.charAt(k) == '3'){
            if(alphaList.charAt(k) == 'd')
                return true;
            else if(alphaList.charAt(k) == 'D')
                return true;
            else if(alphaList.charAt(k) == 'e')
                return true;
            else if(alphaList.charAt(k) == 'E')
                return true;
            else if(alphaList.charAt(k) == 'f')
                return true;
            else if(alphaList.charAt(k) == 'F')
                return true;
            else
                return false;
        }

        else if(equivList.charAt(k) == '4'){
            if(alphaList.charAt(k) == 'g')
                return true;
            else if(alphaList.charAt(k) == 'G')
                return true;
            else if(alphaList.charAt(k) == 'h')
                return true;
            else if(alphaList.charAt(k) == 'H')
                return true;
            else if(alphaList.charAt(k) == 'i')
                return true;
            else if(alphaList.charAt(k) == 'I')
                return true;
            else
                return false;
        }

        else if(equivList.charAt(k) == '5'){
            if(alphaList.charAt(k) == 'j')
                return true;
            else if(alphaList.charAt(k) == 'J')
                return true;
            else if(alphaList.charAt(k) == 'k')
                return true;
            else if(alphaList.charAt(k) == 'K')
                return true;
            else if(alphaList.charAt(k) == 'l')
                return true;
            else if(alphaList.charAt(k) == 'L')
                return true;
            else
                return false;
        }

        else if(equivList.charAt(k) == '6'){
            if(alphaList.charAt(k) == 'M')
                return true;
            else if(alphaList.charAt(k) == 'm')
                return true;
            else if(alphaList.charAt(k) == 'N')
                return true;
            else if(alphaList.charAt(k) == 'n')
                return true;
            else if(alphaList.charAt(k) == 'o')
                return true;
            else if(alphaList.charAt(k) == 'O')
                return true;
            else
                return false;
        }

        else if(equivList.charAt(k) == '7'){
            if(alphaList.charAt(k) == 'p')
                return true;
            else if(alphaList.charAt(k) == 'P')
                return true;
            else if(alphaList.charAt(k) == 'q')
                return true;
            else if(alphaList.charAt(k) == 'Q')
                return true;
            else if(alphaList.charAt(k) == 'r')
                return true;
            else if(alphaList.charAt(k) == 'R')
                return true;
            else if(alphaList.charAt(k) == 's')
                return true;
            else if(alphaList.charAt(k) == 'S')
                return true;
            else
                return false;
        }

        else if(equivList.charAt(k) == '8'){
            if(alphaList.charAt(k) == 't')
                return true;
            else if(alphaList.charAt(k) == 'T')
                return true;
            else if(alphaList.charAt(k) == 'u')
                return true;
            else if(alphaList.charAt(k) == 'U')
                return true;
            else if(alphaList.charAt(k) == 'v')
                return true;
            else if(alphaList.charAt(k) == 'V')
                return true;
            else
                return false;
        }

        else if(equivList.charAt(k) == '9'){
            if(alphaList.charAt(k) == 'w')
                return true;
            else if(alphaList.charAt(k) == 'W')
                return true;
            else if(alphaList.charAt(k) == 'x')
                return true;
            else if(alphaList.charAt(k) == 'X')
                return true;
            else if(alphaList.charAt(k) == 'y')
                return true;
            else if(alphaList.charAt(k) == 'Y')
                return true;
            else if(alphaList.charAt(k) == 'z')
                return true;
            else if(alphaList.charAt(k) == 'Z')
                return true;
            else
                return false;
        }
        else
        return false;
    }
    }


