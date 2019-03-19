package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BackClimber extends Subsystem {

  public WPI_TalonSRX motor;
  public DigitalInput lowerBound;
  public DigitalInput upperBound;

  public BackClimber() {
    motor = new WPI_TalonSRX(RobotMap.CAN.kBackClimber);
    motor.setNeutralMode(NeutralMode.Brake);
    lowerBound = new DigitalInput(RobotMap.DIO.kBClimberLowerBound);
    upperBound = new DigitalInput(RobotMap.DIO.kBClimberUpperBound);
  }

  public void runMotor(boolean direction) {
  if (direction && !lowerBound.get())
    motor.set(-RobotMap.Motors.kClimberSlowSpeed);
  else if(!direction && !upperBound.get())
    motor.set(RobotMap.Motors.kClimberFastSpeed);
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

  @Override
  public void initDefaultCommand() {}
}
