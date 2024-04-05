import java.util.List;

public class DataRecord {

    private final List<Double> attributes;
    private final String labelName;


    public DataRecord(List<Double> attributes, String labelName){
        this.attributes=attributes;
        this.labelName=labelName;
    }

    public DataRecord(List<Double> attributes){
        this.attributes=attributes;
        this.labelName="";
    }

    public List<Double> getAttributes() {
        return attributes;
    }

    public String getLabelName() {
        return labelName;
    }

    public String toString(){
        return attributes + " " + labelName;
    }



}
