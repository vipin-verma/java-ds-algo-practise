package leetcode;

public class AddBinarySolution {
    // Binary add karne ka function
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        AddBinarySolution solution = new AddBinarySolution();

        // Example 1
        String a1 = "11";
        String b1 = "1";
        String answer1 = solution.addBinary(a1, b1);
        System.out.println("a = " + a1 + ", b = " + b1 + " --> sum = " + answer1); // Output: 100

        // Example 2
        String a2 = "1010";
        String b2 = "1011";
        String answer2 = solution.addBinary(a2, b2);
        System.out.println("a = " + a2 + ", b = " + b2 + " --> sum = " + answer2); // Output: 10101
    }
}

