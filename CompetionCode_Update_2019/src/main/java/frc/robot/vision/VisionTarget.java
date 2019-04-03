package frc.robot.vision;

public class VisionTarget
{

    private int elemInArray;
    private double x;
    private double y;
    private double height;
    private double width;
    private double area;
    private double angle;


    VisionTarget(int i, double x,double y,double height,double width,double area, double angle)
    {
        elemInArray = i;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.area = area;
        this.angle = angle;
    }


    public int getElemInArray() {
        return elemInArray;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getArea() {
        return area;
    }



}
