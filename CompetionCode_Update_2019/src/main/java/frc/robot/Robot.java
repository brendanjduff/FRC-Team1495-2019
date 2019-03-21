package frc.robot;

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
    driveBase.drive(oi);
    SmartDashboard.putNumber("Elevator Position: ", Robot.elevator.getPosition());
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    driveBase.drive(oi);
    SmartDashboard.putNumber("Elevator Position: ", Robot.elevator.getPosition());
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}