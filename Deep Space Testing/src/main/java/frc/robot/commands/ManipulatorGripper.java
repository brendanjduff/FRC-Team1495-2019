package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ManipulatorGripper extends Command {

  Value direction;

  public ManipulatorGripper(Value v) {
      requires(Robot.manipulator);
      direction = v;
    }
  
    @Override
    protected void initialize() {
      Robot.manipulator.toggleGripperPiston(direction);
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
  