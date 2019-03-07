package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BackClimber extends Subsystem {

  public WPI_VictorSPX motor;

  public BackClimber() {
    motor = new WPI_VictorSPX(RobotMap.CAN.kBackClimber);
  }

  @Override
  public void initDefaultCommand() {}
}
