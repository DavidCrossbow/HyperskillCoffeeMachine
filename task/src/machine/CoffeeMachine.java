package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static int cups = 9;
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int money = 550;
    static String action;

    public static String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void main(String[] args) {

        do {
            action = action();
            choice();
        }
        while (!action.equals("exit"));
    }

    public static void state() {
        System.out.println(System.lineSeparator() + "The coffee machine has:");
        System.out.printf("%d of water" + System.lineSeparator(), water);
        System.out.printf("%d of milk" + System.lineSeparator(), milk);
        System.out.printf("%d of coffee beans" + System.lineSeparator(), beans);
        System.out.printf("%d of disposable cups" + System.lineSeparator(), cups);
        System.out.printf("%d of money" + System.lineSeparator(), money);

    }

    public static String action() {
        System.out.println(System.lineSeparator() + "Write action (buy, fill, take, remaining, exit):");
        return input();
    }

    public static void choice() {
        switch (action) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                state();
                break;
            case "exit":
                break;
        }
    }

    public static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        switch (input()) {
            case "1":
                doOrNot(250, 0, 16, 1, 4);
                break;
            case "2":
                doOrNot(350, 75, 20, 1, 7);
                break;
            case "3":
                doOrNot(200, 100, 12, 1, 6);
                break;
            case "back":
                break;
            default:
                System.out.println("Try again");
        }
    }

    public static void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        int addWater = Integer.parseInt(input());
        water += addWater;
        System.out.println("Write how many ml of milk do you want to add:");
        int addMilk = Integer.parseInt(input());
        milk += addMilk;
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int addBeans = Integer.parseInt(input());
        beans += addBeans;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int addCups = Integer.parseInt(input());
        cups += addCups;
    }

    public static void take() {
        System.out.printf("I gave you $%d" + System.lineSeparator(), money);
        money = 0;
    }

    public static void doOrNot(int needWater, int needMilk, int needBeans, int needCups, int addMoney) {
        if (water < needWater) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < needMilk) {
            System.out.println("Sorry, not enough milk!");
        } else if (beans < needBeans) {
            System.out.println("Sorry, not enough beans!");
        } else if (cups < needCups) {
            System.out.println("Sorry, not enough cups!");
        } else {
            water -= needWater;
            milk -= needMilk;
            beans -= needBeans;
            cups -= needCups;
            money += addMoney;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }
}