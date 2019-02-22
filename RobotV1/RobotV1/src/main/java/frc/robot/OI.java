/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.TCCargoClawmp;
import frc.robot.commands.TCLauncher;
import frc.robot.commands.TCPanelPgripper;
import frc.robot.commands.TCPdropper;
import frc.robot.commands.TCDropper;
import frc.robot.commands.TCIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick driver = new Joystick(RobotMap.ControllerPort.kDriver);
  public Joystick operator = new Joystick(RobotMap.ControllerPort.kOperator);

  public Button pdropperDrop = new JoystickButton(driver, 11);
  public Button pdropperUp = new JoystickButton(driver, 12);
  public Button panelPgripperGrab  = new JoystickButton(driver, 3);
  public Button panelPgripperRelease = new JoystickButton(driver, 4);
  public Button cargoClawmpGrab = new JoystickButton(operator, 3);
  public Button cargoClawmpRelease = new JoystickButton(operator, 4);
  public Button launcherLaunch = new JoystickButton(operator, 1);
  public Button launcherReverse = new JoystickButton(operator, 7);
  public Button intakeIntake = new JoystickButton(operator, 2);
  public Button intakeReverse = new JoystickButton(operator, 8);
  public Button dropperDrop = new JoystickButton(operator, 11);
  public Button dropperUp = new JoystickButton(operator, 12);

  public OI() {
    pdropperDrop.whenPressed(new TCPdropper(Value.kForward));
    pdropperUp.whenPressed(new TCPdropper(Value.kReverse));
    panelPgripperGrab.whenPressed(new TCPanelPgripper(Value.kForward));
    panelPgripperRelease.whenPressed(new TCPanelPgripper(Value.kReverse));
    cargoClawmpGrab.whenPressed(new TCCargoClawmp(Value.kForward));
    cargoClawmpRelease.whenPressed(new TCCargoClawmp(Value.kReverse));
    launcherLaunch.whileHeld(new TCLauncher(0.8));
    launcherReverse.whileHeld(new TCLauncher(-0.8));
    intakeIntake.whileHeld(new TCIntake(0.8));
    intakeReverse.whileHeld(new TCIntake(0.8));
    dropperDrop.whenPressed(new TCDropper(Value.kForward));
    dropperUp.whenPressed(new TCDropper(Value.kReverse));
  }
}