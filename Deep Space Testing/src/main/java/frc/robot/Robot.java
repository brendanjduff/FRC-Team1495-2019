package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.sensors.Gyro;
import frc.robot.subsystems.DoubleMotorTestSubsystem;
import frc.robot.subsystems.DoubleSolenoidTest;
import frc.robot.subsystems.SingleSolenoidTestSystem;
import frc.robot.subsystems.SingleMotorTestSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  public static WPI_TalonSRX leftDriveMaster, leftDriveFollower, rightDriveMaster, rightDriveFollower;
  public static DifferentialDrive roboDrive;

  public static SingleMotorTestSubsystem singleMotor;
  public static DoubleMotorTestSubsystem doubleMotor;
  public static SingleSolenoidTestSystem hatchPanelManipulator;
  public static DoubleSolenoidTest doubleSolenoid;

  public static Gyro gyro;
  public static OI oi;
  public PowerDistributionPanel pdp;
  public static Compressor compressor;
  public static CameraServer cam;

  public static DigitalInput limitSwitch;

  @Override
  public void robotInit() {
    leftDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveMaster);
    leftDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveFollower);
    rightDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kRightDriveMaster);
    rightDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kRightDriveFollower);
    roboDrive = new DifferentialDrive(new SpeedControllerGroup(leftDriveMaster, leftDriveFollower), new SpeedControllerGroup(rightDriveMaster, rightDriveFollower));

    hatchPanelManipulator = new SingleSolenoidTestSystem();
    singleMotor = new SingleMotorTestSubsystem(RobotMap.CAN.kSingle);
    doubleMotor = new DoubleMotorTestSubsystem(RobotMap.CAN.kDouble1, RobotMap.CAN.kDouble2);
    doubleSolenoid = new DoubleSolenoidTest(hatchPanelManipulator.ds);

    limitSwitch = new DigitalInput(0);

    gyro = new Gyro();
    oi = new OI();
    pdp = new PowerDistributionPanel(RobotMap.CAN.kPDP);
    pdp.clearStickyFaults();
    compressor = new Compressor(RobotMap.CAN.kPCM);
    cam = CameraServer.getInstance();
    cam.startAutomaticCapture("cam1", 0);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    roboDrive.arcadeDrive(oi.joystick.getY()*RobotMap.Multipliers.kDriveMagnitude, oi.joystick.getX()*RobotMap.Multipliers.kDriveRotation);
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}