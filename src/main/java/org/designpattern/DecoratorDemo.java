package org.designpattern;

/*

Structural Pattern
Extend Nature

SOLID followed ( just makesure ISP - Clients should not be forced to depend on methods they do not use)

// Component Interface
✅ Interface (most common & preferred)
✅ Abstract class (sometimes useful)

 */
// Component Interface
abstract class Pizza{
    public abstract String  getPizzaDescription();
    public abstract double getPizzaPrice();
}

class StandardPizza extends Pizza{
    private final static  double cost = 100;
    @Override
    public String  getPizzaDescription(){
        return "Standard Pizza";
    }

    @Override
    public double getPizzaPrice(){
        return cost;
    }
}

// abstract BASEDECORATOR class - never instantiated ( decorators super's to this class )
abstract class PizzaDecorator extends Pizza{
    // PROTECTED OBJECT --- so as child/herited class to access it directly
    protected Pizza pizza;

    // CATCH THE SUPER
    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }

    // ABSTRACT METHODS
    public abstract String getPizzaDescription();
    public abstract double getPizzaPrice();
}


// DECORATING CLASS - EXTENDS & SUPER to BASE DECORATOR
class TomatoToppingDecorator extends PizzaDecorator{

    TomatoToppingDecorator(Pizza pizza){
        super(pizza);
    }

    private final static  double cost = 100;

    @Override
    public String  getPizzaDescription(){
        return pizza.getPizzaDescription() + " Tomato topping";
    }

    @Override
    public double getPizzaPrice(){
        return pizza.getPizzaPrice() + cost;
    }
}


// CLIENT
public class DecoratorDemo {

    public static void main(String[] args) {
        Pizza pizza = new StandardPizza();
        System.out.println(pizza.getPizzaDescription());
        System.out.println(pizza.getPizzaPrice());

        pizza = new TomatoToppingDecorator(pizza);
        System.out.println(pizza.getPizzaDescription());
        System.out.println(pizza.getPizzaPrice());
    }
}
