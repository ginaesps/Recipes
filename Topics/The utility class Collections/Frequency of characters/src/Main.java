import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.nextLine();
        char letter = scanner.nextLine().charAt(0);

        contarCaracteres(sequence, letter);
    }

    public static void contarCaracteres(String cadena, char caracter) {
        int posicion, contador = 0;
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) {
            contador++;
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        System.out.println(contador);
    }
}
