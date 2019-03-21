package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HoldTurboMode extends Command {
  public HoldTurboMode() {

  }

  @Override
  protected void initialize() {
    Robot.driveBase.setTurboMode(true);
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
    Robot.driveBase.setTurboMode(false);
  }

  @Override
  protected void interrupted() {
    Robot.driveBase.setTurboMode(false);
  }
}
