package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DefenseMode;
import frc.robot.commands.IExtenderPiston;
import frc.robot.commands.LoadCargoFromGround;
import frc.robot.commands.MExtenderPiston;
import frc.robot.commands.MGripperPiston;
import frc.robot.commands.MoveElevatorToPosition;
import frc.robot.commands.ReverseMode;
import frc.robot.commands.RunBackClimber;
import frc.robot.commands.RunElevator;
import frc.robot.commands.RunFrontClimber;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunLauncher;
import frc.robot.commands.RunLauncherTimed;
import frc.robot.commands.SlowMode;
import frc.robot.commands.ZeroElevator;
import edu.wpi.first.wpilibj.Joystick;

public class OI {

  public XboxController driver = new XboxController(RobotMap.ControllerPort.kDriver);
  public XboxController operator = new XboxController(RobotMap.ControllerPort.kOperator);

  public Button loadCargoFromGround = new JoystickButton(driver, 1);
  public Button scoreCargo = new JoystickButton(driver, 2);
  public Button slowMode = new JoystickButton(driver, 5);
  public Button defenseMode = new JoystickButton(driver, 7);
  public Button reverseMode = new JoystickButton(driver, 8);
  public Button toggleMExtender = new JoystickButton(operator, 1);
  public Button toggleMGripper = new JoystickButton(operator, 2);
 
  // Test Commands
  public Joystick test1 = new Joystick(RobotMap.ControllerPort.kTest1);
  public Button intakeMotorTestForward = new JoystickButton(test1, 1);
  public Button intakeMotorTestReverse = new JoystickButton(test1, 2);
  public Button intakePistonTestForward = new JoystickButton(test1, 7);
  public Button intakePistonTestReverse = new JoystickButton(test1, 8);
  public Button manipulatorDropperTestForward = new JoystickButton(test1, 3);
  public Button manipulatorDropperTestReverse = new JoystickButton(test1, 4);
  public Button manipulatorGripperTestForward = new JoystickButton(test1, 5);
  public Button manipulatorGripperTestReverse = new JoystickButton(test1, 6);
  public Button launcherMotorTestForward = new JoystickButton(test1, 9);
  public Button launcherMotorTestReverse = new JoystickButton(test1, 10);
  public Button launcherPistonTestForward = new JoystickButton(test1, 11);
  public Button launcherPistonTestReverse = new JoystickButton(test1, 12);

  public Joystick test2 = new Joystick(RobotMap.ControllerPort.kTest2);
  public Button elevatorTestForward = new JoystickButton(test2, 1);
  public Button elevatorTestReverse = new JoystickButton(test2, 2);
  public Button setElevatorPositionZero = new JoystickButton(test2, 3);
  public Button fClimberForward = new JoystickButton(test2, 7);
  public Button fClimberReverse = new JoystickButton(test2, 8);
  public Button bClimberForward = new JoystickButton(test2, 9);
  public Button bClimberReverse = new JoystickButton(test2, 10);
  public Button climbersForward = new JoystickButton(test2, 11);
  public Button climbersReverse = new JoystickButton(test2, 12);
  public Button moveElevator = new JoystickButton(test2, 4);

  public OI() {
    loadCargoFromGround.whileHeld(new LoadCargoFromGround());
    loadCargoFromGround.whenPressed(new RunLauncher(true));
    slowMode.whenPressed(new SlowMode(true));
    //slowMode.whileHeld(new SlowMode(false));
    defenseMode.whenPressed(new DefenseMode(true));
    reverseMode.whenPressed(new ReverseMode(true));
    toggleMExtender.whenPressed(new MExtenderPiston());
    toggleMGripper.whenPressed(new MGripperPiston());
    scoreCargo.whenPressed(new RunLauncherTimed(false, 2));

    // Test Commands UpdatesRequired
    intakeMotorTestForward.whileHeld(new RunIntake(true));
    intakeMotorTestReverse.whileHeld(new RunIntake(false));
    intakePistonTestForward.whenPressed(new IExtenderPiston(Value.kForward));
    intakePistonTestReverse.whenPressed(new IExtenderPiston(Value.kReverse));
    manipulatorDropperTestForward.whenPressed(new MExtenderPiston(Value.kForward));
    manipulatorDropperTestReverse.whenPressed(new MExtenderPiston(Value.kReverse));
    manipulatorGripperTestForward.whenPressed(new MGripperPiston(Value.kForward));
    manipulatorGripperTestReverse.whenPressed(new MGripperPiston(Value.kReverse));
    launcherMotorTestForward.whileHeld(new RunLauncher(true));
    launcherMotorTestReverse.whileHeld(new RunLauncher(false));

    elevatorTestForward.whileHeld(new RunElevator(true));
    elevatorTestReverse.whileHeld(new RunElevator(false));
    setElevatorPositionZero.whenPressed(new ZeroElevator());
    fClimberForward.whileHeld(new RunFrontClimber(true));
    fClimberReverse.whileHeld(new RunFrontClimber(false));
    bClimberForward.whileHeld(new RunBackClimber(true));
    bClimberReverse.whileHeld(new RunBackClimber(false));
    climbersForward.whileHeld(new RunFrontClimber(false));
    climbersForward.whileHeld(new RunBackClimber(true));
    climbersReverse.whileHeld(new RunFrontClimber(true));
    climbersReverse.whileHeld(new RunBackClimber(false));
    moveElevator.whenPressed(new MoveElevatorToPosition(100000));
  }
}

/*
 * - Joystick Button Numbers - 1 : Trigger 2 : ThumbButton 3-6 : Top of
 * controller 7-12 : Base of controller
 * 
 * - Xbox Controller Button Numbers - 1 : A 2 : B 3 : X 4 : Y 5 : Left Bumper 6
 * : Right Bumper 7 : Share 8 : Options 9 : Left Stick 10 : Right Stick
 */