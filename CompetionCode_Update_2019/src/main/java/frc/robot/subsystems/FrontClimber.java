package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class FrontClimber extends Subsystem {

  public WPI_TalonSRX motor;

  public FrontClimber() {
    motor = new WPI_TalonSRX(RobotMap.CAN.kFrontClimber);
  }

  @Override
  public void initDefaultCommand() {
  }
}