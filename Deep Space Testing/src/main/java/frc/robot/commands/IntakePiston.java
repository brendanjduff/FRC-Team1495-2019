package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakePiston extends Command {
  
  Value direction;

  public IntakePiston(Value v) {
    requires(Robot.intake);
    direction = v;
  }

  @Override
  protected void initialize() {
    Robot.intake.togglePiston(direction);
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
