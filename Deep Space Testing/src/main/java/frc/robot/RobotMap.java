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
    public static final int kIntake = 8;
    // Victors
    public static final int kLauncherRight = 11;
    public static final int kLauncherLeft = 12;
  }

  public static class PCM { // The forward value for each piston should be extended
    public static final int kMExtenderForward = 0;
    public static final int kMExtenderReverse = 1;
    public static final int kManipulatorForward = 2;
    public static final int kManipulatorReverse = 3;
    public static final int kIExtenderForward = 7;
    public static final int kIExtenderReverse = 6;
  }

  public static class DIO {
    public static final int kElevatorLowerBound = 0;
    public static final int kElevatorUpperBound = 1;
    public static final int kFClimberLowerBound = 2;
    public static final int kFClimberUpperBound = 3; 
    public static final int kBClimberLowerBound = 4; 
    public static final int kBClimberUpperBound = 5;
  }

  public static class ControllerPort {
    public static final int kDriver = 0;
    public static final int kOperator = 1;
    public static final int kTest1 = 4;
    public static final int kTest2 = 5;
  }

  public static class Motors {
    public static final double kSpeedMultiplier = 0.8;
    public static final double kSlowSpeedMultiplier = 0.5;

    public static final double kRotationMultiplier = 0.75;
    public static final double kSlowRotationMultiplier = 0.45;

    public static final double kIntakeSpeed = 0.5;

    public static final double kLauncherSpeedForward = 1;
    public static final double kLauncherHoldSpeed = .2;

    public static final double kManualElevatorTestSpeed = .5;
  }

  public static class PID { //mess with later
    public static final double kPulsesPerRevolution = 4096;
    public static final int kTimeoutMs = 0;
    public static class Elevator {
      public static final double kP = 0.; // uv
      public static final double kI = 0; // uv
      public static final double kD = 0; // uv
      public static final double kF = 0; // uv
      public static final int kMaxAcceleration = 100; // uv
      public static final int kCruiseVelocity = 100; // uv
      public static final int kIZone = 0; // uv
      public static final double kPeakOutput = 100; // uv

      public static final int kStartPosition = 0; //uv
      public static final int kHatchPanelLevel1 = 0; // uv
      public static final int kHatchPanelLevel2 = 0; // uv
      public static final int kCargoShip = 0; // uv
      public static final int kCargoRocketLevel1 = 0; // uv
      public static final int kCargoRocketLevel2 = 0; // uv
      public static final int kCargoIntake = 0; // uv
    }
  }

  public static final boolean driveMotorSafety = false;
}