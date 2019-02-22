package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TCLauncher extends Command {
  
  double speed;

  public TCLauncher(double s) {
    requires(Robot.launcher);
    speed = s;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.launcher.victor.set(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.launcher.victor.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.launcher.victor.stopMotor();
  }
}