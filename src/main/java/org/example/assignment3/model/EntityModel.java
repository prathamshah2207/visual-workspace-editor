package org.example.assignment3.model;
import java.util.ArrayList;
import java.util.Objects;

public class EntityModel {
    private ArrayList<Subscriber> subsLst;
    //this is the objects list that will have both the box as well as portal
    private ArrayList<object> objLst;

    private double viewLeft;
    private double viewTop;
    private double viewWidth = 0;
    private double viewHeight = 0;
    private double world = 2000;

    public EntityModel() {
        subsLst = new ArrayList<>();
        objLst = new ArrayList<>();
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
    public Portal makePortal(double x, double y) {
        Portal portal = new Portal(x, y);
        notifySubscribers();
        return portal;
    }

    public void addObject(object b) {
        objLst.add(b);
        notifySubscribers();
    }
    public void removeObject(object b) {
        objLst.remove(b);
        notifySubscribers();
    }

    public ArrayList<object> getObjects() {
        return objLst;
    }

    public void moveObject(object b, double dx, double dy) {
        if (objLst.contains(b)) {
            b.move(dx, dy);
            notifySubscribers();
        }
    }

    public object whichObject(double x, double y) {
        return objLst.stream().filter(e -> e.contains(x,y)).findFirst().orElse(null);
    }

    public void notifySubscribers() {
        subsLst.forEach(Subscriber::modelChanged);
    }
    public void addSubscriber(Subscriber s) {
        subsLst.add(s);
    }
    public boolean contains(double x, double y) {
        return objLst.stream().anyMatch(e -> e.contains(x,y));
    }
}
