package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TCIntake extends Command {

  Double speed;

  public TCIntake(double s) {
  requires(Robot.intake);
  speed = s;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.intake.victor.set(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.victor.stopMotor();
  }

  
  @Override
  protected void interrupted() {
    Robot.intake.victor.stopMotor();
  }
}