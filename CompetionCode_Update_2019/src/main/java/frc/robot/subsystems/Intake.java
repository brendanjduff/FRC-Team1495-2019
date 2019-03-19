package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends Subsystem {
  WPI_VictorSPX motor;

  public Intake() {
    motor = new WPI_VictorSPX(RobotMap.CAN.kIntake);
    motor.setNeutralMode(NeutralMode.Coast);
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
