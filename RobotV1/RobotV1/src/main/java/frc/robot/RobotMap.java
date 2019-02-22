package frc.robot;

public class RobotMap {
  public static class CAN {
    public static final int kPDP = 0;
    public static final int kPCM = 0;
    public static final int kLeftDriveMaster = 2;
    public static final int kLeftDriveFollower = 3;
    public static final int kRightDriveMaster = 1;
    public static final int kRightDriveFollower = 4;
    public static final int kIntake = 5;
    public static final int kLeftLauncher = 6;
    public static final int kRightLauncher = 7;
    public static final int kLift = 8;
  }

  public static class PCM {
    public static final int kDropperExtend = 2;
    public static final int kDropperRetract = 3;
    public static final int kCargoClawmpExtend = 0;
    public static final int kCargoClawmpRetract = 1;
    public static final int kPdropperExtend = 4;
    public static final int kPdropperRetract = 5;
    public static final int kPanelPgripperExtend = 6;
    public static final int kPanelPgripperRetract = 7;
  }

  public static class ControllerPort {
    public static final int kDriver = 0;
    public static final int kOperator = 1;
  }

  public static final boolean driveMotorSafety = false;
}