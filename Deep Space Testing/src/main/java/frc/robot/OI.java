package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.TCPanelPgripper;
import frc.robot.commands.TCPdropper;
import frc.robot.commands.TCLauncher;
import frc.robot.commands.TCCargoClawmp;
import frc.robot.commands.TCIntake;
import frc.robot.commands.TCLift;

/* - Xbox Controller Button Numbers
*         1           A
*         2           B
*         3           X
*         4           Y
*         5           Left Bumper            
*         6           Right Bumper
*         7           Share
*         8           Options
*         9           Left Stick
*        10           Right Stick
*/

public class OI {
  public XboxController driver = new XboxController(RobotMap.ControllerPort.kDriver);
  //public Joystick operator = new Joystick(RobotMap.ControllerPort.kOperator);

  public Button pdropperDrop = new JoystickButton(driver, 3);
  public Button pdropperUp = new JoystickButton(driver, 4);
  public Button panelPgripperGrab  = new JoystickButton(driver, 1);
  public Button panelPgripperRelease = new JoystickButton(driver, 2);
  /*public Button launcherLaunch = new JoystickButton(driver, 3);
  public Button launcherUnlaunch = new JoystickButton(driver, 4);
  public Button intakeIn = new JoystickButton(driver, 5);
  public Button intakeOut = new JoystickButton(driver,6);
  public Button cargoClawmpGrab = new JoystickButton(driver, 7);
  public Button cargoClawmpRelease = new JoystickButton(driver, 8);*/

  public Button liftUp = new JoystickButton(driver,7);
  public Button liftDown = new JoystickButton(driver,8);

  public OI() {
    pdropperDrop.whenPressed(new TCPdropper(Value.kForward));
    pdropperUp.whenPressed(new TCPdropper(Value.kReverse));
    panelPgripperGrab.whenPressed(new TCPanelPgripper(Value.kForward));
    panelPgripperRelease.whenPressed(new TCPanelPgripper(Value.kReverse));
    /*launcherLaunch.whileHeld(new TCLauncher(0.8));
    launcherUnlaunch.whileHeld(new TCLauncher(-0.8));
    intakeIn.whileHeld(new TCIntake(0.8));
    intakeOut.whileHeld(new TCIntake(-0.8));
    cargoClawmpGrab.whenPressed(new TCCargoClawmp(Value.kForward));
    cargoClawmpRelease.whenPressed(new TCCargoClawmp(Value.kReverse));*/
    liftUp.whileHeld(new TCLift(0.8));
    liftDown.whileHeld(new TCLift(-0.8));
  }
}