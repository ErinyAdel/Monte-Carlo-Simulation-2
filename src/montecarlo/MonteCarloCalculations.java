package montecarlo;

import java.util.ArrayList;
import java.util.Random;

public class MonteCarloCalculations 
{
    public static double sum ;
    private final Random RANDOM = new Random();
    
    public void calcCumulativeProb(ArrayList<ModelVariable> variables)
    {
        for(int i=0;i<variables.size();i++)
        {
            if(i == 0)
            {
                variables.get(i).setCumulativeProbability(variables.get(i).getProbability());
            }
            else{
                variables.get(i).setCumulativeProbability(variables.get(i-1).getCumulativeProbability()
                        +variables.get(i).getProbability());
            }
        }
    }
    
   public void assignInterval(ArrayList<ModelVariable> variables)
   {
       for(int i=0;i<variables.size();i++)
       {
           if(i==0)
           {
               variables.get(i).setIntervalMin(1);
               variables.get(i).setIntervalMax((int)(variables.get(i).getCumulativeProbability() * 100));
           }else
           {
               variables.get(i).setIntervalMin(variables.get(i-1).getIntervalMax() + 1);
               variables.get(i).setIntervalMax((int)(variables.get(i).getCumulativeProbability() * 100));
           }
       }
   }
   
   private ArrayList<Integer> generateRandomNumList(ModelVariable lastVariable)
   {
       ArrayList<Integer> randomNums = new ArrayList<>();
       for(int i=1;i<=lastVariable.getIntervalMax();i++){
           randomNums.add(RANDOM.nextInt(lastVariable.getIntervalMax()+1));           
       }
       return randomNums;
   }
   
   private int generateRandomIndex(int sizeOfList)
   {
       return RANDOM.nextInt(sizeOfList);
   }
   
   private ArrayList<Integer> getRandomNumList(ArrayList<ModelVariable> variables,int numOfSimulations)
   {
       ArrayList<Integer> randomNums = generateRandomNumList(variables.get(variables.size()-1));
       ArrayList<Integer> pickedRandomNumsList = new ArrayList<>();
       for(int i=0;i<numOfSimulations;i++){
           pickedRandomNumsList.add(randomNums.get(generateRandomIndex(randomNums.size())));
       }
       return pickedRandomNumsList;
   } 
   
   private int calcTotalFrequency(ArrayList<ModelVariable> variables)
   {
       int total = 0;
       for(int i=0;i<variables.size();i++) {
           total+=variables.get(i).getFrequency();
           
       }
       return total;
   }
   
   public void calcProbabilityFromFreq(ArrayList<ModelVariable> variables)
   {
       int totalFreq = calcTotalFrequency(variables);
       for(int i=0;i<variables.size();i++)
       {
           variables.get(i).setProbability((double)variables.get(i).getFrequency()/totalFreq);
       }
   }
   
   public double calcExpectedValue(ArrayList<ModelVariable>variables)
   {
       double expectedValue = 0;
       for(ModelVariable var : variables){
           expectedValue+=var.getProbability() * var.getValue();
       }
       return expectedValue;
   }
   
   public ArrayList<ModelVariable> getSimulatedModelVariables(ArrayList<ModelVariable> variables,int numOfSimulations)
   {
       ArrayList<Integer> randomNumsList = getRandomNumList(variables, numOfSimulations);
       ArrayList<ModelVariable> simulatedVariables = new ArrayList<>();
       for(int i=0;i<randomNumsList.size();i++){
           for(int j=0;j<variables.size();j++){
               if(randomNumsList.get(i) >= variables.get(j).getIntervalMin() 
                       && randomNumsList.get(i) <= variables.get(j).getIntervalMax()){
                   variables.get(j).setRandomNum(randomNumsList.get(i));
                   simulatedVariables.add(variables.get(j));
               }
           }
       }     
       return simulatedVariables;
   }
   
   public double calcAvgFromSimulations(ArrayList<ModelVariable> simulatedVariables)
   {
       double avg = 0;
       int total = 0;
       for(int i=0;i<simulatedVariables.size();i++){
           total+=simulatedVariables.get(i).getValue();
       }
       avg = (double)total/simulatedVariables.size();
       return avg;
   }
   
   public int calcSumOfProb(ArrayList<ModelVariable> variables) {
       sum = 0;
       System.out.println("size="+variables.size());
       for(int i=0; i<variables.size(); i++) {
           sum += variables.get(i).getProbability();
           System.out.println("sum = "+sum);
       }
       System.out.println("sum="+sum);
       return sum == 1? 1:0 ;
   }
   
}