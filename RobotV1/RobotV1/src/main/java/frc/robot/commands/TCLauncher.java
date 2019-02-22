/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TCLauncher extends Command {

    double speed;

    public TCLauncher(double s) {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      requires(Robot.launcher);
      speed = s;
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
      Robot.launcher.leftTalon.set(speed);
      Robot.launcher.rightTalon.set(-speed);
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return false;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
      Robot.launcher.leftTalon.stopMotor();
      Robot.launcher.rightTalon.stopMotor();
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      Robot.launcher.leftTalon.stopMotor();
      Robot.launcher.rightTalon.stopMotor();
    }
  }
  