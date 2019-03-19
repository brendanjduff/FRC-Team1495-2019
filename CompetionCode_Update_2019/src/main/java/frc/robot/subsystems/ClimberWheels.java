/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ClimberWheels extends Subsystem {
  WPI_TalonSRX motor;

  public ClimberWheels() {
    motor = new WPI_TalonSRX(RobotMap.CAN.kClimberWheels);
    motor.setNeutralMode(NeutralMode.Brake);
  }

  public void runMotor(boolean direction) {
    if (direction)
      motor.set(RobotMap.Motors.kClimberWheelsSpeed);
    else
      motor.set(-RobotMap.Motors.kClimberWheelsSpeed);
  }

  public void stopMotor() {
    motor.stopMotor();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
