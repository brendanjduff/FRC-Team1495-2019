package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

import java.util.ArrayList;


public class RobotVision
{

    public enum Value {Count, Width,Angle,Area,X,Y,Height,All}

    private ArrayList<RoboTarget> leftTargets = new ArrayList<>();
    private ArrayList<RoboTarget> rightTargets = new ArrayList<>();


    /*
    * Vision Target Variables
    */
    private static RobotVision inst;
    private NetworkTable visionTable;
    private NetworkTableEntry visionWidth;
    private NetworkTableEntry visionCount;
    private NetworkTableEntry visionAngle;
    private NetworkTableEntry visionArea;
    private NetworkTableEntry visionX;
    private NetworkTableEntry visionY;
    private NetworkTableEntry visionHeight;
    private final double[] DEF_ARR = {0,0};

    private int targetCount;
    private double[] targetWidth;
    private double[] targetAngle;
    private double[] targetArea;
    private double[] targetX;
    private double[] targetY;
    private double[] targetHeight;

    public static synchronized RobotVision getInstance()
    {
        if(inst == null)
        {
            inst = new RobotVision();
        }
        return inst;
    }

    //Prevents Users from creating separate vision Thread
    private RobotVision()
    {
        visionTable = null;
    }




    public boolean isReady()
    {
        updateAllValues();
        return targetCount >= 2;
    }

    public boolean isLeftTarget(RoboTarget item)
    {


        return false;
    }

    public int getLowerAngle()
    {
        updateAllValues();
        if(targetCount == 2)
        {
            if(targetX[0] < 40)
                return 0;
            else
                return 1;
        }

        return -1;
    }


    public void setVisionTable(NetworkTable visionTable)
    {
        this.visionTable = visionTable;
        visionWidth = (this.visionTable.getEntry("WIDTH"));
        visionAngle = (this.visionTable.getEntry("ANGLE"));
        visionArea = (this.visionTable.getEntry("AREA"));
        visionX = (this.visionTable.getEntry("X"));
        visionY = (this.visionTable.getEntry("Y"));
        visionHeight = (this.visionTable.getEntry("HEIGHT"));
        visionCount = (this.visionTable.getEntry("COUNT"));
    }


    public void updateAllValues()
    {
        if(this.visionTable == null)
            return;

        targetCount = (int) visionCount.getDouble(0);
        targetWidth = visionWidth.getDoubleArray(DEF_ARR);
        targetAngle = visionAngle.getDoubleArray(DEF_ARR);
        targetArea  = visionArea.getDoubleArray(DEF_ARR);
        targetX = visionX.getDoubleArray(DEF_ARR);
        targetY = visionY.getDoubleArray(DEF_ARR);
        targetHeight = visionHeight.getDoubleArray(DEF_ARR);
    }

    public double[] getTargetWidth() {
        return targetWidth;
    }

    public double[] getTargetAngle() {
        return targetAngle;
    }

    public double[] getTargetArea() {
        return targetArea;
    }

    public double[] getTargetX() {
        return targetX;
    }

    public double[] getTargetY() {
        return targetY;
    }

    public double[] getTargetHeight() {
        return targetHeight;
    }

    public int getTargetCount() {
        return targetCount;
    }

}
