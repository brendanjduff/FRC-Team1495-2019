package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class LauncherPiston extends Command {

  Value direction;

  public LauncherPiston(Value v) {
    //requires(Robot.launcher);
    direction = v;
  }

  @Override
  protected void initialize() {
    //Robot.launcher.togglePiston(direction);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
