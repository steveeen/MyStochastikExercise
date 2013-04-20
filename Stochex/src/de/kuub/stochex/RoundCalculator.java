/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kuub.stochex;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import javax.swing.JTextArea;

/**
 *
 * @author Bonke
 */
public class RoundCalculator {
  
    public static String CalculateWS(int goodVal,int possibleVal, JTextArea logthere){
                LotteryDrawing test=new LotteryDrawing(8,3);
                //There are 3 possible Paths where no German Team play agains an other german team
                //On all other paths 2 Team Play together
                Deque<RoundValues> path1=new ArrayDeque <RoundValues>(3);
                path1.addLast(RoundValues.NoGermanTeam);
                path1.addLast(RoundValues.OneGermanTeam);
                path1.addLast(RoundValues.OneGermanTeam);
                               
                Deque<RoundValues> path2=new ArrayDeque <RoundValues>(3);
                path2.addLast(RoundValues.OneGermanTeam);
                path2.addLast(RoundValues.NoGermanTeam);
                path2.addLast(RoundValues.OneGermanTeam);
                
                Deque<RoundValues> path3=new ArrayDeque <RoundValues>(3);
                path3.addLast(RoundValues.OneGermanTeam);
                path3.addLast(RoundValues.OneGermanTeam);
                
                
                // test.setSubRoundFor0Positives(new LotteryDrawing(6,4));
                String logtext="";
                logtext+=test.ToString();
                logtext+=LotteryDrawing.GetFloatAsPercent(test.getProbalilityFor0Positives())+"\r\n";
                logtext+=LotteryDrawing.GetFloatAsPercent(test.getProbalilityFor1Positives())+"\r\n";
                logtext+=LotteryDrawing.GetFloatAsPercent(test.getProbalilityFor2Positives())+"\r\n";
                float pathsum=0f;
                float pathvalue=0f;
                pathvalue=test.calculatePath(path1);
                pathsum+=pathvalue;
                logtext+=printPathCalculation(pathvalue);
                logtext+="Pathsum: "+LotteryDrawing.GetFloatAsPercent(pathsum)+"\r\n"; 
                pathvalue=test.calculatePath(path2);
                pathsum+=pathvalue;
                logtext+=printPathCalculation(pathvalue);
                logtext+="Pathsum: "+LotteryDrawing.GetFloatAsPercent(pathsum)+"\r\n"; 
                pathvalue=test.calculatePath(path3);
                pathsum+=pathvalue;
                logtext+=printPathCalculation(pathvalue);
                logtext+="Pathsum: "+LotteryDrawing.GetFloatAsPercent(pathsum)+"\r\n"; 
                
                logthere.setText(logtext);
               return LotteryDrawing.GetFloatAsPercent(pathsum);
    }
    
    private static String printPathCalculation(float value){
         return "Pathval: "+LotteryDrawing.GetFloatAsPercent(value)+"\r\n"; 
    }
    
}
