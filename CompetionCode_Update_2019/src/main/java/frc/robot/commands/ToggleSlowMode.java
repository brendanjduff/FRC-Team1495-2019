package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleSlowMode extends Command {

  public ToggleSlowMode() {}

  @Override
  protected void initialize() {
      Robot.slowMode = !Robot.slowMode;
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}