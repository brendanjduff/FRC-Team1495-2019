package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/*public class IntakeCargo extends CommandGroup {
  public IntakeCargo() {
    requires(Robot.intake);
    addSequential(new IntakePiston(Value.kForward));
    addSequential(new IntakeMotor(true));
    addSequential(new IntakePiston(Value.kReverse));
  }
}*/

public class IntakeCargo extends Command {
    
  public IntakeCargo() {
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    Robot.intake.togglePiston(Value.kForward);
    Robot.intake.runMotor(false);
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
    Robot.intake.togglePiston(Value.kReverse);
    Robot.intake.stopMotor();
  }

  @Override
  protected void interrupted() {
    Robot.intake.togglePiston(Value.kReverse);
    Robot.intake.stopMotor();
  }
}