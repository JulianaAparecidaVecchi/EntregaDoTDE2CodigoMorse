import java.util.*;
class MorseTree {
    private MorseNode root;

    public MorseTree() {
        root = new MorseNode((char)0);
    }

    public void insertByMorse(String morseCode, char letter) {
        MorseNode current = root;
        for (int i = 0; i < morseCode.length(); i++) {
            char c = morseCode.charAt(i);
            if (c == '.') {
                if (current.left == null) current.left = new MorseNode((char)0);
                current = current.left;
            } else if (c == '-') {
                if (current.right == null) current.right = new MorseNode((char)0);
                current = current.right;
            } else {
                // Ignora caracteres inválidos
            }
        }
        current.value = letter;
    }

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
            } else {
                // Espaços ou barras não esperados aqui
            }
        }
        return current.value == 0 ? '?' : current.value;
    }

    public String decodeMessage(String morseMessage) {
        StringBuilder decoded = new StringBuilder();
        String[] words = morseMessage.trim().split(" / ");
        for (int wi = 0; wi < words.length; wi++) {
            String word = words[wi];
            String[] letters = word.trim().split("\\s+");
            for (String letterCode : letters) {
                if (letterCode.length() == 0) continue;
                char decodedChar = decodeLetter(letterCode);
                decoded.append(decodedChar);
            }
            if (wi < words.length - 1) decoded.append(' ');
        }
        return decoded.toString();
    }

    public void buildStandardMapping() {
        Map<Character,String> map = getStandardMorseMap();
        for (Map.Entry<Character,String> e : map.entrySet()) {
            insertByMorse(e.getValue(), e.getKey());
        }
    }

    public static Map<Character,String> getStandardMorseMap() {
        Map<Character,String> map = new LinkedHashMap<>();
        map.put('A', ".-");
        map.put('B', "-...");
        map.put('C', "-.-.");
        map.put('D', "-..");
        map.put('E', ".");
        map.put('F', "..-.");
        map.put('G', "--.");
        map.put('H', "....");
        map.put('I', "..");
        map.put('J', ".---");
        map.put('K', "-.-");
        map.put('L', ".-..");
        map.put('M', "--");
        map.put('N', "-.");
        map.put('O', "---");
        map.put('P', ".--.");
        map.put('Q', "--.-");
        map.put('R', ".-.");
        map.put('S', "...");
        map.put('T', "-");
        map.put('U', "..-");
        map.put('V', "...-");
        map.put('W', ".--");
        map.put('X', "-..-");
        map.put('Y', "-.--");
        map.put('Z', "--..");
        map.put('0', "-----");
        map.put('1', ".----");
        map.put('2', "..---");
        map.put('3', "...--");
        map.put('4', "....-");
        map.put('5', ".....");
        map.put('6', "-....");
        map.put('7', "--...");
        map.put('8', "---..");
        map.put('9', "----.");
        return map;
    }

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
                    q.add(null); q.add(null);
                } else {
                    char display = n.value == 0 ? '.' : n.value;
                    System.out.print("[" + display + "] ");
                    // enqueue children even se null para preservar forma
                    q.add(n.left);
                    q.add(n.right);
                }
            }
            System.out.println();
            level++;
            boolean allNull = true;
            for (MorseNode node : q) if (node != null) { allNull = false; break; }
            if (allNull) break;
        }
    }

    public MorseNode getRoot() {
        return root;
    }
}
