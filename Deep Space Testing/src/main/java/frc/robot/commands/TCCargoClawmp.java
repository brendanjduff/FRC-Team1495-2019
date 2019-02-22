package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class TCCargoClawmp extends Command {
  
  Value value;

  public TCCargoClawmp(Value v) {
      requires(Robot.cargoClawmp);
      value = v;
    }
  
    @Override
    protected void initialize() {
      Robot.cargoClawmp.doubleSolenoid.set(value);
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