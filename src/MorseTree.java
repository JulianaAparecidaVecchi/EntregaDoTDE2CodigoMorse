import java.util.*;
/**
 * Representa uma árvore binária de código Morse.
 * <p>
 * Cada nó da árvore representa uma letra ou número codificado em Morse.
 * O filho esquerdo de um nó representa o ponto '.' e o filho direito representa o traço '-'.
 * </p>
 */
class MorseTree {

    /** Raiz da árvore Morse, normalmente vazia. */
    private MorseNode root;

    /**
     * Construtor da árvore Morse.
     * Inicializa a raiz como um nó vazio.
     */
    public MorseTree() {
        root = new MorseNode((char) 0);
    }

    /**
     * Insere uma letra na árvore de acordo com o código Morse.
     *
     * @param morseCode Código Morse (ex: ".-" para 'A')
     * @param letter Letra ou número a ser inserido
     */
    public void insertByMorse(String morseCode, char letter) {
        MorseNode current = root;
        for (int i = 0; i < morseCode.length(); i++) {
            char c = morseCode.charAt(i);
            if (c == '.') {
                if (current.left == null) current.left = new MorseNode((char) 0);
                current = current.left;
            } else if (c == '-') {
                if (current.right == null) current.right = new MorseNode((char) 0);
                current = current.right;
            }
        }
        current.value = letter;
    }
    /**
     * Decodifica um único código Morse em uma letra.
     *
     * @param morseCode Código Morse (ex: ".-" para 'A')
     * @return A letra correspondente ou '?' se inválido
     */
    public char decodeLetter(String morseCode) {
        MorseNode current = root;
        for (int i = 0; i < morseCode.length(); i++) {
            char c = morseCode.charAt(i);
            if (c == '.') {
                if (current.left == null) return '?';
                current = current.left;
            } else if (c == '-') {
                if (current.right == null) return '?';
                current = current.right;
            }
        }
        return current.value == 0 ? '?' : current.value;
    }

    /**
     * Decodifica uma mensagem Morse completa.
     *
     * @param morseMessage Mensagem Morse com letras separadas por espaço e palavras por ' / '
     * @return Texto decodificado
     */
    public String decodeMessage(String morseMessage) {
        StringBuilder decoded = new StringBuilder();
        String[] words = morseMessage.trim().split(" / ");
        for (int wi = 0; wi < words.length; wi++) {
            String word = words[wi];
            String[] letters = word.trim().split("\\s+");
            for (String letterCode : letters) {
                if (letterCode.length() == 0) continue;
                decoded.append(decodeLetter(letterCode));
            }
            if (wi < words.length - 1) decoded.append(' ');
        }
        return decoded.toString();
    }

    /**
     * Constroi a árvore utilizando o mapeamento padrão de Morse (A-Z, 0-9).
     */
    public void buildStandardMapping() {
        Map<Character, String> map = getStandardMorseMap();
        for (Map.Entry<Character, String> e : map.entrySet()) {
            insertByMorse(e.getValue(), e.getKey());
        }
    }

    /**
     * Retorna o mapa padrão de código Morse para letras e números.
     *
     * @return Mapa de caracteres para código Morse
     */
    public static Map<Character, String> getStandardMorseMap() {
        Map<Character, String> map = new LinkedHashMap<>();
        map.put('A', ".-");    map.put('B', "-..."); map.put('C', "-.-."); map.put('D', "-..");
        map.put('E', ".");     map.put('F', "..-."); map.put('G', "--.");  map.put('H', "....");
        map.put('I', "..");    map.put('J', ".---"); map.put('K', "-.-");  map.put('L', ".-..");
        map.put('M', "--");    map.put('N', "-.");   map.put('O', "---");  map.put('P', ".--.");
        map.put('Q', "--.-");  map.put('R', ".-.");  map.put('S', "...");  map.put('T', "-");
        map.put('U', "..-");   map.put('V', "...-"); map.put('W', ".--");  map.put('X', "-..-");
        map.put('Y', "-.--");  map.put('Z', "--..");
        map.put('0', "-----"); map.put('1', ".----"); map.put('2', "..---"); map.put('3', "...--");
        map.put('4', "....-"); map.put('5', "....."); map.put('6', "-...."); map.put('7', "--...");
        map.put('8', "---.."); map.put('9', "----.");
        return map;
    }

