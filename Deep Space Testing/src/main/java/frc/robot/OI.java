package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.SingleMotorTest;
import frc.robot.commands.DoubleMotorTest;
import frc.robot.commands.ModulateHatchPanelManipulator;

public class OI {
  public Joystick joystick = new Joystick(RobotMap.ControllerPort.kJoystick);

  public Button extend = new JoystickButton(joystick, 3);
  public Button retract = new JoystickButton(joystick, 5);
  public Button singleForward = new JoystickButton(joystick, 7);
  public Button singleReverse = new JoystickButton(joystick, 8);
  public Button doubleForward = new JoystickButton(joystick, 11);
  public Button doubleReverse = new JoystickButton(joystick, 12);

  public OI() {
    extend.whenPressed(new ModulateHatchPanelManipulator(true));
    retract.whenPressed(new ModulateHatchPanelManipulator(false));
    singleForward.whileHeld(new SingleMotorTest(RobotMap.kIntakeSpeed));
    singleReverse.whileHeld(new SingleMotorTest(-RobotMap.kIntakeSpeed));
    doubleForward.whileHeld(new DoubleMotorTest(RobotMap.kIntakeSpeed));
    doubleReverse.whileHeld(new DoubleMotorTest(-RobotMap.kIntakeSpeed));
  }
}
