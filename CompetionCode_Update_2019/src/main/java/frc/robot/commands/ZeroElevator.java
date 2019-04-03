/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ZeroElevator extends Command {
  public ZeroElevator() {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
    Robot.elevator.manualControl(true, RobotMap.Motors.kZeroingElevatorSpeed);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.elevator.getLowerbound();
  }

  @Override
  protected void end() {
    Robot.elevator.setPositionZero();
    Robot.elevator.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.elevator.stopMotor();  
  }
}
