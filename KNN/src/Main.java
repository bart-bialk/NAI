import java.io.IOException;
import java.util.List;


public class Main {


    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(args[0]);

        List<DataRecord> trainList = Input.getDataList(args[1]);
        List<DataRecord> testList = Input.getDataList(args[2]);

        Knn.algorithm(trainList,testList,k,true);

        Input.terminal(k,trainList);

    }
}
