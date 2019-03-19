package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class MExtenderPiston extends Command {

  Value direction = null;

  public MExtenderPiston() {
    requires(Robot.mExtender);
  }

  public MExtenderPiston(Value v) {
    requires(Robot.mExtender);
    direction = v;
  }

  @Override
  protected void initialize() {
    if (direction != null)
      Robot.mExtender.setPiston(direction);
    else
      Robot.mExtender.TogglePiston();
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
