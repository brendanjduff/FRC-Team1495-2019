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
    
    //motor.configClosedloopRamp(.2, 0);
    //motor.configOpenloopRamp(.2, 0);

    motor.configNominalOutputForward(0, 0);
    motor.configNominalOutputReverse(0, 0);
    motor.configPeakOutputForward(1, 0);
    motor.configPeakOutputReverse(-1, 0);
    
    motor.setNeutralMode(NeutralMode.Brake);

    motor.selectProfileSlot(0, 0);
    motor.config_kP(0, 0, 0);
    motor.config_kI(0, 0, 0);
    motor.config_kD(0, 0, 0);
    motor.config_kF(0, 0, 0);
    //motor.configForwardSoftLimitThreshold(RobotMap.ElevatorPID.kForwardSoftLimitThreshold,0);
    //motor.configForwardSoftLimitEnable(false, 0);
    //motor.configReverseSoftLimitThreshold(RobotMap.ElevatorPID.kReverseSoftLimitThreshold, 0);
    //motor.configReverseSoftLimitEnable(false, 0);

    lowerBound = new DigitalInput(RobotMap.DIO.kElevatorLowerBound);
    upperBound = new DigitalInput(RobotMap.DIO.kElevatorUpperBound);

    //zeroElevatorPosition();
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
    return Math.abs(getPosition() - targetPosition) < RobotMap.ElevatorPID.kPositionTolerance;
  }

  public void manualControl(boolean direction) {
    if (direction && !lowerBound.get())
      motor.set(ControlMode.PercentOutput, -RobotMap.Motors.kManualElevatorSpeed);
    else if(!direction && !upperBound.get())
      motor.set(ControlMode.PercentOutput, RobotMap.Motors.kManualElevatorSpeed);
  }

  public void positionControl() {
    motor.set(ControlMode.Position, targetPosition);
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

  public void zeroElevatorPosition() {
    //lower to limit switch
    motor.setSelectedSensorPosition(0);
  }

  @Override
  public void initDefaultCommand() {
  }
}