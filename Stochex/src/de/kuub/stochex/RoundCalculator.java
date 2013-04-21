/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kuub.stochex;

import java.util.ArrayDeque;
import java.util.Deque;
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
                ArrayDeque<Deque<RoundValues>> pathsForNoAllGermanGame=new ArrayDeque<Deque<RoundValues>>();
                pathsForNoAllGermanGame.add(CreatePath(RoundValues.NoGermanTeam, RoundValues.OneGermanTeam,RoundValues.OneGermanTeam));
                pathsForNoAllGermanGame.add(CreatePath(RoundValues.OneGermanTeam, RoundValues.NoGermanTeam, RoundValues.OneGermanTeam));
                pathsForNoAllGermanGame.add(CreatePath(RoundValues.OneGermanTeam, RoundValues.OneGermanTeam));
                
                
                
                ArrayDeque<Deque<RoundValues>> pathsForOneAllGermanGame=new ArrayDeque<Deque<RoundValues>>();
                pathsForOneAllGermanGame.add(CreatePath(RoundValues.TwoGermanTeams));
                pathsForOneAllGermanGame.add(CreatePath(RoundValues.NoGermanTeam, RoundValues.NoGermanTeam));
                pathsForOneAllGermanGame.add(CreatePath(RoundValues.NoGermanTeam, RoundValues.OneGermanTeam, RoundValues.NoGermanTeam));
                pathsForOneAllGermanGame.add(CreatePath(RoundValues.NoGermanTeam, RoundValues.OneGermanTeam, RoundValues.TwoGermanTeams));
                pathsForOneAllGermanGame.add(CreatePath(RoundValues.NoGermanTeam, RoundValues.TwoGermanTeams));
                pathsForOneAllGermanGame.add(CreatePath(RoundValues.OneGermanTeam, RoundValues.NoGermanTeam, RoundValues.NoGermanTeam));
                pathsForOneAllGermanGame.add(CreatePath(RoundValues.OneGermanTeam, RoundValues.NoGermanTeam, RoundValues.TwoGermanTeams));
                pathsForOneAllGermanGame.add(CreatePath(RoundValues.OneGermanTeam, RoundValues.TwoGermanTeams));
                
                String logtext="Calculate WS for No Game with two german Teams:\r\n";
                float pathsum=0f;
                for(Deque<RoundValues> pa :pathsForNoAllGermanGame){
                   float pathvalue=0f;
                   pathvalue=test.calculatePath(pa);
                   pathsum+=pathvalue;
                   logtext+="Pathval for actual Path : "+LotteryDrawing.GetFloatAsPercent(pathvalue)+"\r\n";
                   logtext+="Actual Pathsum for No Game with two german Teams : "+LotteryDrawing.GetFloatAsPercent(pathsum)+"\r\n"; 
                }
                logtext+="\r\nFinal Result for one Game with two german Teams: "+LotteryDrawing.GetFloatAsPercent(pathsum)+"\r\n";
                
                logtext+="\r\n\r\nCalculate WS for one Game with two german Teams:\r\n";
               float pathsum2=0f;
                
                for(Deque<RoundValues> pa :pathsForOneAllGermanGame){
                   float pathvalue=0f;
                   pathvalue=test.calculatePath(pa);
                   pathsum2+=pathvalue;
                   logtext+="Pathval for actual Path : "+LotteryDrawing.GetFloatAsPercent(pathvalue)+"\r\n";
                   logtext+="Actual Pathsum for one Game with two german Teams: "+LotteryDrawing.GetFloatAsPercent(pathsum2)+"\r\n"; 
                }
                logtext+="\r\nFinal Result for no Game with two german Teams: "+LotteryDrawing.GetFloatAsPercent(pathsum2)+"\r\n";
                
                logtext+="\r\nControlsum(addition sum1 and sum2 should be 100%):\r\n "+LotteryDrawing.GetFloatAsPercent(pathsum+pathsum2)+"\r\n";
                logthere.setText(logtext);

               
               return LotteryDrawing.GetFloatAsPercent(pathsum2);
    }
    
   
    
    private static Deque<RoundValues> CreatePath(RoundValues ... args){
        Deque<RoundValues> path=new ArrayDeque <RoundValues>(args.length);
        for(RoundValues val : args){
            path.addLast(val);
        }
        return path;
    }
    
}
