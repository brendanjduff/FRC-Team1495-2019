package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SlowMode extends Command {
  boolean toggle;

  public SlowMode(boolean t) {
    toggle = t;
  }

  @Override
  protected void initialize() {
    if (toggle)
      Robot.slowMode = !Robot.slowMode;
    else
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
    if (!toggle)
      Robot.slowMode = false;
  }

  @Override
  protected void interrupted() {
    if (!toggle)
      Robot.slowMode = false;
  }
}