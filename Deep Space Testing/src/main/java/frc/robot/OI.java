package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ModulateHatchPanelManipulator;

public class OI {
  public Joystick joystick = new Joystick(RobotMap.ControllerPort.kJoystick);

  public Button extend = new JoystickButton(joystick, 3);
  public Button retract = new JoystickButton(joystick, 5);

  public OI() {
    extend.whenPressed(new ModulateHatchPanelManipulator(true));
    retract.whenPressed(new ModulateHatchPanelManipulator(false));
  }
}
