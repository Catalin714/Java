package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


class wrongPosition extends Exception
{
    public wrongPosition(String message)
    {
        super(message);
    }
}

public class Main {



    public static void main(String[] args) throws wrongPosition {

        Main main = new Main();
        main.startGame();


    }


    public void startGame() {



        // Add all ships on table
        Scanner scanner = new Scanner(System.in);
        String coordinatesShip;
        String attackCoordinates;
        boolean nextShip;
        int[] coordinateOfShipInTable;
        List<Ships> ships = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        boolean isFormatForShipCoordinates = true;
        int playerCount = 1;
        while (playerCount <= 2) {
            Player player = new Player(playerCount);
            System.out.println("Player "+player.getId()+", place your ships on the game field");
            player.printBattleshipTable();
            playerCount += 1;

            for (shipsOfTheGame ship : shipsOfTheGame.values()) {
            System.out.println("Enter the coordinates of the " + ship.getNameOfShip() + " (" + ship.getNumberOfCells() + " cells)");
            nextShip = false;
            while (nextShip == false) {
                try {
                    coordinatesShip = scanner.nextLine().trim();
                    // IF FORMAT IS OK GO TO EXTRACT COORDINATES OF SHIP FROM STRING
                    if (isInputFormatOk(coordinatesShip, isFormatForShipCoordinates)) {
                        //First step- Extract coordinates from user input and make sure the input is correct
                        coordinateOfShipInTable = extractShipCoordinates(coordinatesShip, ship);
                        //Second step - Add ship in a list
                        player.playerAddShip(coordinateOfShipInTable[0], coordinateOfShipInTable[1], coordinateOfShipInTable[2], coordinateOfShipInTable[3]);
                        //Third ste-Add the ship on the table and print
                        player.addshipOnTheBattleshipTable(coordinateOfShipInTable[0], coordinateOfShipInTable[1], coordinateOfShipInTable[2], coordinateOfShipInTable[3]);
                        player.printBattleshipTable();
                        nextShip = true;
                    }
                } catch (wrongPosition e) {

                }

            }
        }
        players.add(player);
                System.out.println("Press Enter and pass the move to another player");
                scanner.nextLine();

    }


        BattleshipTable battleshipTable1 = new BattleshipTable();
        isFormatForShipCoordinates = false;
        boolean isGameFinish = false;
        int[] attackCoordonates = new int[2];
        int count=0;


        
        int id=0;

        for(Ships ship:players.get(1).playerShips)
            ship.showShip();

        while (isGameFinish==false){
            Player playerPlay = players.get(id);
            playerPlay.printBattleshipAttackTable();
            System.out.println("---------------------");
            playerPlay.printBattleshipTable();
            System.out.println("Player "+(id==0?1:2)+", it's your turn:");
            id = id==0?1:0;
            Player waitingPlayer = players.get(id);


            attackCoordinates=scanner.nextLine();
            if(isInputFormatOk(attackCoordinates,isFormatForShipCoordinates)){

                attackCoordonates=extractAttackCoordinates(attackCoordinates);


                if(waitingPlayer.isShipUnderAttack(attackCoordonates[0],attackCoordonates[1])&&
                        playerPlay.itWasAttacked(attackCoordonates[0],attackCoordonates[1])==false)
                {

                    playerPlay.updatebattleshipAttackTable(attackCoordonates[0], attackCoordonates[1], 'X');
                    waitingPlayer.updateBattleshipTable(attackCoordonates[0], attackCoordonates[1], 'X');


                    if (isShipSank(waitingPlayer.playerShips,attackCoordonates,waitingPlayer.battleshipTable.battleShipTable,playerPlay.battleshipAttackTable.battleShipTable)
                            &&playerPlay.attackCount==17){

                        playerPlay.attackCount+=1;

                        System.out.println("You sank the last ship. You won. Congratulations!");
                        isGameFinish=true;

                    }
                   else if (isShipSank(waitingPlayer.playerShips,attackCoordonates,waitingPlayer.battleshipTable.battleShipTable,playerPlay.battleshipAttackTable.battleShipTable))
                   {

                        playerPlay.attackCount+=1;

                        System.out.println("You sank a ship! Specify a new target:");
                        System.out.println("Press Enter and pass the move to another player");
                        scanner.nextLine();

                    }
                    else {
                    playerPlay.attackCount += 1;

                    System.out.println("You hit a ship!");
                    System.out.println("Press Enter and pass the move to another player");
                    scanner.nextLine();
                     }
                } else
                {
                    playerPlay.updatebattleshipAttackTable(attackCoordonates[0],attackCoordonates[1],'M');
                    waitingPlayer.updateBattleshipTable(attackCoordonates[0],attackCoordonates[1],'M');
                    System.out.println("You missed!") ;
                    System.out.println("Press Enter and pass the move to another player");
                    scanner.nextLine();

                }


            }
            else {

                id = id==1?0:1;


            }

        }





       /* while (isGameFinish == false) {
            attackCoordinates = scanner.nextLine();
            if (isInputFormatOk(attackCoordinates, isFormatForShipCoordinates)) {
                attackCoordonates = extractAttackCoordinates(attackCoordinates);

                if (battleshipTable.battleShipTable[attackCoordonates[0]][attackCoordonates[1]] == 'O') {
                    if(battleshipTable1.battleShipTable[attackCoordonates[0]][attackCoordonates[1]] != 'X') {
                        count += 1;
                    }
                    battleshipTable1.battleShipTable[attackCoordonates[0]][attackCoordonates[1]] = 'X';
                    battleshipTable1.printBattleshipTable(battleshipTable1.battleShipTable);
                    if (isShipSank(ships,attackCoordonates,battleshipTable.battleShipTable,battleshipTable1.battleShipTable)){
                        System.out.println("You sank a ship! Specify a new target:");
                        System.out.println(count);
                    }
                     if (isShipSank(ships,attackCoordonates,battleshipTable.battleShipTable,battleshipTable1.battleShipTable)
                    &&count==17){
                        System.out.println("You sank the last ship. You won. Congratulations!");
                         isGameFinish=true;
                    }

                   else if (isShipSank(ships,attackCoordonates,battleshipTable.battleShipTable,battleshipTable1.battleShipTable)){
                        System.out.println("You sank a ship! Specify a new target:");
                    }

                    else {
                        System.out.println("You hit a ship! Try again:");
                    }
                } else {

                    battleshipTable1.battleShipTable[attackCoordonates[0]][attackCoordonates[1]] = 'M';
                    battleshipTable1.printBattleshipTable(battleshipTable1.battleShipTable);
                    System.out.println("You missed! Try again:");

                }
            }

        } * */
    }


