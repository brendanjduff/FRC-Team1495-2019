package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class TCPdropper extends Command {
    
  Value value;
  
  public TCPdropper(Value v) {
    requires(Robot.pdropper);
    value = v;
  }

  @Override
  protected void initialize() {
    Robot.pdropper.doubleSolenoid.set(value);
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