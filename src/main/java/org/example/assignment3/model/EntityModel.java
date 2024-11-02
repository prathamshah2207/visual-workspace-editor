package org.example.assignment3.model;
import java.util.ArrayList;

public class EntityModel {
    private ArrayList<Subscriber> subsLst;
    private ArrayList<Box> boxLst;

    public EntityModel() {
        subsLst = new ArrayList<>();
        boxLst = new ArrayList<>();
    }

    public Box makeObject(double x, double y) {
        Box box = new Box(x, y);
        notifySubscribers();
        return box;
    }

    public void addObject(Box b) {
        boxLst.add(b);
        notifySubscribers();
    }
    public void removeObject(Box b) {
        boxLst.remove(b);
        notifySubscribers();
    }

    public ArrayList<Box> getObjects() {
        return boxLst;
    }

    public void moveObject(Box b, double dx, double dy) {
        if (boxLst.contains(b)) {
            b.move(dx, dy);
            notifySubscribers();
        }
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
