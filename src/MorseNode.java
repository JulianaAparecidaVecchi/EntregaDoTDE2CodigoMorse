class MorseNode {
    char value;
    MorseNode left;  // '.'
    MorseNode right; // '-'

    MorseNode(char value) {
        this.value = value;
    }

    boolean isEmpty() {
        return value == 0;
    }
}