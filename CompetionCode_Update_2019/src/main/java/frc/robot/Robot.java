package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.subsystems.*;
import frc.robot.commands.HatchPanelAuto;
import frc.robot.vision.RobotVision;

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
  public static ClimberWheels cWheels;
  public static RobotVision vision;
  public static NetworkTableInstance ntinst;
  public static Gyro gyro;


  public static OI oi;
  public static PowerDistributionPanel pdp;
  public static Compressor compressor;

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
    cWheels = new ClimberWheels();
    vision = RobotVision.getInstance();
    ntinst = NetworkTableInstance.getDefault();
    gyro = new Gyro();

    vision.setNtVisionTable(ntinst.getTable("PiVision"));
    vision.setNtSettingsTable(ntinst.getTable("VisionSettings"));

    oi = new OI();
    pdp = new PowerDistributionPanel(RobotMap.CAN.kPDP);
    pdp.clearStickyFaults();
    compressor = new Compressor(RobotMap.CAN.kPCM);


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
    Scheduler.getInstance().add(new HatchPanelAuto());
  }

  @Override
  public void autonomousPeriodic() {

   driverJoystickUpdate();

    SmartDashboard.putNumber("Elevator Position", Robot.elevator.getPosition());
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {

    SmartDashboard.putBoolean("Is Auto active", OI.driver.getAButton());
    vision.runPeriodicUpdate();
    if(OI.driver.getAButton())
    {
      vision.runVisionGuidanceUpdate(0);
    }
    else
    driverJoystickUpdate();

    SmartDashboard.putBoolean("Vision Status", vision.isReady());
    SmartDashboard.putNumber("Elevator Position", Robot.elevator.getPosition());
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
    private void driverJoystickUpdate()
    {
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

    }
}
