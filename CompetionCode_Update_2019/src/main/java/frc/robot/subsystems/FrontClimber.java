package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class FrontClimber extends Subsystem {

  public WPI_TalonSRX motor;
  public DigitalInput lowerBound;
  public DigitalInput upperBound;

  public FrontClimber() {
    motor = new WPI_TalonSRX(RobotMap.CAN.kFrontClimber);
    motor.setNeutralMode(NeutralMode.Brake);
    lowerBound = new DigitalInput(RobotMap.DIO.kFClimberLowerBound);
    upperBound = new DigitalInput(RobotMap.DIO.kFClimberUpperBound);
  }

  public void runMotor(boolean direction) {
    if (direction && !lowerBound.get())
      motor.set(-RobotMap.Motors.kClimberFastSpeed);
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
  public void initDefaultCommand() {
  }
}