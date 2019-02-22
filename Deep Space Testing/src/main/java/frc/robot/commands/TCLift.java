
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TCLift extends Command {

  Double speed;

  public TCLift(double s) {
  requires(Robot.lift);
  speed = s;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.lift.talon.set(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.lift.talon.stopMotor();
  }

  
  @Override
  protected void interrupted() {
    Robot.lift.talon.stopMotor();
  }
}