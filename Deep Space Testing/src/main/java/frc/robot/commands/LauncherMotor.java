package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LauncherMotor extends Command { 
  boolean direction;

  public LauncherMotor(boolean d) {
    //requires(Robot.launcher);
    direction = d;
  }

  @Override
  protected void initialize() {
    //Robot.launcher.runMotor(direction);
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
    //Robot.launcher.stopMotor();
  }

  @Override
  protected void interrupted() {
    //Robot.launcher.stopMotor();
  }
}