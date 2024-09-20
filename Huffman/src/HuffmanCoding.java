import java.util.*;

public class HuffmanCoding {

    public static Node createTree(List<Node> nodeList){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Node n : nodeList){
            queue.add(n);
        }
        while (queue.size() > 1){
            //normalizacja poprzez wstawienie pierwszego elementu(najmnejszego) w lewe dziecko
            Node left = queue.poll();
            Node right = queue.poll();
            int sum = left.getValue() + right.getValue();
            Node node = new Node('&', sum);
            node.setLeftChild(left);
            node.setRightChild(right);
            queue.add(node);
        }

        return queue.poll();
    }



    public static void generateCodes(Node root, int[] arr, int top, List<String> list) {

        if (root.getLeftChild() != null) {
            arr[top] = 0;
            generateCodes(root.getLeftChild(), arr, top + 1, list);
        }
        if (root.getRightChild() != null) {
            arr[top] = 1;
            generateCodes(root.getRightChild(), arr, top + 1, list);
        }

        if (root.getLeftChild()== null && root.getRightChild() == null) {
            StringBuilder arrStr = new StringBuilder();
            arrStr.append(root.getLetter()).append(" ");
            for (int i = 0; i < top; i++) {
                arrStr.append(arr[i]);
            }
            list.add(arrStr.toString());
        }
    }


    public static List<String> encode(List<Node> nodeList, int size){
        Node root = createTree(nodeList);

        int[] arr = new int[size];
        List<String> list = new ArrayList<>();
        HuffmanCoding.generateCodes(root,arr,0, list);
        return list;
    }



}
