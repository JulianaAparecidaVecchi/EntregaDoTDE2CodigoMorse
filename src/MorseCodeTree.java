import java.util.Map;
import java.util.Scanner;

public class MorseCodeTree {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MorseTree tree = new MorseTree();
        tree.buildStandardMapping();

        System.out.println("Árvore Morse construída com mapeamento padrão (A-Z, 0-9).\n");
        boolean running = true;
        while (running) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Imprimir árvore por níveis (console)");
            System.out.println("2 - Decodificar mensagem Morse (letras separadas por espaço, palavras por ' / ')");
            System.out.println("3 - Decodificar uma única letra Morse");
            System.out.println("4 - Mostrar mapa padrão (A-Z,0-9)");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1":
                    tree.printTreeByLevels();
                    System.out.println();
                    break;
                case "2":
                    System.out.println("Digite a mensagem Morse (ex: .... . .-.. .-.. --- / .-- --- .-. .-.. -.. ):");
                    String msg = scanner.nextLine();
                    String decoded = tree.decodeMessage(msg);
                    System.out.println("Decodificado: " + decoded + "\n");
                    break;
                case "3":
                    System.out.println("Digite o código Morse de uma letra (ex: '.-' para A):");
                    String code = scanner.nextLine().trim();
                    System.out.println("Letra: " + tree.decodeLetter(code) + "\n");
                    break;
                case "4":
                    Map<Character,String> map = MorseTree.getStandardMorseMap();
                    for (Map.Entry<Character,String> e : map.entrySet()) {
                        System.out.println(e.getKey() + " -> " + e.getValue());
                    }
                    System.out.println();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida\n");
            }
        }

        System.out.println("Saindo. Obrigado!\n");
    }
}
