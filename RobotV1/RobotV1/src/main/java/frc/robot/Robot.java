package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Scheduler;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystems.CargoClawmp;
import frc.robot.subsystems.Dropper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.PanelPgripper;
import frc.robot.subsystems.Pdropper;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  public static OI oi;
  public static WPI_TalonSRX leftDriveMaster, leftDriveFollower, rightDriveMaster, rightDriveFollower;
  public static DifferentialDrive roboDrive;
  
  public static CargoClawmp cargoClawmp;
  public static Dropper dropper;
  public static Intake intake;
  public static Launcher launcher;
  public static Lift lift;
  public static PanelPgripper panelPgripper;
  public static Pdropper pdropper;

  public static PowerDistributionPanel pdp;
  public static Compressor compressor;
  public static CameraServer cam;

  @Override
  public void robotInit() {
    leftDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveMaster);
    leftDriveMaster.setSafetyEnabled(RobotMap.driveMotorSafety);
    leftDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveFollower);
    leftDriveFollower.setSafetyEnabled(RobotMap.driveMotorSafety);
    rightDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kRightDriveMaster);
    rightDriveMaster.setSafetyEnabled(RobotMap.driveMotorSafety);
    rightDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kRightDriveFollower);
    rightDriveFollower.setSafetyEnabled(RobotMap.driveMotorSafety);
    roboDrive = new DifferentialDrive(new SpeedControllerGroup(leftDriveMaster, leftDriveFollower), new SpeedControllerGroup(rightDriveMaster, rightDriveFollower));
    roboDrive.setSafetyEnabled(RobotMap.driveMotorSafety);
    pdp = new PowerDistributionPanel(RobotMap.CAN.kPDP);
    pdp.clearStickyFaults();
    compressor = new Compressor(RobotMap.CAN.kPCM);

    cargoClawmp = new CargoClawmp();
    dropper = new Dropper();
    intake = new Intake();
    launcher= new Launcher();
    lift = new Lift();
    panelPgripper = new PanelPgripper();
    pdropper = new Pdropper();
    oi = new OI();

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
    roboDrive.arcadeDrive(oi.driver.getY()*0.8, oi.driver.getX()*0.8);
    lift.talon.set(oi.operator.getY()*0.5); // add multipliers better
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testPeriodic() {
  }
}