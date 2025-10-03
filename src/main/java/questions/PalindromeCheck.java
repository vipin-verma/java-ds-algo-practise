package questions;


public class PalindromeCheck{

    public static void main (String [] args )
    {
        System.out.println(isPalindrome("aabbaa"));

    }

    private static boolean isPalindrome(String s) {

        int l =0  , r = s.length() -1 ;

        while (l<r){
            while (l < r && !isAlphanum(s.charAt(l))) l++;
            while (l < r && !isAlphanum(s.charAt(r))) r--;
            char a = Character.toLowerCase(s.charAt(l));
            char b = Character.toLowerCase(s.charAt(r));
            if(a != b) return false ;
            l++ ; r--;
        }
        return true ;
    }

    private static boolean isAlphanum (char ch ){
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }




}
