/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
//import frc.robot.RobotMap;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

/**
 * Add your docs here.
 */
public class EncoderTest extends Subsystem {
 // public static WPI_TalonSRX testLeftMasterTalonSRX, testLestSlaveTalonSRX, testRightMasterTalonSRX, testRightSlaveTalonSRX;

 // pulbic EncoderTest(int ri)
  public static WPI_TalonSRX testLeftMasterTalonSRX, testLeftSlaveTalonSRX, testRightMasterTalonSRX, testRightSlaveTalonSRX;

  public EncoderTest(int leftMasterId, int rightMasterId, int leftSlaveId, int rightSlaveId) {

    testLeftMasterTalonSRX = new WPI_TalonSRX(leftMasterId);
    testLeftSlaveTalonSRX  = new WPI_TalonSRX(leftSlaveId);
    testRightMasterTalonSRX = new WPI_TalonSRX(rightMasterId);
    testRightSlaveTalonSRX = new WPI_TalonSRX(rightSlaveId);

  testLeftSlaveTalonSRX.follow(testLeftMasterTalonSRX);
  testRightSlaveTalonSRX.follow(testRightMasterTalonSRX);

  testRightMasterTalonSRX.setInverted(true);
  testRightSlaveTalonSRX.setInverted(true);

  testLeftMasterTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
 
  testLeftMasterTalonSRX.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
  testLeftMasterTalonSRX.setSelectedSensorPosition(0);
  testLeftMasterTalonSRX.setSensorPhase(false);

  testRightMasterTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
  testRightMasterTalonSRX.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
  testRightMasterTalonSRX.setSelectedSensorPosition(0);
  testRightMasterTalonSRX.setSensorPhase(false);


}
/*
public static boolean isLeftSensorReset() {
  return testLeftMasterTalonSRX.hasResetOccurred();
}

public static boolean isRightSensorReset() {
  return testRightMasterTalonSRX.hasResetOccurred();
}
  
public static double getLeftTick() {
  return testLeftMasterTalonSRX.getSelectedSensorPosition() * (Math.PI * RobotMap.Multipliers.kWheelDiameter) / RobotMap.Multipliers.kPPR;
}

public static double getRightTick() {
  return testRightMasterTalonSRX.getSelectedSensorPosition() * (Math.PI * RobotMap.Multipliers.kWheelDiameter) / RobotMap.Multipliers.kPPR;
}

*/
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

