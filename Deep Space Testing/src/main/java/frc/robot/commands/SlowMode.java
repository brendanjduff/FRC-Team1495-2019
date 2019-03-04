package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SlowMode extends Command {
  public SlowMode() {}

  @Override
  protected void initialize() {
    Robot.slowMode = true;
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
    Robot.slowMode = false;
  }

  @Override
  protected void interrupted() {
    Robot.slowMode = false;
  }
}