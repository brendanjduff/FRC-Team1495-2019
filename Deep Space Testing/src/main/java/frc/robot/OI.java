package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.IntakeCargo;
import frc.robot.commands.IntakeMotor;
import frc.robot.commands.IntakePiston;
import frc.robot.commands.ManipulatorDropper;
import frc.robot.commands.ManipulatorGripper;
import frc.robot.commands.RunElevator;
import frc.robot.commands.SlowMode;
import frc.robot.commands.LauncherPiston;
import frc.robot.commands.LauncherMotor;


public class OI {
  /*
  public Joystick driver = new Joystick(RobotMap.ControllerPort.kDriver);
  public Button intakeCargo = new JoystickButton(driver, 1);
  public Button slowMode = new JoystickButton(driver, 9);
  */
  /*
  public XboxController driver = new XboxController(RobotMap.ControllerPort.kDriver);
  public Button intakeCargo = new JoystickButton(driver, 1);
  public Button slowMode = new JoystickButton(driver, 2);

  public Joystick operator = new Joystick(RobotMap.ControllerPort.kOperator);
*/
  /*
  public XboxController operator = new XboxController(RobotMap.ControllerPort.kOperator);
  */
/*
  //Test Commands
  public Joystick test1 = new Joystick(RobotMap.ControllerPort.kTest1);
  public Button intakeMotorTestForward = new JoystickButton(test1, 0); //uv
  public Button intakeMotorTestReverse = new JoystickButton(test1, 0); //uv
  public Button intakePistonTestForward = new JoystickButton(test1, 0); //uv
  public Button intakePistonTestReverse = new JoystickButton(test1, 0); //uv
  public Button manipulatorDropperTestForward = new JoystickButton(test1, 0); //uv
  public Button manipulatorDropperTestReverse = new JoystickButton(test1, 0); //uv
  public Button manipulatorGripperTestForward = new JoystickButton(test1, 0); //uv
  public Button manipulatorGripperTestReverse = new JoystickButton(test1, 0); //uv
  public Button launcherMotorTestForward = new JoystickButton(test1, 0); //uv
  public Button launcherMotorTestReverse = new JoystickButton(test1, 0); //uv
  public Button launcherPistonTestForward = new JoystickButton(test1, 0); //uv
  public Button launcherPistonTestReverse = new JoystickButton(test1, 0); //uv

  public Joystick test2 = new Joystick(RobotMap.ControllerPort.kTest2);
  public Button elevatorTestForward = new JoystickButton(test2, 0); //uv
  public Button elevatorTestReverse = new JoystickButton(test2, 0); //uv
*/

  public Joystick boeing = new Joystick(0);
  public Button intakeCargo = new JoystickButton(boeing, 1);
  public Button slowMode = new JoystickButton(boeing, 2);
  public Button manipulatorDropperTestForward = new JoystickButton(boeing, 6);
  public Button manipulatorDropperTestReverse = new JoystickButton(boeing, 4);
  public Button manipulatorGripperTestForward = new JoystickButton(boeing, 5);
  public Button manipulatorGripperTestReverse = new JoystickButton(boeing, 3);
  public Button intakePistonTestForward = new JoystickButton(boeing, 7); //uv
  public Button intakePistonTestReverse = new JoystickButton(boeing, 8); //uv
  public Button intakeMotorTestForward = new JoystickButton(boeing, 9); //uv
  public Button intakeMotorTestReverse = new JoystickButton(boeing, 10); //uv
  public Button elevatorTestForward = new JoystickButton(boeing, 11);
  public Button elevatorTestReverse = new JoystickButton(boeing, 12);

  public OI() {
    //driver
    intakeCargo.whileHeld(new IntakeCargo());
    slowMode.whileHeld(new SlowMode());
    //slowMode.whenPressed(new ToggleSlowMode());

    //operator

    //Test Commands
    intakeMotorTestForward.whileHeld(new IntakeMotor(true));
    intakeMotorTestReverse.whileHeld(new IntakeMotor(false));
    intakePistonTestForward.whenPressed(new IntakePiston(Value.kForward));
    intakePistonTestReverse.whenPressed(new IntakePiston(Value.kReverse));
    manipulatorDropperTestForward.whenPressed(new ManipulatorDropper(Value.kForward));
    manipulatorDropperTestReverse.whenPressed(new ManipulatorDropper(Value.kReverse));
    manipulatorGripperTestForward.whenPressed(new ManipulatorGripper(Value.kForward));
    manipulatorGripperTestReverse.whenPressed(new ManipulatorGripper(Value.kReverse));
    //launcherMotorTestForward.whileHeld(new LauncherMotor(true));
    //launcherMotorTestReverse.whileHeld(new LauncherMotor(false));
    //launcherPistonTestForward.whenPressed(new LauncherPiston(Value.kForward));
    //launcherPistonTestReverse.whenPressed(new LauncherPiston(Value.kReverse));

    elevatorTestForward.whileHeld(new RunElevator(true));
    elevatorTestReverse.whileHeld(new RunElevator(false));
  }
}

/*
 - Joystick Button Numbers - 
1     : Trigger       
2     : ThumbButton
3-6   : Top of controller
7-12  : Base of controller

 - Xbox Controller Button Numbers - 
1 : A             2 : B
3 : X             4 : Y
5 : Left Bumper   6 : Right Bumper
7 : Share         8 : Options
9 : Left Stick   10 : Right Stick
*/