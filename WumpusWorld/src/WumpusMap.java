

public class WumpusMap {

    public static final int NUM_ROWS = 10;
    public static final int NUM_COLUMNS = 10;
    public static final int NUM_PITS = 10;

    private WumpusSquare[][] grid = new WumpusSquare[10][10];




    private int ladderC;
    private int ladderR;

    public WumpusMap(){

        createMap();

    }



    public void createMap(){

        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                grid[x][y] = new WumpusSquare();
            }
        }


        //places wumpus
        for(int x = 0; x < 1; x++){

            int wur = (int)(Math.random()*10);
            int wuc = (int)(Math.random()*10);

            if(grid[wur][wuc].getLadder()||grid[wur][wuc].getPit()){
                while(grid[wur][wuc].getLadder()||grid[wur][wuc].getPit()){

                    wur = (int)(Math.random()*10);
                    wuc = (int)(Math.random()*10);

                }
            }

            grid[wur][wuc].setWumpus(true);

            if(wur - 1 >= 0){
                grid[wur-1][wuc].setStench(true);
            }
            if(wuc + 1 < grid[0].length){
                grid[wur][wuc+1].setStench(true);
            }
            if(wur + 1 < grid.length){
                grid[wur+1][wuc].setStench(true);
            }
            if(wuc - 1 >= 0){
                grid[wur][wuc-1].setStench(true);
            }
        }

        //places gold
        for(int x = 0; x < 1; x++){

            int gor = (int)(Math.random()*10);
            int goc = (int)(Math.random()*10);

            if(grid[gor][goc].getLadder()||grid[gor][goc].getPit() && !grid[gor][goc].getWumpus()){
                while(grid[gor][goc].getLadder()||grid[gor][goc].getPit() && !grid[gor][goc].getWumpus()){

                    gor = (int)(Math.random()*10);
                    goc = (int)(Math.random()*10);

                }
            }

            grid[gor][goc].setGold(true);
        }



        //places ladder
        for(int x = 0; x < 1; x++){

            int lar = (int)(Math.random()*10);
            int lac = (int)(Math.random()*10);

            if(grid[lar][lac].getWumpus()||grid[lar][lac].getGold()){
                while(grid[lar][lac].getWumpus()||grid[lar][lac].getGold()){

                    lar = (int)(Math.random()*10);
                    lac = (int)(Math.random()*10);

                }
            }

            grid[lar][lac].setLadder(true);
            ladderC = lac;
            ladderR= lar;
        }

// place pits
        for(int x = 0; x < 10; x++){


            int pitr = (int)(Math.random()*10);
            int pitc = (int)(Math.random()*10);

            if(grid[pitr][pitc].getLadder()||grid[pitr][pitc].getWumpus()||grid[pitr][pitc].getGold()||grid[pitr][pitc].getPit()){
                while(grid[pitr][pitc].getLadder()||grid[pitr][pitc].getWumpus()||grid[pitr][pitc].getGold()||grid[pitr][pitc].getPit()){

                    pitr = (int)(Math.random()*10);
                    pitc = (int)(Math.random()*10);


                }
            }

            grid[pitr][pitc].setPit(true);


            if(pitr - 1 >= 0){
                grid[pitr-1][pitc].setBreeze(true);
            }
            if(pitc + 1 < grid[0].length){
                grid[pitr][pitc+1].setBreeze(true);
            }
            if(pitr + 1 < grid.length){
                grid[pitr+1][pitc].setBreeze(true);
            }
            if(pitc - 1 >= 0){
                grid[pitr][pitc-1].setBreeze(true);
            }
        }

    }
    public int getLadderCol(){
        return ladderC;
    }
    public int getLadderRow(){
        return ladderR;
    }

    public WumpusSquare getSquare(int row, int col){

        if((col>=0 && col < 10) && (row < 10 && row>=0)){
            return grid[row][col];
        }
        return null;

    }
    public String toString(){

        String toString = "";

        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){

                toString += grid[x][y].toString();
            }
            toString += "\n";
        }
        return toString;

    }

}
