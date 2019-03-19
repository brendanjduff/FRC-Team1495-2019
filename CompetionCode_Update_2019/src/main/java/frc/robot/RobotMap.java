package frc.robot;

public class RobotMap {

  public static class CAN {
    public static final int kPDP = 0;
    public static final int kPCM = 0;
    // Talons
    public static final int kLeftDriveMaster = 1;
    public static final int kLeftDriveFollower = 2;
    public static final int kRightDriveMaster = 3;
    public static final int kRightDriveFollower = 4;
    public static final int kElevator = 5;
    public static final int kFrontClimber = 7;
    public static final int kBackClimber = 6;
    public static final int kClimberWheels = 8;
    // Victors
    public static final int kLauncherRight = 11;
    public static final int kLauncherLeft = 12;
    public static final int kIntake = 13;
  }

  public static class PCM { // The forward value for each piston should be extended
    public static final int kMExtenderForward = 4;
    public static final int kMExtenderReverse = 5;
    public static final int kManipulatorForward = 0;
    public static final int kManipulatorReverse = 1;
    public static final int kIExtenderForward = 6;
    public static final int kIExtenderReverse = 7;
  }

  public static class DIO {
    public static final int kElevatorLowerBound = 9;
    public static final int kElevatorUpperBound = 6;
    public static final int kFClimberLowerBound = 2; 
    public static final int kFClimberUpperBound = 1;
    public static final int kBClimberLowerBound = 0; 
    public static final int kBClimberUpperBound = 3;
  }

  public static class ControllerPort {
    public static final int kDriver = 0;
    public static final int kOperator = 1;
    public static final int kClimber = 2;
  }

  public static class Motors {
    public static final double kSpeedMultiplier = 0.8;
    public static final double kSlowSpeedMultiplier = 0.6;
    public static final double kRotationMultiplier = 0.65;
    public static final double kSlowRotationMultiplier = 0.45;

    public static final double kIntakeSpeed = 0.6;
    public static final double kLauncherSpeedForward = 1;
    public static final double kLauncherHoldSpeed = 0.25;

    public static final double kClimberWheelsSpeed = 0.8;
    public static final double kClimberFastSpeed = 1;
    public static final double kClimberSlowSpeed = 0.85;
    public static final double kClimberHoldSpeed = 0.15;

    public static final double kManualElevatorSpeed = 1;
  }

  public class ElevatorPID {
    public static final int kForwardSoftLimitThreshold = 200000; //uv
    public static final int kReverseSoftLimitThreshold = 1000000; //uv
    public static final int kPositionTolerance = 30000; //uv

    public static final int kBottomPosition = 0; //uv
    public static final int kHatchPanelLevel2 = 0; // uv
    public static final int kCargoShip = 0; // uv
    public static final int kCargoRocketLevel1 = 0; // uv
  }

  public static final boolean driveMotorSafety = false;
}