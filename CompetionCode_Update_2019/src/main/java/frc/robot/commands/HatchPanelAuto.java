package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class HatchPanelAuto extends Command {
  public HatchPanelAuto() {
    requires(Robot.manipulator);
    requires(Robot.mExtender);
  }

  @Override
  protected void initialize() {
  Robot.manipulator.setPiston(Value.kForward);
  Robot.mExtender.setPiston(Value.kForward); //working?
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
