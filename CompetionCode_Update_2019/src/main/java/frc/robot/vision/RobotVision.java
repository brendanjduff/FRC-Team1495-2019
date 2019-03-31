package frc.robot.vision;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import java.util.ArrayList;

import static frc.robot.Robot.roboDrive;


public class RobotVision {


    private static RobotVision inst = null;
    private NetworkTable ntVisionTable;
    private NetworkTable ntSettingsTable = null;

    private boolean isGuidanceActive = false;
    private long timeStartGuidance = 0;

    /*
     *  Vision Settings for vision yaw differential
     */
    private double targetPixel;
    private double focalLength;
    private double angleThreshold = 1.0;
    private double xSlowDownOnCompensation;
    private double zTurnSpeedOnCompensation;

    /*
     * Vision Target Variables
     */
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

    /* EXAMPLE CODE TO IMPLEMENT INTO ROBOT
     *
     * */

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


    /*
     * Should be run on periodic when needed
     * */
    public void runPeriodicUpdate() {
        updateVisionTargetData();
        SmartDashboard.putBoolean("Vision Status", hasTargets());
    }


    /*
     *   Will run visionGuidance based on the mode selected (Call me WhilePressed)
     * */
    public void runVisionGuidanceUpdate(int mode) {
        if (!isNTReady())
            return;
        switch (mode) {
            case 0:
                version1();
                break;
            case 1:
                visionYawDifferential();
                break;
            default:
                System.out.println("ERROR! Invalid Driving Mode");
        }
    }

