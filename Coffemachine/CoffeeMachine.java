package machine;

import java.util.InputMismatchException;
import java.util.Scanner;



enum Product {
    espresso(250,0,16,1,4)
    , latte(350,75,20,1,7)
    , cappucino(200,100,12,1,6);

    int waterNecessar;
    int milkNecessar;
    int coffebeansNecessar;
    int cupsNecessar;
    int productPrice;

    Product(int waterNecessar, int milkNecessar, int coffebeansNecessar, int cupsNecessar, int productPrice) {

        this.waterNecessar=waterNecessar;
        this.milkNecessar=milkNecessar;
        this.coffebeansNecessar=coffebeansNecessar;
        this.cupsNecessar=cupsNecessar;
        this.productPrice=productPrice;

    }


}

public class CoffeeMachine {

    private int water;
    private int milk;
    private int coffeBeans;
    private int cups;
    private int maxCoffe;
    private int money;



    public CoffeeMachine(int water,int milk,int coffeBeans,int money,int cups){
        this.cups=cups;
        this.water=water;
        this.milk=milk;
        this.coffeBeans=coffeBeans;
        this.maxCoffe = Math.min(Math.min(water/200,milk/50),coffeBeans/15);
        this.money=money;
    }







    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //int waterInMachine=scanner.nextInt();
        //int milkInMachine=scanner.nextInt();
        //int coffeBeansInMachine = scanner.nextInt();
        //int moneyInMachine = scanner.nextInt();
        String userAction;

        CoffeeMachine coffeeMachine = new CoffeeMachine(400,540,120,550,9);



        System.out.println("Write action (buy, fill, take, remaining, exit):");
        boolean isActive=true;


        while (isActive==true)
        {

            userAction = scanner.next();

        switch (userAction) {

            case "buy":
                coffeeMachine.buy();
                System.out.println("Write action (buy, fill, take, remaining, exit):");

                break;

            case "fill":
                coffeeMachine.fill();
                System.out.println("Write action (buy, fill, take, remaining, exit):");

                break;

            case "take":
                coffeeMachine.takeMoney();
                System.out.println("Write action (buy, fill, take, remaining, exit):");

                break;

            case "remaining":
                coffeeMachine.showCoffeMachineDetails();
                System.out.println("Write action (buy, fill, take, remaining, exit):");

                break;
            case "exit":
                isActive = false;
                break;
            default:
                System.out.println("Something wrong");
           }
        }
    }


    public void isEnoughIngredients(int numberOfCoffee)
    {
        int waterNecessar = numberOfCoffee*200;
        int milkNecessar  =numberOfCoffee*50;
        int coffebeansNecessar = numberOfCoffee*15;
        int cupsDifference;

        if(maxCoffe>numberOfCoffee){
            cupsDifference=maxCoffe-numberOfCoffee;
            System.out.println("Yes, I can make that amount of coffee (and even "+cupsDifference+ " more than that)");



        }
        else if(maxCoffe<numberOfCoffee){

            System.out.println("No, I can make only "+maxCoffe+ " cup(s) of coffee");

        } else if(maxCoffe==numberOfCoffee)
        {

            System.out.println("Yes, I can make that amount of coffee");
        }






    }

    public void showCoffeMachineDetails(){
        System.out.println("The coffe machine has:");
        System.out.println(water+" ml of water");
        System.out.println(milk+" ml of milk");
        System.out.println(coffeBeans+" g of coffe beans");
        System.out.println(cups+" disposable cups");
        System.out.println("$"+money+" of money");


    }

    public void makeCoffe(Product product){

        if(this.water>product.waterNecessar&&milk>product.waterNecessar&&
           this.coffeBeans>product.coffebeansNecessar&&this.cups>product.cupsNecessar){
            System.out.println("I have enough resources, making you a coffee!");
            this.water-=product.waterNecessar;
            this.milk-=product.milkNecessar;
            this.coffeBeans-=product.coffebeansNecessar;
            this.cups-=product.cupsNecessar;
            this.money+=product.productPrice;
        }else
        {
            System.out.println("Sorry, not enough water!");

        }



    }





    public void fill(){
       Scanner scanner = new Scanner(System.in);
       int waterFill;
       int milkFill;
       int coffeBeansFill;
       int cupsFill;

        System.out.println("Write how many ml of water you want to add:");
        waterFill=scanner.nextInt();
        this.water+=waterFill;
        System.out.println("Write how many ml of milk you want do add:");
        milkFill=scanner.nextInt();
        this.milk+=milkFill;
        System.out.println("Write how many grams of coffe beans you want to add:");
        coffeBeansFill=scanner.nextInt();
        this.coffeBeans+=coffeBeansFill;
        System.out.println("Write how many disposable cups of coffe you want to add:");
        cupsFill=scanner.nextInt();
        this.cups+=cupsFill;


    }


    public void takeMoney(){
        this.money-=this.money;
        System.out.println("I gave you "+"$"+money);


    }


    public void buy(){
        Scanner scanner = new Scanner(System.in);
        int userInput=9;

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");



            try {

                userInput = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input ");

            }
            scanner.nextLine();






        switch (userInput){

            case 1:
                makeCoffe(Product.espresso);
                break;

            case 2:
                makeCoffe(Product.latte);
               break;

            case 3:
                makeCoffe(Product.cappucino);
                break;
            default:
                System.out.println("Something is wrong");

        }


    }




}
