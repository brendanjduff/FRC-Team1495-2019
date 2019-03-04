package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeMotor extends Command {
  
  boolean direction;

  public IntakeMotor(boolean d) {
    requires(Robot.intake);
    direction = d;
  }

  @Override
  protected void initialize() {
    Robot.intake.runMotor(direction);
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
    Robot.intake.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.intake.stopMotor();
  }
}