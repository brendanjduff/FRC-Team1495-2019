package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class MGripperPiston extends Command {

  Value direction = null;

  public MGripperPiston() {
    requires(Robot.manipulator);
  }

  public MGripperPiston(Value v) {
    requires(Robot.manipulator);
    direction = v;
  }

  @Override
  protected void initialize() {
    if (direction != null)
      Robot.manipulator.setPiston(direction);
    else
      Robot.manipulator.TogglePiston();
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
