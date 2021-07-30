


public class WumpusSquare {



    private boolean gold;
    private boolean ladder;
    private boolean pit;
    private boolean breeze;
    private boolean wumpus;
    private boolean deadWumpus;
    private boolean stench;
    private boolean visited;

    public WumpusSquare(){

        gold = false;
        ladder = false;
        pit = false;
        breeze = false;
        wumpus = false;
        deadWumpus = false;
        stench = false;
        visited = false;

    }

    //getters
    public boolean getGold(){
        return gold;
    }
    public boolean getLadder(){
        return ladder;
    }
    public boolean getPit(){
        return pit;
    }

    public boolean getBreeze(){
        return breeze;
    }
    public boolean getWumpus(){
        return wumpus;
    }
    public boolean getDeadWumpus(){
        return deadWumpus;
    }
    public boolean getStench(){
        return stench;
    }
    public boolean getVisited(){
        return visited;
    }

    //setter
    public void setGold(boolean x){
        gold = x;
    }
    public void setLadder(boolean x){
        ladder = x;
    }
    public void setPit(boolean x){
        pit = x;
    }
    public void setBreeze(boolean x){
        breeze = x;
    }
    public void setWumpus(boolean x){
        wumpus = x;
    }
    public void setDeadWumpus(boolean x){
        deadWumpus = x;
    }
    public void setStench(boolean x){
        stench = x;
    }
    public void setVisited(boolean x){
        visited = x;
    }


    public String toString(){

        if(pit)
            return "P";
        if(ladder)
            return "L";
        if(deadWumpus)
            return "D";
        if(wumpus)
            return "w";
        if(gold && wumpus)
            return "W";
        if(gold)
            return "G";
        else return "*";

    }

}
