public class Task1 {


    public static int octStringToInt(String s) {
        if (s == null) {
            throw new NullPointerException("Вхідний рядок не може бути null.");
        }

        String trimmed = s.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Вхідний рядок не може бути порожнім.");
        }

        int startIndex = 0;
        boolean isNegative = false;
        char firstChar = trimmed.charAt(0);

        if (firstChar == '-') {
            isNegative = true;
            startIndex = 1;
        } else if (firstChar == '+') {
            startIndex = 1;
        }

        if (startIndex == trimmed.length()) {
            throw new IllegalArgumentException("Рядок містить лише знак без цифр: " + s);
        }

        long result = 0;
        char[] chars = trimmed.toCharArray();

        for (int i = startIndex; i < chars.length; i++) {
            char c = chars[i];
            if (c < '0' || c > '7') {
                throw new IllegalArgumentException("Недопустимий символ для вісімкової системи числення: '" + c + "'");
            }
            int digit = c - '0';
            result = result * 8 + digit;

            if (!isNegative && result > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Число перевищує максимальне значення Integer.MAX_VALUE.");
            }
            if (isNegative && -result < Integer.MIN_VALUE) {
                throw new IllegalArgumentException("Число менше за мінімальне значення Integer.MIN_VALUE.");
            }
        }

        return isNegative ? (int) -result : (int) result;
    }

    public static void main(String[] args) {
        System.out.println("=== Тестування octStringToInt (дозволені аргументи) ===");
        String[] validInputs = {"777", "0", "12", "+100", "-777"};
        for (String input : validInputs) {
            System.out.println("octStringToInt(\"" + input + "\") = " + octStringToInt(input));
        }

        System.out.println("\n=== Тестування octStringToInt (заборонені аргументи) ===");
        String[] invalidInputs = {null, "", "   ", "789", "CAFE", "-", "999999999999"};
        for (String input : invalidInputs) {
            try {
                System.out.print("Спроба передати \"" + input + "\": ");
                int res = octStringToInt(input);
                System.out.println(res);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println("Викинуто виключення -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}