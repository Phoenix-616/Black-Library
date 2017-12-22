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
        
        public static enum JokeType{
            SWITCHLVL, KILLKINDLY 
        }
        
        private final int ALAHBABAHINTERVAL = 5;
      
        private int remainBeforeAlahBabah = ALAHBABAHINTERVAL;
      
        private Random rand = new Random();
        
        public Demiurg(String name){
            this.name = name;
        }
        
        public Joke playJoke(){
            
            FieldCoord target = new FieldCoord(rand.nextInt(8), rand.nextInt(8));
            
            if(remainBeforeAlahBabah == 0){
                remainBeforeAlahBabah = ALAHBABAHINTERVAL;
                return new Joke(target, JokeType.KILLKINDLY);
            }
            
            remainBeforeAlahBabah--;
            return new Joke(target, JokeType.SWITCHLVL);
        }
    
    }
