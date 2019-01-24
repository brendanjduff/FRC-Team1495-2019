package frc.robot;

public class RobotMap {
  public static class CAN {
    // PDP [0] | PCM [0] | TalonSRX [1,10] | VictorSPX [11,14]
    public static int kPDP = 0;
    public static int kPCM = 0;
    public static int kLeftDriveMaster = 2;
    public static int kLeftDriveFollower = 3;
    public static int kRightDriveMaster = 1;
    public static int kRightDriveFollower = 4;
  }

  public static class PCM {
    public static int kHatchPanelManipulatorExtend = 0;
    public static int kHatchPanelManipulatorRetract = 1;
  }

  public static class ControllerPort {
    public static int kJoystick = 0;
  }
  
  public static class Multipliers {
    public static double kDriveMagnitude = .8;
    public static double kDriveRotation = .8;
  }
}