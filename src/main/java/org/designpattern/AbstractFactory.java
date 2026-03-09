package org.designpattern;


// abstract pdt 1
interface Chair{
    void sitOn();
}

// abstract pdt 2
interface Sofa{
    void layOn();
}

// Concrete pdt 1A
class VictorianChair implements Chair{
    @Override
    public void sitOn() {
        System.out.println("Victorian chair sitOn");
    }
}

// Concrete pdt 1A
class MordernChair implements Chair{
    @Override
    public void sitOn() {
        System.out.println("Modern chair sitOn");
    }
}

// Concrete pdt 2A
class VictorianSofa implements Sofa{
    @Override
    public void layOn() {
        System.out.println("Victorian chair layOn");
    }
}

// Concrete pdt 2B
class MordernSofa implements Sofa{
    @Override
    public void layOn() {
        System.out.println("Modern chair layOn");
    }
}


// Abstract factory
interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}

// Concrete Factory 1
class VictorianFurniture implements FurnitureFactory{
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }
}

// Concrete Factory 2
class MordernFurniture implements FurnitureFactory{
    @Override
    public Chair createChair() {
        return new MordernChair();
    }

    @Override
    public Sofa createSofa() {
        return new MordernSofa();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        FurnitureFactory factory = new VictorianFurniture();
        Chair chair = factory.createChair();
        Sofa sofa = factory.createSofa();

        chair.sitOn();
        sofa.layOn();
    }
}
