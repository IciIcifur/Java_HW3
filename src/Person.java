import static java.lang.Integer.parseInt;

public class Person {
    private final String name;
    private final String patronymic;
    private final String surname;
    private final String birthDate;

    int age;
    String gender = "male";
    String shortenedName;

    Person(String input) {
        String[] parsedInput = input.split(" ");

        if (parsedInput.length != 4) throw new RuntimeException("Some required data is missed!");

        surname = parsedInput[0];
        name = parsedInput[1];
        patronymic = parsedInput[2];
        birthDate = parsedInput[3];

        calculateAge();
        defineGender();
        shortenName();
    }

    public void print() {
        System.out.println("Name: " + shortenedName + ", age: " + age + ", gender: " + gender);
    }

    private void calculateAge() {
        java.time.LocalDate currentDate = java.time.LocalDate.now();

        String[] parts = birthDate.split("\\.");
        if (parts.length != 3) throw new RuntimeException();

        try {
            int year = parseInt(parts[2]);
            int month = parseInt(parts[1]);
            int day = parseInt(parts[0]);

            if (!validateDate(year, month, day)) throw new RuntimeException();

            age = currentDate.getYear() - year;

            if (month > currentDate.getMonthValue()) {
                age -= 1;
            } else if (month == currentDate.getMonthValue() && day > currentDate.getDayOfMonth()) {
                age -= 1;
            }
            if (age > 150) {
                throw new RuntimeException();
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("Incorrect date!");
        }
    }

    private void defineGender() {
        String[] femaleSurnameEndings = {"ева", "ёва", "ова", "ина", "ына", "ая"};
        String[] femalePatronymicEndings = {"овна", "евна", "ична", "инична"};

        int patronymicLength = patronymic.length();
        int surnameLength = surname.length();


        for (String ending : femalePatronymicEndings) {
            if (patronymicLength >= ending.length() && patronymic.substring(patronymicLength - ending.length()).equals(ending)) {
                gender = "female";
                return;
            }
        }

        for (String ending : femaleSurnameEndings) {
            if (surnameLength >= ending.length() && surname.substring(surnameLength - ending.length()).equals(ending)) {
                gender = "female";
                return;
            }
        }
    }

    private void shortenName() {
        shortenedName = name.charAt(0) + ". " + patronymic.charAt(0) + ". " + surname;
    }

    private boolean validateDate(int year, int month, int day) {
        java.time.LocalDate currentDate = java.time.LocalDate.now();

        if (year > currentDate.getYear() || (year == currentDate.getYear() && month > currentDate.getMonthValue()) ||
                (year == currentDate.getYear() && month == currentDate.getMonthValue() && day > currentDate.getDayOfMonth())) {
            return false;
        }

        if (month > 12) return false;

        if (month == 2 && ((year % 4 != 0 && day > 28) || (year % 4 == 0 && day > 29))) return false;

        return day <= (30 + (month + month / 8) % 2);
    }
}
