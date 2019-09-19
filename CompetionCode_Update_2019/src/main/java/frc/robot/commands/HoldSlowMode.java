package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HoldSlowMode extends Command {
  public HoldSlowMode() {

  }

  @Override
  protected void initialize() {
    Robot.driveBase.setSlowMode(true);
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
    Robot.driveBase.setSlowMode(false);
  }

  @Override
  protected void interrupted() {
    Robot.driveBase.setSlowMode(false);
  }
}
