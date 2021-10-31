package battleship;

public class BattleshipTable {
    public char[][] battleShipTable = new char[10][10];


    // 1.In this method we create Battleship table
    public BattleshipTable() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                battleShipTable[i][j] = '~';
            }
        }

    }

    //2. In this method we print battleship table
    public void printBattleshipTable(char[][] battleshipTable) {
        int columnNumber = 1;
        char lineNumber = 'A';
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");

                } else if (i == 0 && j > 0) {
                    System.out.print(columnNumber + " ");
                    columnNumber = columnNumber + 1;
                } else if (i > 0 && j == 0) {
                    System.out.print(lineNumber + " ");
                    lineNumber++;
                } else {
                    System.out.print(battleshipTable[i - 1][j - 1] + " ");

                }


            }
            System.out.println();
        }

    }

    //3 Getter for Battleship table
    public char[][] getBattleshipTable() {


        return this.battleShipTable;


    }

    // This method adds ship on table and with help of tryClass.checkPosition() check if a cell nearby is fill with 0
    public void addshipOnTheBattleshipTable(int starterRow, int finishRow, int starterColumn, int finishColumn) throws wrongPosition {

        Main.checkPosition(starterRow, finishRow, starterColumn, finishColumn, this.battleShipTable);
        if (starterRow != finishRow) {

            for (int i = starterRow; i <= finishRow; i++) {
                this.battleShipTable[i][starterColumn] = 'O';
            }
        } else if (starterColumn != finishColumn) {
            for (int i = starterColumn; i <= finishColumn; i++) {
                this.battleShipTable[starterRow][i] = 'O';
            }
        }
    }


}
