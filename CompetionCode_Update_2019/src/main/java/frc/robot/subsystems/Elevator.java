package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Elevator extends Subsystem {

  WPI_TalonSRX motor;
  DigitalInput lowerBound;
  DigitalInput upperBound;

  public Elevator() {
    motor = new WPI_TalonSRX(RobotMap.CAN.kElevator);

    motor.configFactoryDefault();
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.PID.kTimeoutMs);

    motor.setSensorPhase(true);
    motor.setInverted(false);

    motor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.PID.kTimeoutMs);
    motor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.PID.kTimeoutMs);

    motor.configNominalOutputForward(0, RobotMap.PID.kTimeoutMs);
    motor.configNominalOutputReverse(0, RobotMap.PID.kTimeoutMs);
    motor.configPeakOutputForward(1, RobotMap.PID.kTimeoutMs);
    motor.configPeakOutputReverse(-1, RobotMap.PID.kTimeoutMs);

    motor.selectProfileSlot(0, 0);
    motor.config_kP(0, RobotMap.PID.Elevator.kP, RobotMap.PID.kTimeoutMs);
    motor.config_kI(0, RobotMap.PID.Elevator.kI, RobotMap.PID.kTimeoutMs);
    motor.config_kD(0, RobotMap.PID.Elevator.kD, RobotMap.PID.kTimeoutMs);
    motor.config_kF(0, RobotMap.PID.Elevator.kF, RobotMap.PID.kTimeoutMs);
    motor.configMotionAcceleration(RobotMap.PID.Elevator.kMaxAcceleration, RobotMap.PID.kTimeoutMs);
    motor.configMotionCruiseVelocity(RobotMap.PID.Elevator.kCruiseVelocity, RobotMap.PID.kTimeoutMs);

    //lowerBound = new DigitalInput(RobotMap.DIO.kElevatorLowerBound);
    //upperBound = new DigitalInput(RobotMap.DIO.kElevatorUpperBound);

    // zero the sensor
  }

  public void setPosition(int targetPos) {
    motor.set(ControlMode.MotionMagic, targetPos);
  }

  public void runMotor(boolean direction) {
    if (direction)
      motor.set(-RobotMap.Motors.kManualElevatorTestSpeed);
    else
      motor.set(RobotMap.Motors.kManualElevatorTestSpeed);
  }

  public int getPosition() {
    return motor.getSelectedSensorPosition();
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
