import java.util.ArrayList;
import java.util.List;

public class DataRecord {

    private final List<Double> attributes;
    private final String language;


    public DataRecord(List<Double> attributes, String labelName){
        this.attributes=attributes;
        this.language=labelName;
    }

    public DataRecord(List<Double> attributes){
        this.attributes=attributes;
        this.language="";
    }

    public List<Double> normalizeAttributes(){
        List<Double> normalizedAttributes = new ArrayList<>();
        double sumAttributes = 0;

        for (double atr : attributes){
            sumAttributes += Math.pow(atr,2);
        }
        double length = Math.sqrt(sumAttributes);

        for (int i=0; i<attributes.size();i++){
            normalizedAttributes.add(attributes.get(i)/length);
        }

        return normalizedAttributes;
    }

    public List<Double> getAttributes() {
        return attributes;
    }

    public String getLanguage() {
        return language;
    }

    public String toString(){
        return attributes + " " + language;
    }



}
