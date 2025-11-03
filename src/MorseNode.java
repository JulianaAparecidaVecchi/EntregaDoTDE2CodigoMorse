/**
 * Representa um nó da árvore Morse.
 * Cada nó pode conter uma letra (ou número) e referências para os nós filhos
 * correspondentes aos sinais '.' (ponto) e '-' (traço).
 */
public class MorseNode {

    /** Valor armazenado no nó. Se for 0, significa que o nó está vazio (ex: raiz). */
    char value;

    /** Filho à esquerda, representando o sinal '.' (ponto) na árvore Morse. */
    MorseNode left;

    /** Filho à direita, representando o sinal '-' (traço) na árvore Morse. */
    MorseNode right;

    /**
     * Construtor que cria um nó Morse com o valor especificado.
     *
     * @param value o caractere a ser armazenado no nó
     */
    MorseNode(char value) {
        this.value = value;
    }

    /**
     * Verifica se o nó está vazio.
     * Um nó vazio é identificado pelo valor zero (char 0), normalmente usado para a raiz.
     *
     * @return true se o nó estiver vazio, false caso contrário
     */
    boolean isEmpty() {
        return value == 0;
    }
}
