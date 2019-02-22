package frc.robot;

public class RobotMap {
  public static class CAN {
    public static final int kPDP = 0;
    public static final int kPCM = 0;
    //Talons
    public static final int kLeftDriveMaster = 2;
    public static final int kLeftDriveFollower = 1;
    public static final int kRightDriveMaster = 3;
    public static final int kRightDriveFollower = 4;
    public static final int kElevator = 5;
    public static final int kFrontClimber = 7;
    public static final int kBackClimber = 6;
    //Victors
    public static final int kIntake = 11;
    public static final int kLauncher = 12;
  }

  public static class PCM {
    public static final int kPdropperExtend = 4;
    public static final int kPdropperRetract = 5;
    public static final int kPanelPgripperExtend = 6;
    public static final int kPanelPgripperRetract =7;
    public static final int kDropperExtend = 0;
    public static final int kDropperRetract = 1;
    public static final int kCargoClawmpExtend = 2;
    public static final int kCargoClawmpRetract = 3;
  }

  public static class DIO {
    public static final int kElevatorLowerBound = 0;
    public static final int kElevatorUpperBound = 1;
  }

  public static class ControllerPort {
    public static final int kDriver = 0;
    public static final int kOperator = 1;
    public static final int kTestCode = 3;
  }

  public static final double kWheelDiameter = 6;
  public static final double kPulsesPerRevolution = 4096;//choose new number
  public static final boolean driveMotorSafety = false;
}