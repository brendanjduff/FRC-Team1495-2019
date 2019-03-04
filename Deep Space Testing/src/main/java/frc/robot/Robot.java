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
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Manipulator;


public class Robot extends TimedRobot {
  public static WPI_TalonSRX leftDriveMaster, leftDriveFollower, rightDriveMaster, rightDriveFollower;
  public static DifferentialDrive roboDrive;
  public static boolean slowMode = false;

  //public static Launcher launcher;
  public static Intake intake;
  ///public static Climber climber;
  public static Manipulator manipulator;
  public static Elevator elevator;

  public static OI oi;
  public static PowerDistributionPanel pdp;
  public static Compressor compressor;
  public static CameraServer cam;

  @Override
  public void robotInit() {
    leftDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveMaster);
    leftDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveFollower);
    rightDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kRightDriveMaster);
    rightDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kRightDriveFollower);
    roboDrive = new DifferentialDrive(new SpeedControllerGroup(leftDriveMaster, leftDriveFollower), new SpeedControllerGroup(rightDriveMaster, rightDriveFollower));

    //launcher = new Launcher();
    intake = new Intake();
    //climber = new Climber();
    manipulator = new Manipulator();
    elevator = new Elevator();

    oi = new OI();
    pdp = new PowerDistributionPanel(RobotMap.CAN.kPDP);
    pdp.clearStickyFaults();
    compressor = new Compressor(RobotMap.CAN.kPCM);
    //cam = CameraServer.getInstance();
    //cam.startAutomaticCapture("cam1", 0);
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
    //Test
    /*
    //XboxController
    if(!slowMode)
      roboDrive.arcadeDrive(-oi.driver.getY(Hand.kLeft)*RobotMap.Motors.kSpeedMultiplier, oi.driver.getX(Hand.kRight)*RobotMap.Motors.kRotationMultiplier);
    else
      roboDrive.arcadeDrive(-oi.driver.getY(Hand.kLeft)*RobotMap.Motors.kSlowSpeedMultiplier, oi.driver.getX(Hand.kRight)*RobotMap.Motors.kSlowRotationMultiplier);
    */
    //XboxController Alternate
    /*double triggerSum = oi.driver.getTriggerAxis(Hand.kRight) - oi.driver.getTriggerAxis(Hand.kLeft);
    if(!slowMode)
      roboDrive.arcadeDrive(-triggerSum*RobotMap.Motors.kSpeedMultiplier, oi.driver.getX(Hand.kLeft)*RobotMap.Motors.kRotationMultiplier);
    else
      roboDrive.arcadeDrive(-triggerSum*RobotMap.Motors.kSlowSpeedMultiplier, oi.driver.getX(Hand.kLeft)*RobotMap.Motors.kSlowRotationMultiplier);
    /*
    //Joystick
    if(!slowMode)
      roboDrive.arcadeDrive(-oi.driver.getY()*RobotMap.Motors.kSpeedMultiplier, oi.driver.getX()*RobotMap.Motors.kRotationMultiplier);
    else
      roboDrive.arcadeDrive(-oi.driver.getY()*RobotMap.Motors.kSlowSpeedMultiplier, oi.driver.getX()*RobotMap.Motors.kSlowRotationMultiplier);
      */
      
      if(!slowMode)
      roboDrive.arcadeDrive(-oi.boeing.getY()*RobotMap.Motors.kSpeedMultiplier, oi.boeing.getX()*RobotMap.Motors.kRotationMultiplier);
    else
      roboDrive.arcadeDrive(-oi.boeing.getY()*RobotMap.Motors.kSlowSpeedMultiplier, oi.boeing.getX()*RobotMap.Motors.kSlowRotationMultiplier);
      

    //SmartDashboard.putNumber("Elevator Position", Robot.elevator.getPosition());
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}