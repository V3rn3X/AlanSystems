import org.decimal4j.util.DoubleRounder;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    public static File file;

    public static void showMenu() {

        Scanner scanner = new Scanner(System.in);
        String readChoose;
        do {
            System.out.println("Choose option:");
            System.out.print("1. Read file (Current: ");
            String path = file != null ? file.getAbsolutePath() : "Empty";
            System.out.println(path + ")");
            System.out.println("2. Import JSON from file");
            System.out.println("3. Review actual stock");
            System.out.println("0. Exit");
            readChoose = scanner.nextLine();
            switchMenu(readChoose);
        } while (!readChoose.equals("0"));
        System.out.println("exit");
    }

    private static void switchMenu(String readChoose) {

        FileHandling fileHandling = new FileHandling();
        int readChooseParser = tryParse(readChoose);

        switch (readChooseParser) {
            case 1 -> file = fileHandling.readFile();
            case 2 -> {
                if (file == null) {
                    System.out.println("\u001B[31m" + "The file is empty, please select the file first." + "\u001B[0m");
                } else {
                    List<String> jsonString = fileHandling.jsonToStingList(file);
                    fileHandling.jsonStringToProduct(jsonString);
                }
            }
            case 3 -> {
                Map<Integer, Product> productMap = Product.getProductMap();
                productMap.forEach((key, Product) -> System.out.println("Product: " + key + " quantity: " + Product.getQuantity() +
                        ", value: " + Product.getValue() +
                        ", ppl: " + DoubleRounder.round(Product.getValue() / Product.getQuantity(), 5)));
            }
            case 0 ->{}
            default -> {
                System.out.println("\u001B[31m" + " Wrong number! Try again!" + "\u001B[0m");
            }
        }
        System.out.println();
    }

    public static int tryParse(String readChoose) {
        int number;
        try {
            return Integer.parseInt(readChoose);
        } catch (NumberFormatException ex) {
            number = 10;
            ex.printStackTrace();
            return number;
        }
    }
}
