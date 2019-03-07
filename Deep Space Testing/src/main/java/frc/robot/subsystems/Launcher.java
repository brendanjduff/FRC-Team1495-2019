package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Launcher extends Subsystem {
  WPI_VictorSPX leftMotor;
  WPI_VictorSPX rightMotor;

  public Launcher() {
    leftMotor = new WPI_VictorSPX(RobotMap.CAN.kLauncherLeft);
    rightMotor = new WPI_VictorSPX(RobotMap.CAN.kLauncherRight);
  }

  public void runMotor(boolean direction) {
    if (direction) {
      leftMotor.set(-RobotMap.Motors.kLauncherHoldSpeed);
      rightMotor.set(RobotMap.Motors.kLauncherHoldSpeed);
  }
    else {
      leftMotor.set(RobotMap.Motors.kLauncherSpeedForward);
      rightMotor.set(-RobotMap.Motors.kLauncherSpeedForward);
    }
  }

  public void stopMotor() {
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }

  @Override
  public void initDefaultCommand() {
  }
}