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

        for (int i=0; i< 1000; i++){
            Collections.shuffle(trainList);
            for (DataRecord dr : trainList){
                for (Perceptron perceptron : perceptronList){
                    perceptron.learn(dr,0.8);
                }
            }

        }

        SwingUtilities.invokeLater(() -> new GUI(perceptronList));
    }

}
