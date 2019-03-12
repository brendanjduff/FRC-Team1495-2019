/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class DoubleSolenoidCommand extends Command {
  boolean extend; 
 
  public DoubleSolenoidCommand(boolean e) {


  extend = e;

   requires(Robot.doubleSolenoid);


   
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {//
    if (extend) {
      Robot.doubleSolenoid.solenoid1.set(Value.kForward);
      Robot.doubleSolenoid.solenoid2.set(Value.kForward);
    }
    else{
      Robot.doubleSolenoid.solenoid1.set(Value.kReverse);
      Robot.doubleSolenoid.solenoid2.set(Value.kReverse);
    }
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

