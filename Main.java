import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "textfile.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Оберіть дію:");
            System.out.println("1 - Записати у файл");
            System.out.println("2 - Прочитати вміст файлу");
            System.out.println("3 - Вийти з редактора");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    writeToFile();
                    break;
                case "2":
                    readFromFile();
                    break;
                case "3":
                    System.out.println("Вихід з редактора.");
                    return;
                default:
                    System.out.println("Некоректний вибір, спробуйте ще раз.");
            }
        }
    }

    private static void writeToFile() {
        System.out.println("Оберіть режим запису:");
        System.out.println("1 - Перезаписати файл");
        System.out.println("2 - Додати в кінець файлу");

        String mode = scanner.nextLine();
        boolean append = mode.equals("2");

        System.out.println("Введіть рядок для запису: ");
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            System.out.println("Запис скасовано.");
            return;
        }

        try (FileWriter fw = new FileWriter(FILE_NAME, append);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(input);
            bw.newLine();
            System.out.println("Дані успішно записані у файл.");
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }
    }

    private static void readFromFile() {
        try (FileReader fr = new FileReader(FILE_NAME);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено. Спочатку запишіть дані.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}
