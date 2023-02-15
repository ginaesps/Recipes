import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        List<List<String>> table = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            String rowString = scanner.nextLine();
            List<String> row = Arrays.asList(rowString.split(" "));
            table.add(row);
        }

        int rotateDistance = scanner.nextInt();

        Collections.rotate(table, rotateDistance);

        for (List<String> row : table) {
            for (String num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}