package javaBasics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class tets {


   /* Given a list of integers, find out all the numbers starting with 1 using Stream functions?
    Input: List<Integer> list = [10,15,8,49,25,98,32];
    output: 10,15*/


    //min leng = 6 char
    //max len = 15 char
   // 1 uppar cse , 1 lower case , 1 specail char , 1 digit

    public static  void main (String [] args )
    {

        List<String> list = Arrays.asList("10","15","8","49","25","98","32" , "1000");


        List<String> ans = list.stream()
                .filter( n -> n.startsWith("1"))
                .toList();

        System.out.println(ans);

        System.out.println("password " + passwordgenerator());
    }

    private static String passwordgenerator() {
         int min = 6;
         int max = 15;
        Random rand = new Random();

        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%:'";

        int randomNumber = rand.nextInt(max - min + 1) + min;

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < randomNumber ; i++)
        {
            int index = rand.nextInt(characterSet.length());
            sb.append(characterSet.charAt(index));
        }

        // 1 uppar cse , 1 lower case , 1 specail char , 1 digit



        if ( sb.toString().chars()
                .anyMatch(Character :: isUpperCase))
        {
            if (sb.toString().chars().anyMatch(Character :: isDigit)){
                if (sb.toString().chars().anyMatch(Character :: isSpaceChar))
                {
                    if(sb.toString().chars().anyMatch(Character :: isLowerCase))
                    {
                        return sb.toString();
                    } else {
                        passwordgenerator();
                    }

                }
                else {
                    passwordgenerator();
                }
            }
            else {
                passwordgenerator();
            }
        }
        else {
            passwordgenerator();
        }



         return "";

    }


}
