package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class RunIntakeTimed extends TimedCommand {
  boolean direction;

  public RunIntakeTimed(boolean d, double timeout) {
    super(timeout);
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
  protected void end() {
    Robot.intake.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.intake.stopMotor();
  }
}