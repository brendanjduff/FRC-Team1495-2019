/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunBackClimber extends Command {

  boolean direction;

  public RunBackClimber(boolean d) {
    requires(Robot.bClimber);
    direction = d;
  }

  @Override
  protected void initialize() {
    Robot.bClimber.runMotor(direction);
    Robot.iExtender.setPiston(Value.kForward);
  }

  @Override
  protected void execute() {
    Robot.bClimber.checkLimits(direction);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.bClimber.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.bClimber.stopMotor();
  }
}