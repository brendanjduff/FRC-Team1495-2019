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

public class LimitSwitchPneumaticsTest extends Command {

boolean end;

  public LimitSwitchPneumaticsTest() {
      requires(Robot.doubleSolenoid);
    }
  
    
    @Override
    protected void initialize() {
      Robot.doubleSolenoid.solenoidOne.set(Value.kReverse);
      Robot.doubleSolenoid.solenoidTwo.set(Value.kReverse);
    }
  
    
    @Override
    protected void execute() {
      if(Robot.limitSwitch.get()) {
        Robot.doubleSolenoid.solenoidOne.set(Value.kForward);
        Robot.doubleSolenoid.solenoidTwo.set(Value.kForward);
      }
    }
  
  
    @Override
    protected boolean isFinished() {
      return false;
    }
  
   
    @Override
    protected void end() {
    }
  
    
    @Override
    protected void interrupted() {
    }
  }
  