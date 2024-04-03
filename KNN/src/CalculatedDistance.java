public class CalculatedDistance implements Comparable<CalculatedDistance>{


    private final DataRecord trainRecord;
    private final double distance;

    public CalculatedDistance(DataRecord trainRecord,double distance){
        this.trainRecord=trainRecord;
        this.distance=distance;
    }

    public DataRecord getTrainRecord() {
        return trainRecord;
    }


    @Override
    public int compareTo(CalculatedDistance o) {
        return Double.compare(this.distance,o.distance);
    }
}
