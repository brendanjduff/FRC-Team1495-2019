/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunFrontClimber extends Command {

  boolean direction;

  public RunFrontClimber(boolean d) {
    requires(Robot.fClimber);
    direction = d;
  }

  @Override
  protected void initialize() {
    Robot.fClimber.runMotor(direction);
  }

  @Override
  protected void execute() {
    Robot.fClimber.checkLimits(direction);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.fClimber.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.fClimber.stopMotor();
  }
}