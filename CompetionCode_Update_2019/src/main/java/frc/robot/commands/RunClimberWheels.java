/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunClimberWheels extends Command {
  
  boolean direction;

  public RunClimberWheels(boolean d) {
    requires(Robot.cWheels);
    direction = d;
  }

  @Override
  protected void initialize() {
    Robot.cWheels.runMotor(direction);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.cWheels.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.cWheels.stopMotor();
  }
}