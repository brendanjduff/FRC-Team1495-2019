package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Climber extends Subsystem {

  WPI_TalonSRX frontMotor;
  WPI_TalonSRX backMotor;

  public Climber() {
    frontMotor = new WPI_TalonSRX(RobotMap.CAN.kFrontClimber);
    backMotor = new WPI_TalonSRX(RobotMap.CAN.kBackClimber);
  }

  @Override
  public void initDefaultCommand() {}
}