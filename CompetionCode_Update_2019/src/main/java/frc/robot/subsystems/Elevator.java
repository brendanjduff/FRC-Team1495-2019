package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Elevator extends Subsystem {

  WPI_TalonSRX motor;
  DigitalInput lowerBound;
  DigitalInput upperBound;

  int targetPosition;

  public Elevator() {
    motor = new WPI_TalonSRX(RobotMap.CAN.kElevator);

    motor.configFactoryDefault();
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

    motor.setSensorPhase(true);
    motor.setInverted(false);
    
    motor.setNeutralMode(NeutralMode.Brake);
  
    lowerBound = new DigitalInput(RobotMap.DIO.kElevatorLowerBound);
    upperBound = new DigitalInput(RobotMap.DIO.kElevatorUpperBound);
  }

  public boolean getLowerbound() {
    return lowerBound.get();
  }

  public boolean getUpperbound() {
    return upperBound.get();
  }

  public void setTargetPosition(int targetPos) {
    targetPosition = targetPos;
  }

  public int getTargetPosition() {
    return targetPosition;
  }

  public int getPosition() {
    return motor.getSelectedSensorPosition();
  }

  public boolean isAtPosition() {
    return Math.abs(getPosition() - targetPosition) < RobotMap.ElevatorControl.kPositionTolerance;
  }

  public void manualControl(boolean direction, double speed) {
    if (direction && !lowerBound.get())
      motor.set(ControlMode.PercentOutput, -speed);
    else if(!direction && !upperBound.get())
      motor.set(ControlMode.PercentOutput, speed);
  }

  public void checkLimits(boolean direction) {
    if(direction && lowerBound.get())
      motor.stopMotor();
    if (!direction && upperBound.get())
      motor.stopMotor();
  }

  public void stopMotor() {
    motor.stopMotor();
  }

  public void setPositionZero() {
    motor.setSelectedSensorPosition(0);
  }

  @Override
  public void initDefaultCommand() {
  }
}