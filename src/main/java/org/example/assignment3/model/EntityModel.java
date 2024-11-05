package org.example.assignment3.model;
import java.util.ArrayList;

public class EntityModel {
    private ArrayList<Subscriber> subsLst;
    private ArrayList<Box> boxLst;

    private double viewLeft;
    private double viewTop;
    private double viewWidth = 0;
    private double viewHeight = 0;
    private double world = 2000;

    public EntityModel() {
        subsLst = new ArrayList<>();
        boxLst = new ArrayList<>();
        viewLeft = 0;
        viewTop = 0;
    }

    public double getViewLeft() {
        return viewLeft;
    }
    public void setViewLeft(double viewLeft) {
        this.viewLeft = viewLeft;
    }
    public double getViewTop() {
        return viewTop;
    }
    public void setViewTop(double viewTop) {
        this.viewTop = viewTop;
    }
    public void setViewWidth(double vW) {
        viewWidth = vW;
    }
    public void setViewHeight(double vH) {
        viewHeight = vH;
    }
    public double getViewWidth(){
        return viewWidth;
    }
    public double getViewHeight(){
        return viewHeight;
    }

    public void panView(double dX, double dY) {
        viewLeft = Math.max(0, Math.min(world - viewWidth, viewLeft - dX));
        viewTop = Math.max(0, Math.min(world - viewHeight, viewTop - dY));
        notifySubscribers();
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