    /**
     * Imprime a árvore Morse por níveis no console.
     */
    public void printTreeByLevels() {
        Queue<MorseNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            System.out.print("Level " + level + ": ");
            for (int i = 0; i < size; i++) {
                MorseNode n = q.poll();
                if (n == null) {
                    System.out.print("[null] ");
                    q.add(null);
                    q.add(null);
                } else {
                    char display = n.value == 0 ? '.' : n.value;
                    System.out.print("[" + display + "] ");
                    q.add(n.left);
                    q.add(n.right);
                }
            }
            System.out.println();
            level++;
            boolean allNull = true;
            for (MorseNode node : q)
                if (node != null) {
                    allNull = false;
                    break;
                }
            if (allNull) break;
        }
    }

    /**
     * Retorna a raiz da árvore Morse.
     *
     * @return nó raiz
     */
    public MorseNode getRoot() {
        return root;
    }

    /**
     * Gera código Graphviz (DOT) para visualização da árvore.
     * O filho esquerdo será roxinho e o direito rosinha.
     *
     * @return String contendo o código DOT
     */
    public String toGraphvizDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph MorseTree {\n");
        sb.append("    node [shape=circle, style=filled, fontcolor=black];\n");
        sb.append("    N0 [label=\"\", fillcolor=white];\n");

        if (root.left != null) {
            String childId = "N" + root.left.hashCode();
            char label = root.left.value == 0 ? ' ' : root.left.value;
            sb.append("    ").append(childId)
                    .append(" [label=\"").append(label)
                    .append("\", fillcolor=\"#C084FC\"];\n");
            sb.append("    N0 -> ").append(childId).append(" [label=\".\"];\n");
            buildGraphvizDot(root.left, childId, sb, "#C084FC");
        }

        if (root.right != null) {
            String childId = "N" + root.right.hashCode();
            char label = root.right.value == 0 ? ' ' : root.right.value;
            sb.append("    ").append(childId)
                    .append(" [label=\"").append(label)
                    .append("\", fillcolor=\"#F9A8D4\"];\n");
            sb.append("    N0 -> ").append(childId).append(" [label=\"-\"];\n");
            buildGraphvizDot(root.right, childId, sb, "#F9A8D4");
        }

        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Método auxiliar para construir recursivamente o Graphviz DOT.
     *
     * @param node nó atual
     * @param parentId ID do nó pai
     * @param sb StringBuilder que acumula o código DOT
     * @param color Cor dos nós descendentes (herdada do ramo)
     */
    private void buildGraphvizDot(MorseNode node, String parentId, StringBuilder sb, String color) {
        if (node == null) return;

        String nodeId = "N" + node.hashCode();

        if (node.left != null) {
            String childId = "N" + node.left.hashCode();
            char label = node.left.value == 0 ? ' ' : node.left.value;
            sb.append("    ").append(childId)
                    .append(" [label=\"").append(label)
                    .append("\", fillcolor=\"").append(color).append("\"];\n");
            sb.append("    ").append(nodeId).append(" -> ").append(childId)
                    .append(" [label=\".\"];\n");
            buildGraphvizDot(node.left, childId, sb, color);
        }

        if (node.right != null) {
            String childId = "N" + node.right.hashCode();
            char label = node.right.value == 0 ? ' ' : node.right.value;
            sb.append("    ").append(childId)
                    .append(" [label=\"").append(label)
                    .append("\", fillcolor=\"").append(color).append("\"];\n");
            sb.append("    ").append(nodeId).append(" -> ").append(childId)
                    .append(" [label=\"-\"];\n");
            buildGraphvizDot(node.right, childId, sb, color);
        }
    }

}