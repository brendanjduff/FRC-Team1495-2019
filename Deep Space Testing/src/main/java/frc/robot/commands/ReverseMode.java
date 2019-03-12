package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ReverseMode extends Command {
  boolean toggle;

  public ReverseMode(boolean t) {
    toggle = t;
  }

  @Override
  protected void initialize() {
    if (toggle)
      Robot.reverseMode = !Robot.reverseMode;
    else
      Robot.reverseMode = true;
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
    if (!toggle)
      Robot.reverseMode = false;
  }

  @Override
  protected void interrupted() {
    if (!toggle)
      Robot.reverseMode = false;
  }
}