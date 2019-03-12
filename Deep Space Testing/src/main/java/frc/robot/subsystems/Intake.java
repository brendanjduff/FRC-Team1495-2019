package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends Subsystem {
  WPI_TalonSRX motor;

  public Intake() {
    motor = new WPI_TalonSRX(RobotMap.CAN.kIntake);
  }

  public void runMotor(boolean direction) {
    if (direction)
      motor.set(RobotMap.Motors.kIntakeSpeed);
    else
      motor.set(-RobotMap.Motors.kIntakeSpeed);
  }

  public void stopMotor() {
    motor.stopMotor();
  }

  @Override
  public void initDefaultCommand() {
  }
}