    // THIS METHOD MAKE SURE THAT USER INPUT FOLLOWS THE FORMAT
    public static boolean isInputFormatOk(String userInput, boolean isFormatForShipCoordinates) {

        if (isFormatForShipCoordinates == true) {
            //case 1     A1 C1
            if (userInput.length() == 5 && Pattern.matches("[ABCDEFGHIJ]\\d\\s[ABCDEFGHIJ]\\d", userInput)) {
                return true;
                //case2   A10 B2
            } else if (userInput.length() == 6 && Pattern.matches("[ABCDEFGHIJ][1][0]\\s[ABCDEFGHIJ]\\d", userInput)) {
                return true;
                //case3 A1 B10
            } else if (userInput.length() == 6 && Pattern.matches("[ABCDEFGHIJ]\\d\\s[ABCDEFGHIJ][1][0]", userInput)) {
                return true;
                //case4 A10 D10
            } else if (userInput.length() == 7 && Pattern.matches("[ABCDEFGHIJ][1][0]\\s[ABCDEFGHIJ][1][0]", userInput)) {
                return true;

            } else {
                System.out.println("Something is wrong");
                return false;
            }

            // THIS PART IS FOR ATTACK COORDINATES
        } else if (isFormatForShipCoordinates == false) {
            if (userInput.length() == 2 && Pattern.matches("[ABCDEFGHIJ]\\d", userInput)) {
                return true;
            } else if (userInput.length() == 3 && Pattern.matches("[ABCDEFGHIJ]\\d[0]", userInput)) {
                return true;


            } else {
                System.out.println("Error! You entered the wrong coordinates! Try again:\n");
                return false;
            }


        } else {
            return false;

        }

    }


    public static int[] extractAttackCoordinates(String targetInput) {
        String lineCoordonates = "ABCDEFGHIJ";
        int row = -1;
        int column = -1;
        int[] attackCoordinates = new int[2];
        boolean isHit = false;
        if (targetInput.length() == 3) {
            row = lineCoordonates.indexOf(targetInput.charAt(0));
            column = Character.getNumericValue(targetInput.charAt(1)) * 10 + Character.getNumericValue(targetInput.charAt(2)) - 1;

        } else if (targetInput.length() == 2) {
            row = lineCoordonates.indexOf(targetInput.charAt(0));
            column = Character.getNumericValue(targetInput.charAt(1)) - 1;
        }
        attackCoordinates[0] = row;
        attackCoordinates[1] = column;

        return attackCoordinates;
    }

    // extract ship coordinates from user input + check if user input position the ship only vertically or horizontally;
    // +check if user input match the numbers of cells necesary for ship. IF SOMETHING IS WRONG THE METHOD THROWN wrongPosition Exception

