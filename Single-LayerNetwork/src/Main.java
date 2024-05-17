import javax.swing.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        List<DataRecord> trainList;
        List<String> langueageList;
        List<Perceptron> perceptronList= new ArrayList<>();

        langueageList = Input.getLanguages("data");
        trainList = Input.getTrainList("data");
        for (String lang : langueageList){
            perceptronList.add(new Perceptron(lang, trainList.get(0).getAttributes().size()));
        }

        for (int i=0; i< 10000; i++){
            Collections.shuffle(trainList);
            for (Perceptron perceptron : perceptronList){
                perceptron.learn(trainList,0.1);
            }
        }

        SwingUtilities.invokeLater(() -> new GUI(perceptronList));
    }

}
