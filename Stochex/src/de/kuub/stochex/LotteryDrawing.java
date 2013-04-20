/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kuub.stochex;

import java.util.*;
/**
 *
 * @author Bonke
 */



public class LotteryDrawing {
    private int advantageousValue;
    private int possibleValue;
    private LotteryDrawing parentRound=null;
    private Map<RoundValues,LotteryDrawing> subrounds;
    
    
    public LotteryDrawing(){
    
    }
    
    
    
    private float probabilityFor0Positives;  
    private float probabilityFor1Positives;
    private float probabilityFor2Positives; 

}
