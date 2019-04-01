package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefenseMode extends Command {
  boolean toggle;

  public DefenseMode(boolean t) {
    requires(Robot.iExtender);
    requires(Robot.mExtender);
    toggle = t;
  }

  @Override
  protected void initialize() {
    if (toggle)
      Robot.defenseMode = !Robot.defenseMode;
    else
      Robot.defenseMode = true;

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
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    if (!toggle) {
      Robot.defenseMode = false;
      Robot.mExtender.Unlock();
      Robot.iExtender.Unlock();
    }
  }

  @Override
  protected void interrupted() {
    if (!toggle) {
      Robot.defenseMode = false;
      Robot.mExtender.Unlock();
      Robot.iExtender.Unlock();
    }
  }
}