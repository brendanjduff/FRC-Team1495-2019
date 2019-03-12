/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Launcher extends Subsystem {

  public WPI_TalonSRX leftTalon;
  public WPI_TalonSRX rightTalon;

  public Launcher() {
    rightTalon = new WPI_TalonSRX(RobotMap.CAN.kRightLauncher);
    leftTalon = new WPI_TalonSRX(RobotMap.CAN.kLeftLauncher);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
