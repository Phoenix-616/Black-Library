package ru.nsu.fit.g14203.dreamteam.awesomechess.field;

import java.util.Random;

/**
 *
 * @author ekaterina
 */
  public class Demiurg {
      
      
        String name;
      
        public class Joke{
            FieldCoord where;
            JokeType what;
            
            Joke(FieldCoord where, JokeType what){
                this.where = where;
                this.what = what;
            }
        }
        
        public enum JokeType{
            SWITCHLVL, KILLKINDLY 
        }
        
        private final int executionInterval = 5;
      
        private int remainBeforeExecution = executionInterval;
      
        private Random rand = new Random();
        
        public Demiurg(String name){
            this.name = name;
        }
        
        public Joke playJoke(){
            
            FieldCoord target = new FieldCoord(rand.nextInt(8), rand.nextInt(8));
            
            if(remainBeforeExecution == 0){
                remainBeforeExecution = executionInterval;
                return new Joke(target, JokeType.KILLKINDLY);
            }
            
            remainBeforeExecution--;
            return new Joke(target, JokeType.SWITCHLVL);
        }
    
    }
