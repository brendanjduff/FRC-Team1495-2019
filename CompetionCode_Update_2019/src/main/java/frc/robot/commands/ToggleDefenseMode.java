package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleDefenseMode extends Command {

  public ToggleDefenseMode() {
    requires(Robot.iExtender);
    requires(Robot.mExtender);
  }

  @Override
  protected void initialize() {
    
    Robot.defenseMode = !Robot.defenseMode;

    if (Robot.defenseMode) {
      // move elevator to bottom position
      Robot.mExtender.setPiston(Value.kReverse);
      Robot.mExtender.Lock();
      Robot.iExtender.setPiston(Value.kReverse);
      Robot.iExtender.Lock();
    } else if (!Robot.defenseMode) {
      Robot.mExtender.Unlock();
      Robot.iExtender.Unlock();
    }
  }

  @Override
  protected void execute() {}

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}