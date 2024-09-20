import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        String line;
        int flag = 0,count=0;
        List<Node> nodeList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("testhuffman.txt"));

            while ((line = reader.readLine()) != null){
                if (flag == 0){
                    count = Integer.parseInt(line);
                    flag++;
                }
                else {
                    nodeList.add(new Node(line.split(" ")[0].charAt(0), Integer.parseInt(line.split(" ")[1])));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        List<String> list = HuffmanCoding.encode(nodeList,count);
        list.sort((x, y) -> x.split(" ")[0].charAt(0)-y.split(" ")[0].charAt(0));
        for (String s : list){
            System.out.println(s);
        }

    }

}
