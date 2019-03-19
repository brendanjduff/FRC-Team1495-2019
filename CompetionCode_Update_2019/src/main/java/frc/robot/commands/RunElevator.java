package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunElevator extends Command {

  boolean direction;

  public RunElevator(boolean d) {
    requires(Robot.elevator);
    direction = d;
  }

  @Override
  protected void initialize() {
    Robot.elevator.manualControl(direction);
  }

  @Override
  protected void execute() {
    Robot.elevator.checkLimits(direction);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.elevator.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.elevator.stopMotor();
  }
}