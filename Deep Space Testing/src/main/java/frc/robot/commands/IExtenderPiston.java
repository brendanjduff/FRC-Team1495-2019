package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IExtenderPiston extends Command {

  Value direction = null;

  public IExtenderPiston() {
    requires(Robot.iExtender);
  }

  public IExtenderPiston(Value v) {
    requires(Robot.iExtender);
    direction = v;
  }

  @Override
  protected void initialize() {
    if (direction != null)
      Robot.iExtender.setPiston(direction);
    else
      Robot.iExtender.TogglePiston();
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
