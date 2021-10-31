package battleship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    int id;
    int attackCount=1;
     BattleshipTable battleshipTable;
     BattleshipTable battleshipAttackTable;
    List<Ships> playerShips= new ArrayList<Ships>();
    public Player(int id ){
        this.id=id;
       battleshipTable= new BattleshipTable();
       battleshipAttackTable = new BattleshipTable();
    }

    public void playerAddShip(int startRow, int finishRow, int startColumn, int finishColumn){
        playerShips.add(new Ships(startRow,finishRow,startColumn,finishColumn));
    }

    public List<Ships> getPlayerShips() {
        return playerShips;
    }

    public int getId() {
        return id;
    }

    public void addshipOnTheBattleshipTable(int startRow, int finishRow, int startColumn,int finishColumn) throws wrongPosition {
        battleshipTable.addshipOnTheBattleshipTable(startRow,finishRow,startColumn,finishColumn);

    }



    public void printBattleshipTable(){

        battleshipTable.printBattleshipTable(battleshipTable.battleShipTable);
    }

    public void printBattleshipAttackTable(){
        battleshipAttackTable.printBattleshipTable(battleshipAttackTable.battleShipTable);

    }


    public boolean isShipUnderAttack(int row, int column){
        if(battleshipTable.battleShipTable[row][column]=='O')
        {
            return true;

        }
        else
        {

            return false;
        }

    }


    public boolean itWasAttacked(int row, int column){
        if(battleshipAttackTable.battleShipTable[row][column]=='X')
        {
            return true;

        }
        else
        {

            return false;
        }

    }

    public void updateBattleshipTable(int row,int column, char a){

        battleshipTable.battleShipTable[row][column] = a;

    }

    public void updatebattleshipAttackTable(int row,int column,char a){


        battleshipAttackTable.battleShipTable[row][column]=a;
    }




}
