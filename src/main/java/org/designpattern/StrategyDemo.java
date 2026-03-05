package org.designpattern;

/*

Behavioural Pattern
Implements Nature

From blueprint to Strategies
And a Context/Strategy Changer Concrete Class ( private obj (+DIP) + Strategy Changer + Strategy pass on )

Key Principle Used: Open–Closed Principle [ add new payment methods without modifying existing code ]
Constructn injection: DIP

 */


interface Payment{
    abstract void pay();
}

class PayPalPayment implements Payment{
    @Override
    public void pay() {
        System.out.println("Paying through PayPal Payment");
    }
}


class CreditCardPayment implements Payment{
    @Override
    public void pay() {
        System.out.println("Paying through Credit Card ");
    }
}


class ShoppingCart {
//class PaymentStrategyService {
    // Private Obj
    private Payment payment;

    ShoppingCart(Payment payment) {
        this.payment = payment;
    }

//    when without DIP
//    public void setPaymentStrategy(Payment payment) {
//        this.payment = payment;
//    }

    public void changePaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public void checkout(){
        payment.pay();
    }
}

public class StrategyDemo {

    public static void main(String[] args) {
//        PaymentStrategyService paymentStatergyService = new PaymentStrategyService();
        ShoppingCart shoppingCart = new ShoppingCart(new CreditCardPayment());

        shoppingCart.changePaymentStrategy(new PayPalPayment());
        shoppingCart.checkout();

        shoppingCart.changePaymentStrategy(new CreditCardPayment());
        shoppingCart.checkout();
    }

}
