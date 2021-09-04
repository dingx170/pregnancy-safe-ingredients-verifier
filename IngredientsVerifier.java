import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

class IngredientsVerifier {

    public static void main(String[] args) {
        List<String> coreUnsafeChemicals = readUnsafeChemicalsFromFile("CoreUnsafeChemicals.txt");
        List<String> otherUnsafeChemicals = readUnsafeChemicalsFromFile("OtherUnsafeChemicals.txt");
        verifyIngredients(coreUnsafeChemicals, otherUnsafeChemicals);
    }

    private static List readUnsafeChemicalsFromFile(String fileName) {
        List<String> unsafeChemicals = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));

            while (scanner.hasNextLine()){
                unsafeChemicals.add(scanner.nextLine().toLowerCase());
            }
            scanner.close();

        } catch(Exception ex) {
            System.out.println("Invalid file");
        } 

        return unsafeChemicals;
    }

    private static void verifyIngredients(List<String> coreUnsafeChemicals, List<String> otherUnsafeChemicals) {
        String userInput = null;

        try{
            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("\nEnter \"exit\" to quit or copy & paste the ingredients here to verify: ");
                userInput = bufr.readLine().toLowerCase();
                
                for (String chemical : coreUnsafeChemicals) {
                    if (userInput.contains(chemical)) {
                        System.out.println("Contains unsafe chemical (must avoid): " + chemical);
                    }
                }
                for (String chemical : otherUnsafeChemicals) {
                    if (userInput.contains(chemical)) {
                        System.out.println("Contains unsafe chemical (better avoid): " + chemical);
                    }
                }

            } while (!userInput.equals("exit"));

        } catch(Exception ex) {
            System.out.println("Invalid input"); 
        }
    }
}