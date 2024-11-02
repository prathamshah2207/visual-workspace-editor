package org.example.assignment3.model;
import java.util.ArrayList;
import java.util.List;

public class EntityModel {
    private ArrayList<Subscriber> subsLst;
    private ArrayList<Box> boxLst;

    public EntityModel() {
        subsLst = new ArrayList<>();
        boxLst = new ArrayList<>();
    }

    public void addObject(double x, double y) {
        boxLst.add(new Box(x, y));
        notifySubscribers();
    }

    public ArrayList<Box> getObjects() {
        return boxLst;
    }

    public void moveObject(Box bx, double dx, double dy) {
        bx.move(dx, dy);
        notifySubscribers();
    }

    public Box whichObject(double x, double y) {
        return boxLst.stream().filter(e -> e.contains(x,y)).findFirst().orElse(null);
    }

    public void notifySubscribers() {
        subsLst.forEach(Subscriber::modelChanged);
    }
    public void addSubscriber(Subscriber s) {
        subsLst.add(s);
    }
    public boolean contains(double x, double y) {
        return boxLst.stream().anyMatch(e -> e.contains(x,y));
    }
}
