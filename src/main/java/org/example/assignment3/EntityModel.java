package org.example.assignment3;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class EntityModel {
    private ArrayList<Subscriber> subsLst;
    private ArrayList<Entity> entitsLst;

    public EntityModel() {
        subsLst = new ArrayList<>();
        entitsLst = new ArrayList<>();
    }

    public void addEntity(double x, double y) {
        entitsLst.add(new Entity(x, y));
        notifySubscribers();
    }

    public List<Entity> getEntities() {
        return entitsLst;
    }

    public void moveEntity(Entity ntt, double dx, double dy) {
        ntt.move(dx, dy);
        notifySubscribers();
    }

    public Entity whichEntity(double x, double y) {
        return entitsLst.stream().filter(e -> e.contains(x,y)).findFirst().orElse(null);
    }

    public void notifySubscribers() {
        subsLst.forEach(Subscriber::modelChanged);
    }
    public void addSubscriber(Subscriber s) {
        subsLst.add(s);
    }
    public boolean contains(double x, double y) {
        return entitsLst.stream().anyMatch(e -> e.contains(x,y));
    }
}
