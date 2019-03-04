package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends Subsystem {
  WPI_VictorSPX motor;
  DoubleSolenoid piston;

  public Intake() {
    motor = new WPI_VictorSPX(RobotMap.CAN.kIntake);
    piston = new DoubleSolenoid(RobotMap.PCM.kIntakeForward,RobotMap.PCM.kIntakeReverse);
  }

  public void runMotor(boolean direction) {
    if(direction)
      motor.set(RobotMap.Motors.kIntakeSpeed);
    else
      motor.set(-RobotMap.Motors.kIntakeSpeed);
  }

  public void stopMotor() {
    motor.stopMotor();
  }

  public void togglePiston(Value v) {
    piston.set(v);
  }

  @Override
  public void initDefaultCommand() {}
}
