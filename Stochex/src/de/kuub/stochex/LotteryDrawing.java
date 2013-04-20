/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kuub.stochex;

import java.text.MessageFormat;
import java.util.*;

/**
 *
 * @author Bonke
 */
public class LotteryDrawing {

    private float advantageousValue;
    private float possibleValueGood;
    private float possibleValueBad;
    private LotteryDrawing parentRound = null;
    private HashMap<RoundValues, LotteryDrawing> subrounds = new HashMap<>(3);
    private float probalitityTillHere;

    public LotteryDrawing(int advValue, int gdValue) {
        advantageousValue = (float) advValue;
        possibleValueGood = (float) gdValue;
        possibleValueBad = (float) (advValue - gdValue);

        if (getAdvantagousValuesAfterRound() > 1) {
            if (getGoodValueswith0PositivesAfterRound() > 0) {
                this.setSubRoundFor0Positives(new LotteryDrawing(getAdvantagousValuesAfterRound(), getGoodValueswith0PositivesAfterRound()));
            }
            if (getGoodValueswith1PositivesAfterRound() > 0) {
                this.setSubRoundFor1Positives(new LotteryDrawing(getAdvantagousValuesAfterRound(), getGoodValueswith1PositivesAfterRound()));
            }
            if (getGoodValueswith2PositivesAfterRound() > 0) {
                this.setSubRoundFor2Positives(new LotteryDrawing(getAdvantagousValuesAfterRound(), getGoodValueswith2PositivesAfterRound()));
            }
        }
    }
    public float calculatePath(Deque<RoundValues> myPath){
        if(myPath.size()==0){
           return 0;
        }
        
        RoundValues val=myPath.removeFirst();
        System.out.println(val.toString());
        float sum=getPossibilityByRoundValue(val);
        System.out.println("Round "+val.toString()+" posByRnd:"+sum+" \r\n");
        if(myPath.size()>0&& this.subrounds.containsKey(val)){
            sum=sum*subrounds.get(val).calculatePath(myPath);
        };
        System.out.println("sum*:" +sum);
        return sum;
    }
     
    
    public float getPossibilityByRoundValue(RoundValues val){
       float prop=0f;
        switch(val){
            case NoGermanTeam:
                prop= getProbalilityFor0Positives();
                break;
            case OneGermanTeam:
                prop= getProbalilityFor1Positives();
                break;
            case TwoGermanTeams:
                prop= getProbalilityFor2Positives();
                break;
            }
        return prop;
    }
    
    public void setSubRoundFor0Positives(LotteryDrawing subr) {
        subrounds.put(RoundValues.NoGermanTeam, subr);
    }

    public void setSubRoundFor1Positives(LotteryDrawing subr) {
        subrounds.put(RoundValues.OneGermanTeam, subr);
    }

    public void setSubRoundFor2Positives(LotteryDrawing subr) {
        subrounds.put(RoundValues.TwoGermanTeams, subr);
    }

    public LotteryDrawing getSubroundFor0Positives() {
        return subrounds.get(RoundValues.NoGermanTeam);
    }

    public LotteryDrawing getSubroundFor1Positives() {
        return subrounds.get(RoundValues.OneGermanTeam);
    }

    public LotteryDrawing getSubroundFor2Positives() {
        return subrounds.get(RoundValues.TwoGermanTeams);
    }

    public float getProbalilityFor0Positives() {
        return getProbalilityFor0PositivesR1() * getProbalilityFor0PositivesR2();
    }

    public float getProbalilityFor1Positives() {
        return 2 * (getProbalilityFor1PositivesR1() * getProbalilityFor1PositivesR2());
    }

    public float getProbalilityFor2Positives() {
        return getProbalilityFor2PositivesR1() * getProbalilityFor2PositivesR2();
    }

    public int getAdvantagousValuesAfterRound() {
        return (int) advantageousValue - 2;
    }

    public int getGoodValueswith2PositivesAfterRound() {
        return (int) possibleValueGood - 2;
    }

    public int getGoodValueswith1PositivesAfterRound() {
        return (int) possibleValueGood - 1;
    }

    public int getGoodValueswith0PositivesAfterRound() {
        return (int) possibleValueGood;
    }

    public String ToString() {
        return String.format("AdvantagousValue: %s \r\npossibleGoodValue: %s \r\npossibleBadValue: %s\r\n", advantageousValue, possibleValueGood, possibleValueBad);
    }

    private float getProbalilityFor0PositivesR1() {
        return 1 / (advantageousValue / possibleValueBad);
    }

    private float getProbalilityFor0PositivesR2() {
        return 1 / ((advantageousValue - 1) / (possibleValueBad - 1));
    }

    private float getProbalilityFor1PositivesR1() {
        return 1 / (advantageousValue / possibleValueBad);
    }

    private float getProbalilityFor1PositivesR2() {
        return 1 / ((advantageousValue - 1) / (possibleValueGood));
    }

    private float getProbalilityFor2PositivesR1() {
        return 1 / (advantageousValue / possibleValueGood);
    }

    private float getProbalilityFor2PositivesR2() {
        return 1 / ((advantageousValue - 1) / (possibleValueGood - 1));
    }

    public static String GetFloatAsPercent(float Val) {
        return MessageFormat.format("{0,number,#.##%}", Val);
    }
}
