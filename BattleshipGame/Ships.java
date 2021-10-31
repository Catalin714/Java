package battleship;

public class Ships {
    int startRow;
    int finishRow;
    int startColumn;
    int finishColumn;

    public Ships(int startRow,int finishRow,int startColumn,int finishColumn){
        this.startRow=startRow;
        this.finishRow=finishRow;
        this.startColumn=startColumn;
        this.finishColumn=finishColumn;
    }

   public void showShip(){

       System.out.println("Start row is: "+startRow+" and finish row is"+finishRow+"and start column is "+startColumn
        +"and finish column is "+ finishColumn);
   }



}

enum shipsOfTheGame{
    Aircraft_Carrier(5,"Aircraft Carrier"),
    Battleship(4,"Battleship"),
    Submarine(3,"Submarine"),
    Cruiser(3,"Cruiser"),
    Destroyer(2,"Destroyer");

    public final int numberOfCells;
    public final String nameOfShip;

    shipsOfTheGame(int numberOfCells, String nameOfShip){
        this.numberOfCells=numberOfCells;
        this.nameOfShip=nameOfShip;
    }

    public int getNumberOfCells(){
        return  this.numberOfCells;
    }

    public String getNameOfShip()
    {
        return this.nameOfShip;
    }




}