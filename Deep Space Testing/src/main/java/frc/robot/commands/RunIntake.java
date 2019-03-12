package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunIntake extends Command {

  boolean direction;

  public RunIntake(boolean d) {
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