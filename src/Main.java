import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose game language:");
        System.out.println("1 - English");
        System.out.println("2 - Русский");
        int languageChoice = scanner.nextInt();
        String languageCode = languageChoice == 1 ? "en" : "ru";
        LanguageManager.loadLanguage(languageCode);

        System.out.println(LanguageManager.getResource("modeSelection"));
        System.out.println("1 - " + LanguageManager.getResource("singlePlayer"));
        System.out.println("2 - " + LanguageManager.getResource("multiplayer"));
        int gameMode = scanner.nextInt();

        if (gameMode == 1) {
            TicTacToe game = new TicTacToe();
            game.playWithAI();
        } else if (gameMode == 2) {
            TicTacToe game = new TicTacToe();
            game.playWithFriend();
        }
        scanner.close();
    }
}