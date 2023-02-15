import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstSequence = scanner.nextLine();
        String secondSequence  = scanner.nextLine();

        List<String> firstList = Arrays.asList(firstSequence.split(" "));
        List<String> secondList = Arrays.asList(secondSequence.split(" "));

        int firstIndex = Collections.indexOfSubList(firstList, secondList);
        int lastIndex = Collections.lastIndexOfSubList(firstList, secondList);

        if (firstIndex != -1 && lastIndex != -1) {
            System.out.println(firstIndex + " " + lastIndex);
        } else {
            System.out.println("-1 -1");
        }
    }
}