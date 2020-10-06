package machine;

public class CoffeeMachine {

    public static void main(String[] args) {
        CoffeeMaker coffeeMaker = new CoffeeMaker(400, 540, 120, 9, 550);
        CoffeeMaker.State.WAITING_AN_ACTION.changeState(coffeeMaker);
    }
}