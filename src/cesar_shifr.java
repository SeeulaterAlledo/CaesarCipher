//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Scanner;

class CaesarCipher {
    private static final String ALPHABET = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    CaesarCipher() {
    }

    public static String encrypt(String text, int shift) {
        return processText(text, shift);
    }

    public static String decrypt(String text, int shift) {
        return processText(text, -shift);
    }

    private static String processText(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for(char c : text.toUpperCase().toCharArray()) {
            int index = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".indexOf(c);
            if (index != -1) {
                int newIndex = (index + shift + "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".length()) % "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".length();
                result.append("АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".charAt(newIndex));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void processFile(String inputFile, String outputFile, int shift, boolean decrypt) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFile)));
            String processedText = decrypt ? decrypt(content, shift) : encrypt(content, shift);
            Files.write(Paths.get(outputFile), processedText.getBytes(), new OpenOption[0]);
            System.out.println("Файл успешно обработан: " + outputFile);
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }

    }

    public static void bruteForce(String inputFile) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFile)));

            for(int i = 1; i < "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".length(); ++i) {
                System.out.println("Ключ: " + i + " - " + decrypt(content, i));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите режим работы: 1 - шифрование, 2 - расшифровка, 3 - brute force");
        int mode = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите путь к входному файлу:");
        String inputFile = scanner.nextLine();
        if (mode == 3) {
            bruteForce(inputFile);
        } else {
            System.out.println("Введите путь к выходному файлу:");
            String outputFile = scanner.nextLine();
            System.out.println("Введите ключ (целое число):");
            int key = scanner.nextInt();
            processFile(inputFile, outputFile, key, mode == 2);
        }

    }
}
