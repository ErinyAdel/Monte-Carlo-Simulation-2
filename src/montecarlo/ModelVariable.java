package montecarlo;

public class ModelVariable 
{
    private int value;
    private double probability;
    private double cumulativeProbability;
    private int intervalMin;
    private int intervalMax;
    private int frequency;
    private int randomNum;

    public ModelVariable() {
        this.frequency = 0;
        this.value = 0;
        this.probability = .0;
        this.cumulativeProbability = .0;
        this.intervalMin = 0;
        this.intervalMax = 0;
        this.randomNum = 0;
    }

    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setProbability(double probability) {
            this.probability = probability;
    }

    public void setCumulativeProbability(double cumulativeProbability) {
        this.cumulativeProbability = cumulativeProbability;
    }

    public void setIntervalMin(int intervalMin) {
        this.intervalMin = intervalMin;
    }

    public void setIntervalMax(int intervalMax) {
        this.intervalMax = intervalMax;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getValue() {
        return this.value;
    }

    public double getProbability() {
        return this.probability;
    }

    public double getCumulativeProbability() {
        return this.cumulativeProbability;
    }

    public int getIntervalMin() {
        return this.intervalMin;
    }

    public int getIntervalMax() {
        return this.intervalMax;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public int getRandomNum() {
        return this.randomNum;
    }
}
