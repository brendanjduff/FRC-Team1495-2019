package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.SingleMotorTest;
import frc.robot.commands.DoubleMotorTest;
import frc.robot.commands.DoubleSolenoidTestRun;
import frc.robot.commands.SingleSolenoidTestRun;
import frc.robot.commands.LimitSwitchPneumaticsTest;

public class OI {
  public Joystick joystick = new Joystick(RobotMap.ControllerPort.kJoystick);

  public Button extend = new JoystickButton(joystick, 3);
  public Button retract = new JoystickButton(joystick, 5);
  public Button extendBoth = new JoystickButton(joystick, 4);
  public Button retractBoth = new JoystickButton(joystick, 6);
  public Button singleForward = new JoystickButton(joystick, 7);
  public Button singleReverse = new JoystickButton(joystick, 8);
  public Button limitMnoo = new JoystickButton(joystick,9);
  public Button doubleForward = new JoystickButton(joystick, 11);
  public Button doubleReverse = new JoystickButton(joystick, 12);

  public OI() {
    extend.whenPressed(new SingleSolenoidTestRun(true));
    retract.whenPressed(new SingleSolenoidTestRun(false));
    extendBoth.whenPressed(new DoubleSolenoidTestRun(true));
    retractBoth.whenPressed(new DoubleSolenoidTestRun(false));
    singleForward.whileHeld(new SingleMotorTest(RobotMap.kIntakeSpeed));
    singleReverse.whileHeld(new SingleMotorTest(-RobotMap.kIntakeSpeed));
    limitMnoo.toggleWhenPressed(new LimitSwitchPneumaticsTest());
    doubleForward.whileHeld(new DoubleMotorTest(RobotMap.kIntakeSpeed));
    doubleReverse.whileHeld(new DoubleMotorTest(-RobotMap.kIntakeSpeed));
  }
}
