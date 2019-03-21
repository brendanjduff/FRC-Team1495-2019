package frc.robot;

import edu.wpi.first.networktables.NetworkTable;

import java.util.ArrayList;

public class RobotVision
{
    private static RobotVision inst;
    private NetworkTable visionTable;
    private int targetCount;
   // private double

    public static synchronized RobotVision getInstance()
    {
        if(inst == null)
        {
            inst = new RobotVision();
        }
        return inst;
    }

    private RobotVision()
    {

    }

    //public void

    public void setVisionTable(NetworkTable visionTable)
    {
        this.visionTable = visionTable;
    }
}
