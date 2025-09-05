package questions;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RandomPassowrdGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.?/";

    private static final SecureRandom RAND = new SecureRandom();

    public static void main (String [] args )
    {

        int min = 6 ;
        int max = 15 ;

        String password = generate (min , max);
        System.out.print(password);


    }

    private static String generate(int min, int max) {
     int len = min + RAND.nextInt(max-min + 1);

        List<Character> chars = new ArrayList<>(len);

        chars.add(randomChar(UPPER));
        chars.add(randomChar(LOWER));
        chars.add(randomChar(DIGITS));
        chars.add(randomChar(SPECIAL));

        String all = UPPER + LOWER + DIGITS + SPECIAL ;

        for (int i = chars.size() ; i < len ; i++)
        {
            chars.add(randomChar(all));
        }

        Collections.shuffle(chars , RAND);

        StringBuilder sb = new StringBuilder(len);
        for (char c : chars) sb.append(c);
        return sb.toString();






    }

    private static Character randomChar(String s) {

        return s.charAt(RAND.nextInt(s.length()));
    }
}
