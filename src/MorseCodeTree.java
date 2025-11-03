import java.util.Map;
import java.util.Scanner;

/**
 * Classe principal para construir e visualizar a Árvore de Código Morse.
 * <p>
 * A árvore é construída com o mapeamento padrão (A-Z, 0-9).
 * O usuário pode interagir com a árvore de várias formas:
 * - Visualização textual no console;
 * - Geração de arquivo Graphviz (.dot) para criar imagens;
 * - Decodificação de mensagens ou letras Morse;
 * - Consulta do mapa padrão de Morse.
 * </p>
 */
public class MorseCodeTree {

    /** Scanner usado para leitura de entradas do usuário. */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicializa a árvore Morse e gerencia o menu interativo.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        MorseTree tree = new MorseTree();
        tree.buildStandardMapping();

        // Cabeçalho do programa
        System.out.println("=====================================================");
        System.out.println("        ÁRVORE DE CÓDIGO MORSE (A-Z, 0-9)");
        System.out.println("=====================================================");
        System.out.println("A árvore foi construída com o mapeamento padrão.\n");

        boolean running = true;
        while (running) {
            exibirMenu();
            String opt = scanner.nextLine().trim();

            switch (opt) {
                case "1":
                    visualizarArvoreConsole(tree);
                    break;

                case "2":
                    gerarArquivoGraphviz(tree);
                    break;

                case "3":
                    decodificarMensagem(tree);
                    break;

                case "4":
                    decodificarLetra(tree);
                    break;

                case "5":
                    mostrarMapaPadrao();
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Opção inválida.\n");
            }
        }

        System.out.println("Saindo... Obrigado por usar o visualizador Morse!\n");
    }

    /**
     * Exibe o menu principal do programa.
     */
    private static void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("-----------------------------------------------------");
        System.out.println("1 - Visualizar árvore Morse (console)");
        System.out.println("2 - Visualizar árvore Morse (Graphviz .dot → imagem)");
        System.out.println("3 - Decodificar mensagem Morse (letras separadas por espaço, palavras por ' / ')");
        System.out.println("4 - Decodificar uma única letra Morse");
        System.out.println("5 - Mostrar mapa padrão (A-Z, 0-9)");
        System.out.println("0 - Sair");
        System.out.println("-----------------------------------------------------");
        System.out.print("Opção: ");
    }

    /**
     * Visualiza a árvore Morse no console por níveis.
     *
     * @param tree a árvore Morse construída
     */
    private static void visualizarArvoreConsole(MorseTree tree) {
        System.out.println("\n--- Árvore Morse (visualização textual) ---");
        System.out.println("Cada nível representa uma camada da árvore Morse.");
        System.out.println("'.' indica ramos à esquerda e '-' indica ramos à direita.\n");
        tree.printTreeByLevels();
        System.out.println();
    }

    /**
     * Gera o arquivo Graphviz (.dot) para visualização da árvore Morse.
     * Cria a pasta 'bin' na raiz do projeto se não existir.
     *
     * @param tree a árvore Morse construída
     */
    private static void gerarArquivoGraphviz(MorseTree tree) {
        System.out.println("\n--- Gerando arquivo Graphviz (.dot) ---");
        String dotCode = tree.toGraphvizDot();

        java.io.File dir = new java.io.File("bin");
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Pasta 'bin' criada na raiz do projeto.");
            } else {
                System.err.println("Falha ao criar a pasta 'bin'. Verifique permissões.");
            }
        }

        try (java.io.FileWriter writer = new java.io.FileWriter("bin/morse_tree.dot")) {
            writer.write(dotCode);
            System.out.println("Arquivo 'bin/morse_tree.dot' gerado com sucesso.\n");

            System.out.println("Você pode visualizar a árvore de duas formas:");
            System.out.println("1) Online: abra 'bin/morse_tree.dot' em um visualizador Graphviz Online.");
            System.out.println("2) Local: se tiver o Graphviz instalado, execute:");
            System.out.println("   dot -Tpng bin/morse_tree.dot -o bin/morse_tree.png");
            System.out.println("   Isso gerará a imagem 'morse_tree.png' na pasta 'bin'.\n");

        } catch (java.io.IOException e) {
            System.err.println("Erro ao escrever o arquivo DOT: " + e.getMessage());
        }
    }

    /**
     * Solicita ao usuário uma mensagem Morse e decodifica para texto.
     *
     * @param tree a árvore Morse construída
     */
    private static void decodificarMensagem(MorseTree tree) {
        System.out.println("\nDigite a mensagem Morse (ex: .... . .-.. .-.. --- / .-- --- .-. .-.. -.. ):");
        String msg = scanner.nextLine();
        String decoded = tree.decodeMessage(msg);
        System.out.println("Decodificado: " + decoded + "\n");
    }

    /**
     * Solicita ao usuário um código Morse de uma única letra e decodifica.
     *
     * @param tree a árvore Morse construída
     */
    private static void decodificarLetra(MorseTree tree) {
        System.out.println("\nDigite o código Morse de uma letra (ex: '.-' para A):");
        String code = scanner.nextLine().trim();
        System.out.println("Letra: " + tree.decodeLetter(code) + "\n");
    }

    /**
     * Mostra no console o mapa padrão de Morse (A-Z, 0-9).
     */
    private static void mostrarMapaPadrao() {
        System.out.println("\n--- Mapeamento padrão (A-Z, 0-9) ---");
        Map<Character, String> map = MorseTree.getStandardMorseMap();
        for (Map.Entry<Character, String> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
        System.out.println();
    }
}
