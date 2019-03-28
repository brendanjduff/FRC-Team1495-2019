package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Robot.roboDrive;


public class RobotVision {

    public enum Value {Count, Width, Angle, Area, X, Y, Height, All}

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
        visionTable = null;
    }


    public void runVisionGuidanceUpdate(int mode) {
        updateAllValues();
        switch (mode) {
            case 0:
                version1();
                break;
            case 1:
                yawDifferential();
                break;
        }
    }

    public double goodEnough = 3;
    public double expectedXCentered = 213;

    private void version1() {
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


    VisionTarget target = null;

    private void yawDifferential() {
        int e = 0;

        if (target == null && targetCount > 1) {
            for (int i = 1; i < targetAngleArray.length; i++) {
                if (targetAngleArray[i] > 50 && targetAreaArray[e] < targetAreaArray[i])
                    e = i;
            }
        } else if (targetCount == 1) {
            if (targetAreaArray[0] > 50) {
                e=0;

            } else {
                return;
            }
        }
        target = updateRoboTarget(0);
    }


    public boolean isReady() {
        updateAllValues();
        return targetCount >= 2;
    }


    public int getLowerAngle() {
        updateAllValues();
        if (targetCount == 2) {
            if (targetXArray[0] < 40)
                return 0;
            else
                return 1;
        }

        return -1;
    }


    public void setVisionTable(NetworkTable visionTable) {
        this.visionTable = visionTable;
        visionWidth = (this.visionTable.getEntry("WIDTH"));
        visionAngle = (this.visionTable.getEntry("ANGLE"));
        visionArea = (this.visionTable.getEntry("AREA"));
        visionX = (this.visionTable.getEntry("X"));
        visionY = (this.visionTable.getEntry("Y"));
        visionHeight = (this.visionTable.getEntry("HEIGHT"));
        visionCount = (this.visionTable.getEntry("COUNT"));
    }


    private void updateAllValues() {
        if (this.visionTable == null)
            return;

        targetCount = (int) visionCount.getDouble(0);
        targetWidthArray = visionWidth.getDoubleArray(DEF_ARR);
        targetAngleArray = visionAngle.getDoubleArray(DEF_ARR);
        targetAreaArray = visionArea.getDoubleArray(DEF_ARR);
        targetXArray = visionX.getDoubleArray(DEF_ARR);
        targetYArray = visionY.getDoubleArray(DEF_ARR);
        targetHeightArray = visionHeight.getDoubleArray(DEF_ARR);
    }

    private VisionTarget updateRoboTarget(int i)
    {
        updateAllValues();
        VisionTarget newTarget =  new VisionTarget(
                i,
                targetXArray[i],
                targetYArray[i],
                targetHeightArray[i],
                targetWidthArray[i],
                targetAreaArray[i],
                targetAngleArray[i]
                );
        return newTarget;
    }

}