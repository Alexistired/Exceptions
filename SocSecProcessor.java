import java.util.Scanner;

public class SocSecProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            System.out.print("Name? ");
            String name = scanner.nextLine();
            System.out.print("SSN? ");
            String ssn = scanner.nextLine();

            try {
                if (isValid(ssn)) {
                    System.out.println(name + " " + ssn + " is valid");
                }
            } catch (SocSecException e) {
                System.out.println("Invalid " + name + " " + ssn + ", " + e.getMessage());
            }

            System.out.print("Continue? (y/n): ");
            choice = scanner.nextLine().charAt(0);
        } while (Character.toLowerCase(choice) == 'y');
        
        scanner.close();
    }

    public static boolean isValid(String ssn) throws SocSecException {
        if (ssn.length() != 11) {
            throw new SocSecException("wrong number of characters");
        }

        for (int i = 0; i < ssn.length(); i++) {
            char c = ssn.charAt(i);
            if (i == 3 || i == 6) {
                if (c != '-') {
                    throw new SocSecException("dashes at wrong positions");
                }
            } else {
                if (!Character.isDigit(c)) {
                    throw new SocSecException("contains a character that is not a digit");
                }
            }
        }

        return true;
    }
}
