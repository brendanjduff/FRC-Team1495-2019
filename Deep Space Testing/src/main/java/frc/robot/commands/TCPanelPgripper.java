package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class TCPanelPgripper extends Command {
    
  Value value;
  
  public TCPanelPgripper(Value v) {
    requires(Robot.panelPgripper);
    value = v;
  }

  @Override
  protected void initialize() {
    Robot.panelPgripper.doubleSolenoid.set(value);
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