    public static int[] extractShipCoordinates(String coordinates, shipsOfTheGame ship) throws wrongPosition {

        String lineCoordonates = "ABCDEFGHIJ";
        String[] shipsName = {"Destroyer", "Cruiser", "Submarine", "Battleship", "Aircraft Carrier"};
        int starterRow = -1;
        int startColumn = -1;
        int finishRow = -1;
        int finishColumn = -1;
        int[] shipCoordinates = new int[4];

        if (coordinates.length() == 5) {
            starterRow = lineCoordonates.indexOf(coordinates.charAt(0));
            startColumn = Character.getNumericValue(coordinates.charAt(1)) - 1;
            finishRow = lineCoordonates.indexOf(coordinates.charAt(3));
            finishColumn = Character.getNumericValue(coordinates.charAt(4)) - 1;
        } else if (coordinates.length() == 6) {
            if (' ' == coordinates.charAt(2)) {
                starterRow = lineCoordonates.indexOf(coordinates.charAt(0));
                finishRow = lineCoordonates.indexOf(coordinates.charAt(3));
                startColumn = Character.getNumericValue(coordinates.charAt(1)) - 1;
                finishColumn = 9;
            } else if (' ' != coordinates.charAt(2)) {
                starterRow = lineCoordonates.indexOf(coordinates.charAt(0));
                finishRow = lineCoordonates.indexOf(coordinates.charAt(4));
                startColumn = 9;
                finishColumn = Character.getNumericValue(coordinates.charAt(5)) - 1;
            }

        } else if (coordinates.length() == 7) {

            starterRow = lineCoordonates.indexOf(coordinates.charAt(0));
            finishRow = lineCoordonates.indexOf(coordinates.charAt(4));
            startColumn = 9;
            finishColumn = 9;
        }

        //check if ships coordonates is positionate strictly vertical or horizontal
        if (starterRow == finishRow || startColumn == finishColumn) {

        } else {
            System.out.println("Error! Wrong ship location! Try again:\n");
            throw new wrongPosition("Error");

        }

        // check if ships is posionated corectyl
        if (Math.abs(starterRow - finishRow) == ship.getNumberOfCells() - 1 || Math.abs(startColumn - finishColumn) == ship.getNumberOfCells() - 1) {


        } else {
            System.out.println("Error! Wrong length of the" + " " + ship.getNameOfShip() + "! Try again:");
            throw new wrongPosition("(Incorrect length of the ship)..");
        }


        int swap;
        if (starterRow > finishRow) {
            swap = starterRow;
            starterRow = finishRow;
            finishRow = swap;
        }

        if (startColumn > finishColumn) {
            swap = startColumn;
            startColumn = finishColumn;
            finishColumn = swap;
        }
        shipCoordinates[0] = starterRow;
        shipCoordinates[1] = finishRow;
        shipCoordinates[2] = startColumn;
        shipCoordinates[3] = finishColumn;

        return shipCoordinates;

    }

    public static void checkPosition(int startRow, int finihRow, int startColumn, int finishColumn, char battleshipTable[][]) throws wrongPosition {
        int startRowIndex = startRow != 0 && startRow != 9 ? startRow - 1 : ((startRow == 9) ? startRow - 1 : startRow);
        int finishRowIndex = finihRow != 0 && finihRow != 9 ? finihRow + 1 : ((finihRow == 0) ? finihRow + 1 : finihRow);
        int startColumnIndex = startColumn != 0 && finishColumn != 9 ? startColumn - 1 : ((startColumn == 9) ? startColumn - 1 : startColumn);
        int finishColumnIndex = startColumn != 0 && finishColumn != 9 ? finishColumn + 1 : ((finishColumn == 0) ? finishColumn + 1 : finishColumn);


        for (int i = startRowIndex; i <= finishRowIndex; i++) {
            for (int j = startColumnIndex; j <= finishColumnIndex; j++) {
                if (battleshipTable[i][j] != '~') {
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                    throw new wrongPosition("Error! Wrong ship location! Try again:\n");
                }
            }
        }


    }


    // THIS METHOD TAKE A SHIPS list and check if attackCoordinates is between start and finish coordinates of the ship, and if is check if the whole ship is attacked
    public boolean isShipSank(List<Ships> ships, int[] attackCoordinates, char[][] battleshipTableWithShips, char[][] battleshipTableWithoutShips) {
        int rowAttackCoordinates = attackCoordinates[0];
        int columnAttackCoordinates = attackCoordinates[1];
        boolean isShipSank = false;

        for (Ships ship : ships) {
            if (rowAttackCoordinates >= ship.startRow && rowAttackCoordinates <= ship.finishRow && ship.startRow == ship.finishRow) {
                for (int i = ship.startColumn; i <= ship.finishColumn; i++) {
                    if (battleshipTableWithShips[ship.startRow][i] == 'X' && battleshipTableWithoutShips[ship.startRow][i] == 'X') {
                        isShipSank = true;

                    } else {
                        isShipSank = false;
                        break;

                    }

                }


            } else if (columnAttackCoordinates >= ship.startColumn && columnAttackCoordinates <= ship.finishColumn && ship.startColumn == ship.finishColumn) {

                for (int i = ship.startRow; i <= ship.finishRow; i++) {
                    if (battleshipTableWithShips[i][ship.startColumn] == 'X' && battleshipTableWithoutShips[i][ship.startColumn] == 'X') {
                        isShipSank = true;

                    } else {
                        isShipSank = false;
                        break;

                    }


                }


            }


        }


        return isShipSank;

    }
}






