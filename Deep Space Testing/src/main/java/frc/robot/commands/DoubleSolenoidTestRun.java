

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class DoubleSolenoidTestRun extends Command {
  
  boolean extend;

  public DoubleSolenoidTestRun(boolean e) {
    requires(Robot.doubleSolenoid);
    extend = e;
  }

  
  @Override
  protected void initialize() {
    if(extend) {
      Robot.doubleSolenoid.solenoidOne.set(Value.kForward);
      Robot.doubleSolenoid.solenoidTwo.set(Value.kForward);
    }
    else {
      Robot.doubleSolenoid.solenoidOne.set(Value.kReverse);
      Robot.doubleSolenoid.solenoidTwo.set(Value.kReverse);
    }
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
