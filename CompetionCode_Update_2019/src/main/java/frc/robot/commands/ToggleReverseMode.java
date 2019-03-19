package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleReverseMode extends Command {

  public ToggleReverseMode() {}

  @Override
  protected void initialize() {
      Robot.reverseMode = !Robot.reverseMode;
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