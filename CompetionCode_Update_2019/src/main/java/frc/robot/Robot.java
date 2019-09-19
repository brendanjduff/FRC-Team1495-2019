package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.HatchPanelAuto;
import frc.robot.subsystems.BackClimber;
import frc.robot.subsystems.ClimberWheels;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.FrontClimber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeExtender;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.ManipulatorExtender;
import frc.robot.vision.RobotVision;

public class Robot extends TimedRobot {
  public static DriveBase driveBase;

  public static Elevator elevator;
  public static Manipulator manipulator;
  public static ManipulatorExtender mExtender;
  public static Intake intake;
  public static IntakeExtender iExtender;
  public static Launcher launcher;
  public static FrontClimber fClimber;
  public static BackClimber bClimber;
  public static ClimberWheels cWheels;

  public static OI oi;
  public static PowerDistributionPanel pdp;
  public static Compressor compressor;
  public static NavX nav;
  public static RobotVision vision;
  public static NetworkTableInstance ntInst;
  public static CameraServer cam;

  public static boolean defenseMode = false;

  @Override
  public void robotInit() {
    driveBase = new DriveBase();

    elevator = new Elevator();
    manipulator = new Manipulator();
    mExtender = new ManipulatorExtender();
    intake = new Intake();
    iExtender = new IntakeExtender();
    launcher = new Launcher();
    fClimber = new FrontClimber();
    bClimber = new BackClimber();
    cWheels = new ClimberWheels();
    nav = new NavX();
    vision = RobotVision.getInstance();
    ntInst = NetworkTableInstance.getDefault();
    ntInst.setServerTeam(1495, 1735);
    ntInst.startServer();

    vision.setNtSettingsTable(ntInst.getTable("PiSettings"));
    vision.setNtVisionTable(ntInst.getTable("PiVision"));

    cam = CameraServer.getInstance();

    cam.startAutomaticCapture();

    
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
    vision.runPeriodicUpdate();
    driveStateUpdate();
    SmartDashboard.putNumber("Elevator Position: ", Robot.elevator.getPosition());
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    vision.runPeriodicUpdate();
    driveStateUpdate();
    SmartDashboard.putNumber("Elevator Position: ", Robot.elevator.getPosition());
    SmartDashboard.putNumber("Pitch", nav.getPitchDeg());
    SmartDashboard.putNumber("Roll", nav.getRoll());
    SmartDashboard.putNumber("Yaw", nav.getYawDeg());
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }

  private void driveStateUpdate()
  {
      if(oi.driver.getAButton()){
      vision.runVisionGuidanceUpdate(1);
      vision.setGuidanceActive(true);
      }
      else{
      driveBase.drive(oi);
      vision.setGuidanceActive(false);
      }
  }
}