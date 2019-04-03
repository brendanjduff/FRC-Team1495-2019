package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveElevatorToPosition extends Command {

  int target;
  boolean initialDir;

  public MoveElevatorToPosition(int t) {
    requires(Robot.elevator);
    target = t;
  }

  @Override
  protected void initialize() {
    Robot.elevator.setTargetPosition(target);
    if(Robot.elevator.getPosition() > Robot.elevator.getTargetPosition() + RobotMap.ElevatorControl.kPositionTolerance) {
      initialDir = false;
      Robot.elevator.manualControl(initialDir, RobotMap.Motors.kManualElevatorSpeed);
    }
    else if(Robot.elevator.getPosition() < Robot.elevator.getTargetPosition() - RobotMap.ElevatorControl.kPositionTolerance) {
      initialDir = true;
      Robot.elevator.manualControl(initialDir, RobotMap.Motors.kManualElevatorSpeed);
    }
    else {
      Robot.elevator.stopMotor();
    }
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.elevator.isAtPosition();
  }

  @Override
  protected void end() {
    Robot.elevator.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.elevator.stopMotor();
  }
}
