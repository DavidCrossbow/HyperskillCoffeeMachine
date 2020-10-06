package machine;

import java.util.Scanner;

class CoffeeMaker {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public CoffeeMaker(int water, int milk, int beans, int cups, int money) {
        setWater(water);
        setMilk(milk);
        setBeans(beans);
        setCups(cups);
        setMoney(money);
    }

    private void setWater(int water) {
        if (water >= 0) {
            this.water = water;
        }
    }

    private void setMilk(int milk) {
        if (milk >= 0) {
            this.milk = milk;
        }
    }

    private void setBeans(int beans) {
        if (beans >= 0) {
            this.beans = beans;
        }
    }

    private void setCups(int cups) {
        if (cups >= 0) {
            this.cups = cups;
        }
    }

    private void setMoney(int money) {
        if (money >= 0) {
            this.money = money;
        }
    }

    private String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private String action() {
        System.out.println(System.lineSeparator() + "Write action (buy, fill, take, remaining, exit):");
        return input();
    }

    private void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        int addWater = Integer.parseInt(input());
        if (addWater >= 0) {
            water += addWater;
        }
        System.out.println("Write how many ml of milk do you want to add:");
        int addMilk = Integer.parseInt(input());
        if (addMilk >= 0) {
            milk += addMilk;
        }
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int addBeans = Integer.parseInt(input());
        if (addBeans >= 0) {
            beans += addBeans;
        }

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int addCups = Integer.parseInt(input());
        if (addCups >= 0) {
            cups += addCups;
        }
    }

    private void state() {
        System.out.println(System.lineSeparator() + "The coffee machine has:");
        System.out.printf("%d of water" + System.lineSeparator(), water);
        System.out.printf("%d of milk" + System.lineSeparator(), milk);
        System.out.printf("%d of coffee beans" + System.lineSeparator(), beans);
        System.out.printf("%d of disposable cups" + System.lineSeparator(), cups);
        System.out.printf("%d of money" + System.lineSeparator(), money);
    }

    private void take() {
        System.out.printf("I gave you $%d" + System.lineSeparator(), money);
        money = 0;
    }

    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        switch (input()) {
            case "1":
                doOrNot(Coffee.ESPRESSO);
                break;
            case "2":
                doOrNot(Coffee.LATTE);
                break;
            case "3":
                doOrNot(Coffee.CAPPUCCINO);
                break;
            case "back":
                break;
            default:
                System.out.println("Try again");
        }
    }

    private void doOrNot(Coffee coffee) {
        if (water < coffee.getWater()) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < coffee.getMilk()) {
            System.out.println("Sorry, not enough milk!");
        } else if (beans < coffee.getBeans()) {
            System.out.println("Sorry, not enough beans!");
        } else if (cups < coffee.getCups()) {
            System.out.println("Sorry, not enough cups!");
        } else {
            water -= coffee.getWater();
            milk -= coffee.getMilk();
            beans -= coffee.getBeans();
            cups -= coffee.getCups();
            money += coffee.getMoney();
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    enum State {
        WAITING_AN_ACTION,
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT;

        public void changeState(CoffeeMaker coffeeMaker) {
            switch (State.valueOf((coffeeMaker.action().toUpperCase()))) {
                case BUY:
                    coffeeMaker.buy();
                    changeState(coffeeMaker);
                    break;
                case FILL:
                    coffeeMaker.fill();
                    changeState(coffeeMaker);
                    break;
                case REMAINING:
                    coffeeMaker.state();
                    changeState(coffeeMaker);
                    break;
                case TAKE:
                    coffeeMaker.take();
                    changeState(coffeeMaker);
                    break;
                case EXIT:
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    enum Coffee {
        ESPRESSO(250, 0, 16, 1, 4),
        LATTE(350, 75, 20, 1, 7),
        CAPPUCCINO(200, 100, 12, 1, 6);

        private final int water;
        private final int milk;
        private final int beans;
        private final int cups;
        private final int money;

        Coffee(int water, int milk, int beans, int cups, int money) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
            this.money = money;
        }

        private int getWater() {
            return water;
        }

        private int getMilk() {
            return milk;
        }

        private int getBeans() {
            return beans;
        }

        private int getCups() {
            return cups;
        }

        private int getMoney() {
            return money;
        }
    }
}