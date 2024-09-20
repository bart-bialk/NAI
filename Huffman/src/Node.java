public class Node implements Comparable<Node> {
    private char letter;
    private int value;
    private Node leftChild;
    private Node rightChild;

    public Node(char letter, int value) {
        this.letter = letter;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public char getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }
    public Node getLeftChild() {return leftChild;}
    public Node getRightChild() {return rightChild;}

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(Node o) {
        //normalizacja (jesli wagi te same porownuje litery)
        if (o.getValue() == this.getValue()) {
            return this.getLetter()-o.getLetter();
        }
        return this.value-o.getValue();
    }

    @Override
    public String toString() {
        return "Node{" +
                "letter=" + letter +
                ", value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