    /*
       Sets up the NetworkTable Vision settings, allowing for some editing of parameters without reupload of code. MUST BE IMPLEMENTED IN ROBOT FOR VISION GUIDANCE TO WORK
    */
    public void setNtVisionTable(NetworkTable ntVisionTable) {
        if (this.ntVisionTable != null) {
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

    /*
     *  Sets up the network table for the vision Data MUST BE IMPLEMENTED FOR VISION GUIDANCE TO WORK
     * */
    public void setNtSettingsTable(NetworkTable ntSettingsTable) {
        if (this.ntSettingsTable != null) {
            System.out.println("WARNING! Vision table data attempted to be initialized twice! Exiting...");
            return;
        }

        this.ntSettingsTable = ntSettingsTable;


        //I'm not sure how to clean this...
        //TODO Find an abstraction method
        this.ntSettingsTable.addEntryListener("FocalLength", (table, key, entry, value, flags) -> {
            focalLength = entry.getValue().getDouble();
            System.out.println("Focal Length Value updated to: " + focalLength);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        this.ntSettingsTable.getEntry("FocalLength").setDouble(553.25);


        this.ntSettingsTable.addEntryListener("TargetPixel", (table, key, entry, value, flags) -> {
            targetPixel = entry.getValue().getDouble();
            System.out.println("TargetPixel Value updates to:" + targetPixel);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        this.ntSettingsTable.getEntry("TargetPixel").setDouble(319.5);

        this.ntSettingsTable.addEntryListener("AngleThreshold", (table, key, entry, value, flags) -> {
            angleThreshold = value.getDouble();
            System.out.println(key + " updated to: " + value.getDouble());
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        this.ntSettingsTable.getEntry("AngleThreshold").setDouble(1);

        this.ntSettingsTable.addEntryListener("xSlowDown", (table, key, entry, value, flags) -> {
            xSlowDownOnCompensation = value.getDouble();
            System.out.println(key + " updated to: " + value.getDouble());
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        this.ntSettingsTable.getEntry("xSlowDown").setDouble(.1);

        this.ntSettingsTable.addEntryListener("zTurnSpeed", (table, key, entry, value, flags) -> {
            zTurnSpeedOnCompensation = value.getDouble();
            System.out.println(key + " updated to: " + value.getDouble());
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        this.ntSettingsTable.getEntry("zTurnSpeed").setDouble(.3);

    }

    /*
        returns whether vision sees any targets
    */
    public boolean hasTargets() {
        updateVisionTargetData();
        return targetCount >= 1 && targetCount < 4;
    }


    /*
     * returns that status of tables Ready-ness
     * */
    private boolean isNTReady() {
        return ntSettingsTable != null && ntVisionTable != null;
    }

    /*
     *  VisionYaw Mode, takes into account the change in angle needed to be aligned based on the X center
     *  Adjustments done directly by vision
     * */
    private void visionYawDifferential() {
        int rTargetIndex = -1;
        int lTargetIndex = -1;

        double xSpeed = 0.45;
        double zSpeed = 0.0;
        try {
            //Version 1 of target selection
//
//            if (targetCount == 2) {
//                for (int i = 0; i < targetAngleArray.length; i++) {
//                    if (targetAngleArray[i] < -40 /*&& (targetAreaArray[rTargetIndex] < targetAreaArray[i])*/)
//                        rTargetIndex = i;
//                    if (targetAngleArray[i] > -40 /*&& (targetAreaArray[lTargetIndex] < targetAreaArray[i])*/)
//                        lTargetIndex = i;
//                }
//            } else if (targetCount == 1 && targetAngleArray[0] > -40) {
//                rTargetIndex = -1;
//                lTargetIndex = 0;
//            } else {
//                rTargetIndex = -1;
//                lTargetIndex = -1;
//            }
//

            //Version 2 of target selection

            VisionTarget closest = null;
            VisionTarget targetR;
            VisionTarget targetL;

            if (targetCount == 1) {
                if (targetAngleArray[0] < -40)
                    rTargetIndex = 0;
                else
                    lTargetIndex = 0;

            } else if (targetCount == 2) {
                if (targetAngleArray[0] < -40) {
                    rTargetIndex = 0;
                    lTargetIndex = 1;
                } else {
                    rTargetIndex = 1;
                    lTargetIndex = 0;
                }
            } else if (targetCount > 2) {
                ArrayList<VisionTarget> targetRList = new ArrayList<>();
                ArrayList<VisionTarget> targetLList = new ArrayList<>();
                for (int i = 0; i < targetCount; i++) {
                    if (targetAngleArray[i] < -40)
                        targetRList.add(updateVisionTarget(i));
                    else
                        targetLList.add(updateVisionTarget(i));
                }
                if (targetRList.size() < targetLList.size()) {
                    VisionTarget r = targetRList.get(0);
                    rTargetIndex = r.getElemInArray();

                    for (VisionTarget T : targetLList) {
                        if (T.getX() < r.getX()) {
                            if (closest != null && closest.getX() < T.getX())
                                closest = T;
                            else if (closest == null)
                                closest = T;
                        }
                    }

                    lTargetIndex = closest.getElemInArray();
                } else {
                    VisionTarget l = targetLList.get(0);
                    lTargetIndex = l.getElemInArray();

                    for (VisionTarget T : targetLList) {
                        if (T.getX() > l.getX())
                            if (closest != null && closest.getX() > T.getX())
                                closest = T;
                            else if (closest == null)
                                closest = T;
                    }
                    rTargetIndex = closest.getElemInArray();
                }
            }

            targetR = updateVisionTarget(rTargetIndex);
            targetL = updateVisionTarget(lTargetIndex);

            SmartDashboard.putBoolean("RightTarget", targetR != null);
            SmartDashboard.putBoolean("LeftTarget", targetL != null);

            if (targetR != null || targetL != null) {
                //works?
                double angleRelative;
                if (targetR != null && targetL != null) {
                    double diff = (targetR.getX() + targetL.getX()) / 2.0;
                    SmartDashboard.putNumber("X Actual?", diff);
                    angleRelative = Math.toDegrees(Math.atan((diff - 339.5) / 554.3));
                } else {
                    angleRelative = Math.toDegrees(Math.atan((targetR == null ? targetL.getX() : targetR.getX() - targetPixel)) / focalLength);
                }
                SmartDashboard.putNumber("Difference Angle To Target", angleRelative);
                SmartDashboard.putBoolean("YawDifferentialTargetFound", true);
                //If Above threshold Compensate
                if (!(angleRelative > 28 && angleRelative < 32)) {
                    SmartDashboard.putBoolean("Compensating", true);
                    xSpeed = .5;
                    if (angleRelative > 30)
                        zSpeed = .4;
                    else
                        zSpeed = -.4;
                } else {
                    SmartDashboard.putNumber("Difference Angle To Target", 0);
                    SmartDashboard.putBoolean("Compensating", false);
                }
            } else
                SmartDashboard.putBoolean("YawDifferentialTargetFound", false);


            SmartDashboard.putNumber("XSpeed", xSpeed);
            SmartDashboard.putNumber("ZSpeed", zSpeed);

            roboDrive.arcadeDrive(xSpeed, zSpeed);

            // if (encoderSpeed() == 0 && (System.currentTimeMillis() - timeStartGuidance > 10000))
            //   Robot.mExtender.TogglePiston();

            SmartDashboard.putBoolean("FailedCode", false);
        } catch (Exception e) {
            System.out.println("Something went wrong with Vision");
            SmartDashboard.putBoolean("FailedCode", true);
        }
    }

    /*
        Returns the speed of the encoder in units, don't worry about unit conversion when implementing
    * */
    private double encoderSpeed() {
        return Robot.rightDriveMaster.getSelectedSensorPosition(0);
    }


    /*
     *  Updates the vision data arrays
     * */
    private void updateVisionTargetData() {

        if (ntVisionTable == null)
            return;

        targetCount = (int) ntVisionCount.getDouble(0);
        targetWidthArray = ntVisionWidth.getDoubleArray(DEF_ARR);
        targetAngleArray = ntVisionAngle.getDoubleArray(DEF_ARR);
        targetAreaArray = ntVisionArea.getDoubleArray(DEF_ARR);
        targetXArray = ntVisionX.getDoubleArray(DEF_ARR);
        targetYArray = ntVisionY.getDoubleArray(DEF_ARR);
        targetHeightArray = ntVisionHeight.getDoubleArray(DEF_ARR);
    }

    /*
        Returns a VisionTarget Object based on index i of the target arrays

        Int i = Index of the vision data arrays
     */
    private VisionTarget updateVisionTarget(int i) {
        updateVisionTargetData();
        if (i < 0)
            return null;
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

    //Below is useless testing code, if needed call the vision guidance under mode 0
    private void version1() {
        double goodEnough = 3;
        double expectedXCentered = 213;
        if (hasTargets()) {
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


    public void setGuidanceActive(boolean guidanceActive) {
        if (isGuidanceActive != guidanceActive) {
            if (isGuidanceActive)
                timeStartGuidance = System.currentTimeMillis();
        }
        isGuidanceActive = guidanceActive;
    }
}