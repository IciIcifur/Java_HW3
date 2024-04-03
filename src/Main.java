import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                \nThis program will parse date of birth, surname, name, and patronymic into initials and age.
                \u001B[36mName, patronymic and surname might be specified in Russian.\u001B[0m\s""");
        System.out.println("Enter 'q' to quit.");
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter surname, name, patronymic and date of birth split by spaces:");
            System.out.println("\u001B[36mDate of birth format: dd.mm.yyyy\u001B[0m");

            try {
                String input = in.nextLine();
                if (input.equals("q")) System.exit(0);

                Person newPerson = new Person(input);
                newPerson.print();
            } catch (RuntimeException e) {
                System.out.println("\nAn error occurred while parsing your input:");
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        }
    }
}