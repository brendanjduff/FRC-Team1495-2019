package frc.robot.vision;

public class VisionAlignRotate {

    private static VisionAlignRotate instance = null;

    public synchronized VisionAlignRotate getInstance()
    {

        if(instance == null)
            instance = new VisionAlignRotate();

    }


}
