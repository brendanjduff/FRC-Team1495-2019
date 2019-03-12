/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class IntakeCargo extends Command {

  public IntakeCargo() {
    requires(Robot.intake);
    requires(Robot.iExtender);
  }

  @Override
  protected void initialize() {
    Robot.iExtender.setPiston(Value.kForward);
    Robot.intake.runMotor(false);
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
    Robot.iExtender.setPiston(Value.kReverse);
    Robot.intake.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.iExtender.setPiston(Value.kReverse);
    Robot.intake.stopMotor();
  }
}