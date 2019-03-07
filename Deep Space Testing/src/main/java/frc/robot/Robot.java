package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.ManipulatorExtender;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeExtender;
import frc.robot.subsystems.BackClimber;
import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.FrontClimber;

public class Robot extends TimedRobot {
  public static WPI_TalonSRX leftDriveMaster, leftDriveFollower, rightDriveMaster, rightDriveFollower;
  public static DifferentialDrive roboDrive;
  public static boolean slowMode = false;
  public static boolean defenseMode = true;
  public static boolean reverseMode = false;

  public static Elevator elevator;
  public static Manipulator manipulator;
  public static ManipulatorExtender mExtender;
  public static Intake intake;
  public static IntakeExtender iExtender;
  public static Launcher launcher;
  public static FrontClimber fClimber;
  public static BackClimber bClimber;

  public static OI oi;
  public static PowerDistributionPanel pdp;
  public static Compressor compressor;
  public static CameraServer cam;
  public static ReactingLeds leds= new ReactingLeds();

  @Override
  public void robotInit() {
    leftDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveMaster);
    leftDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveFollower);
    rightDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kRightDriveMaster);
    rightDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kRightDriveFollower);
    roboDrive = new DifferentialDrive(new SpeedControllerGroup(leftDriveMaster, leftDriveFollower),
        new SpeedControllerGroup(rightDriveMaster, rightDriveFollower));

    elevator = new Elevator();
    manipulator = new Manipulator();
    mExtender = new ManipulatorExtender();
    intake = new Intake();
    iExtender = new IntakeExtender();
    launcher = new Launcher();
    fClimber = new FrontClimber();
    bClimber = new BackClimber();

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
    // grip game pieces that are within robot. Set all solenoids to correct value.
    // Disable defense mode.
  }

  @Override
  public void autonomousPeriodic() {
    /*
    //Xbox Controller
        if (!slowMode && !reverseMode)
      roboDrive.arcadeDrive(-oi.driver.getY(Hand.kRight) * RobotMap.Motors.kSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kRotationMultiplier);
    else if(!slowMode && reverseMode)
      roboDrive.arcadeDrive(oi.driver.getY(Hand.kRight) * RobotMap.Motors.kSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kRotationMultiplier);
    else if(slowMode && !reverseMode)
      roboDrive.arcadeDrive(-oi.driver.getY(Hand.kRight) * RobotMap.Motors.kSlowSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kSlowRotationMultiplier);
    else if(slowMode && reverseMode)
      roboDrive.arcadeDrive(oi.driver.getY(Hand.kRight) * RobotMap.Motors.kSlowSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kSlowRotationMultiplier);
    */

    // XboxController Alternate
    double triggerSum = oi.driver.getTriggerAxis(Hand.kRight) - oi.driver.getTriggerAxis(Hand.kLeft);
    if (!slowMode && !reverseMode)
      roboDrive.arcadeDrive(-triggerSum * RobotMap.Motors.kSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kRotationMultiplier);
    else if(!slowMode && reverseMode)
      roboDrive.arcadeDrive(triggerSum * RobotMap.Motors.kSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kRotationMultiplier);
    else if(slowMode && !reverseMode)
      roboDrive.arcadeDrive(-triggerSum * RobotMap.Motors.kSlowSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kSlowRotationMultiplier);
    else if(slowMode && reverseMode)
      roboDrive.arcadeDrive(triggerSum * RobotMap.Motors.kSlowSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kSlowRotationMultiplier);

    SmartDashboard.putNumber("Elevator Position", Robot.elevator.getPosition());
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
        /*
    //Xbox Controller
        if (!slowMode && !reverseMode)
      roboDrive.arcadeDrive(-oi.driver.getY(Hand.kRight) * RobotMap.Motors.kSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kRotationMultiplier);
    else if(!slowMode && reverseMode)
      roboDrive.arcadeDrive(oi.driver.getY(Hand.kRight) * RobotMap.Motors.kSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kRotationMultiplier);
    else if(slowMode && !reverseMode)
      roboDrive.arcadeDrive(-oi.driver.getY(Hand.kRight) * RobotMap.Motors.kSlowSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kSlowRotationMultiplier);
    else if(slowMode && reverseMode)
      roboDrive.arcadeDrive(oi.driver.getY(Hand.kRight) * RobotMap.Motors.kSlowSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kSlowRotationMultiplier);
    */

    // XboxController Alternate
    double triggerSum = oi.driver.getTriggerAxis(Hand.kRight) - oi.driver.getTriggerAxis(Hand.kLeft);
    if (!slowMode && !reverseMode)
      roboDrive.arcadeDrive(-triggerSum * RobotMap.Motors.kSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kRotationMultiplier);
    else if(!slowMode && reverseMode)
      roboDrive.arcadeDrive(triggerSum * RobotMap.Motors.kSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kRotationMultiplier);
    else if(slowMode && !reverseMode)
      roboDrive.arcadeDrive(-triggerSum * RobotMap.Motors.kSlowSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kSlowRotationMultiplier);
    else if(slowMode && reverseMode)
      roboDrive.arcadeDrive(triggerSum * RobotMap.Motors.kSlowSpeedMultiplier,
          oi.driver.getX(Hand.kLeft) * RobotMap.Motors.kSlowRotationMultiplier);

    SmartDashboard.putNumber("Elevator Position", Robot.elevator.getPosition());
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}