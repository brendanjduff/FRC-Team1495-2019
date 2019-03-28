package frc.robot.vision;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Robot.roboDrive;


public class RobotVision {

    private static RobotVision inst = null;

    /*
    *  Vision Settings
    */
    private NetworkTable ntSettingsTable = null;

    private double targetPixel = 339.5;
    private double focalLength = 550.2;
    private double angleThreshold = 1;





    /*
     * Vision Target Variables
     */
    private NetworkTable ntVisionTable = null;
    private NetworkTableEntry ntVisionWidth;
    private NetworkTableEntry ntVisionCount;
    private NetworkTableEntry ntVisionAngle;
    private NetworkTableEntry ntVisionArea;
    private NetworkTableEntry ntVisionX;
    private NetworkTableEntry ntVisionY;
    private NetworkTableEntry ntVisionHeight;
    private final double[] DEF_ARR = {0, 0};

    private int targetCount;
    private double[] targetWidthArray;
    private double[] targetAngleArray;
    private double[] targetAreaArray;
    private double[] targetXArray;
    private double[] targetYArray;
    private double[] targetHeightArray;

    public static synchronized RobotVision getInstance() {
        if (inst == null) {
            inst = new RobotVision();
        }
        return inst;
    }

    //Prevents Users from creating separate vision Thread
    private RobotVision() {
        ntVisionTable = null;
    }


    public void runPeriodicUpdate() {
        updateVisionTargetData();
        //TODO Implement SmartDashboard data HERE
        SmartDashboard.putBoolean("Vision Ready Status", isReady());
    }

    public boolean isReady() {
        updateVisionTargetData();
        return targetCount >= 1;
    }

    public void runVisionGuidanceUpdate(int mode) {
        switch (mode) {
            case 0:
                version1();
                break;
            case 1:
                yawDifferential();
                break;
            default:
                System.out.println("ERROR! Invalid Driving Mode");
        }
    }

    public void setNtVisionTable(NetworkTable ntVisionTable) {
        if(this.ntVisionTable != null)
        {
            System.out.println("WARNING! Vision table data attempted to be initialized twice! Exiting...");
            return;
        }
        this.ntVisionTable = ntVisionTable;
        ntVisionWidth = (this.ntVisionTable.getEntry("WIDTH"));
        ntVisionAngle = (this.ntVisionTable.getEntry("ANGLE"));
        ntVisionArea = (this.ntVisionTable.getEntry("AREA"));
        ntVisionX = (this.ntVisionTable.getEntry("X"));
        ntVisionY = (this.ntVisionTable.getEntry("Y"));
        ntVisionHeight = (this.ntVisionTable.getEntry("HEIGHT"));
        ntVisionCount = (this.ntVisionTable.getEntry("COUNT"));
    }

    public void setNtSettingsTable(NetworkTable ntSettingsTable) {
        if(this.ntSettingsTable != null) {
            System.out.println("WARNING! Vision table data attempted to be initialized twice! Exiting...");
            return;
        }

        this.ntSettingsTable = ntSettingsTable;

        this.ntSettingsTable.getEntry("FocalLength").getDouble(339.5);

        this.ntSettingsTable.addEntryListener("FocalLength", (table, key, entry, value, flags) ->{
            focalLength = entry.getValue().getDouble();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        //TODO Add all the network table vision setting listeners

    }



    private void version1() {
        double goodEnough = 3;
        double expectedXCentered = 213;
        if (isReady()) {
            //GetLeftTarget
            int targetE = getLowerAngle();

            SmartDashboard.putBoolean("In DeadZone", Math.abs(targetXArray[targetE] - expectedXCentered) > goodEnough);
            if (Math.abs(targetXArray[targetE] - expectedXCentered) > goodEnough) {
                //If diff is positive then we've overshot right
                double difference = expectedXCentered - targetXArray[targetE];
                if (difference > 0) {
                    roboDrive.arcadeDrive(.2, .2);
                } else {
                    roboDrive.arcadeDrive(.2, -.2);
                }
            } else {
                roboDrive.arcadeDrive(-.5, 0);
            }
        } else {
            roboDrive.arcadeDrive(-.5, 0);
        }
    }
    private int getLowerAngle() {
        updateVisionTargetData();
        if (targetCount == 2) {
            if (targetXArray[0] < 40)
                return 0;
            else
                return 1;
        }

        return -1;
    }


    private VisionTarget target = null;
    private void yawDifferential() {
        int targetIndexSelection = 0;

        if (targetCount > 1)
            for (int i = 1; i < targetAngleArray.length; i++)
                if (targetAngleArray[i] > 50 && targetAreaArray[targetIndexSelection] < targetAreaArray[i])
                    targetIndexSelection = i;
         else //noinspection ConstantConditions
                    if (targetCount == 1 && targetAngleArray[0] < 50)
            return;
        else
            return;

        target = updateRoboTarget(targetIndexSelection);

        double angleToTarget = Math.toDegrees(Math.atan((target.getX()-targetPixel)/focalLength));

        if(Math.abs(angleToTarget) > angleThreshold)
        {
            //TODO Implement roboDrive response if the angle is above the threshold
        }



    }


    private void updateVisionTargetData() {
        if (this.ntVisionTable == null)
            return;

        targetCount = (int) ntVisionCount.getDouble(0);
        targetWidthArray = ntVisionWidth.getDoubleArray(DEF_ARR);
        targetAngleArray = ntVisionAngle.getDoubleArray(DEF_ARR);
        targetAreaArray = ntVisionArea.getDoubleArray(DEF_ARR);
        targetXArray = ntVisionX.getDoubleArray(DEF_ARR);
        targetYArray = ntVisionY.getDoubleArray(DEF_ARR);
        targetHeightArray = ntVisionHeight.getDoubleArray(DEF_ARR);
    }

    private VisionTarget updateRoboTarget(int i) {
        updateVisionTargetData();
        return new VisionTarget(
                i,
                targetXArray[i],
                targetYArray[i],
                targetHeightArray[i],
                targetWidthArray[i],
                targetAreaArray[i],
                targetAngleArray[i]
        );
    }

}