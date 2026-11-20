public class Task2 {

    public static int countNumbersInSentence(String sentence) {
        if (sentence == null) {
            throw new NullPointerException("Речення не може бути null.");
        }

        String trimmed = sentence.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Речення не може бути порожнім.");
        }

        String[] tokens = trimmed.split("\\s+");
        int numberCount = 0;

        for (String token : tokens) {
            if (!token.matches("[a-zA-Z0-9]+")) {
                throw new IllegalArgumentException("Речення містить недопустимий токен: \"" + token + "\"");
            }

            if (token.matches("[0-9]+")) {
                numberCount++;
            }
        }

        return numberCount;
    }

    public static void main(String[] args) {
        System.out.println("=== Тестування countNumbersInSentence (дозволені аргументи) ===");
        String example1 = "The user with the nickname koala757677 this month wrote 3 times more comments than the user with the nickname croco181dile920 4 months ago";
        String example2 = "Java 21 was released in 2023 and has 0 bugs in test42";
        String example3 = "Only words without digits here";

        System.out.println("Приклад 1 (очікується 2): " + countNumbersInSentence(example1));
        System.out.println("Приклад 2 (очікується 3): " + countNumbersInSentence(example2));
        System.out.println("Приклад 3 (очікується 0): " + countNumbersInSentence(example3));

        System.out.println("\n=== Тестування countNumbersInSentence (заборонені аргументи) ===");
        String[] invalidSentences = {
                null,
                "",
                "   ",
                "Invalid symbol @ in 2026!"
        };

        for (String badInput : invalidSentences) {
            try {
                System.out.print("Спроба передати \"" + badInput + "\": ");
                int count = countNumbersInSentence(badInput);
                System.out.println(count);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println("Викинуто виключення -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}