package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class RunLauncherTimed extends TimedCommand {
  boolean direction;

  public RunLauncherTimed(boolean d, double timeout) {
    super(timeout);
    requires(Robot.launcher);
    direction = d;
  }

  @Override
  protected void initialize() {
    Robot.launcher.runMotor(direction);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected void end() {
    Robot.launcher.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.launcher.stopMotor();
  }
}