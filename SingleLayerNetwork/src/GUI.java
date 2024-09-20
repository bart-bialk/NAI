import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JFrame {


    public GUI(List<Perceptron> perceptronList){
        this.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        this.getContentPane().add(textArea,BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        this.getContentPane().add(buttonPanel, BorderLayout.NORTH);

        JLabel responseLabel = new JLabel("Pass text");
        responseLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        JButton checkButton = new JButton("CHECK");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea.getText().isEmpty()){
                    DataRecord record = new DataRecord(Input.getAttributesList(textArea.getText()));
                    List<Double> normalizedAttributes = record.normalizeAttributes();
                    double maxValue = -1;
                    String predictedLanguage = "";

                    for (Perceptron perceptron : perceptronList){
                        perceptron.normalizeWeights();
                        double computed = perceptron.compute(normalizedAttributes);
                        System.out.println(computed + " " + perceptron.getPerLanguage());

                        if(computed > maxValue){
                            maxValue = computed;
                            predictedLanguage = perceptron.getPerLanguage();
                        }
                    }

                    responseLabel.setText("Predicted Language is: " + predictedLanguage);
                }
            }
        });

        JButton clearButton = new JButton("CLEAR TEXT");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                responseLabel.setText("Pass text");
            }
        });

        buttonPanel.add(clearButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(responseLabel);

        this.setTitle("LANGUAGE PREDICTION");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800,600);
        this.setLocation(500,200);
    }
}
