package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends Subsystem {
  public WPI_VictorSPX victor;

  public Intake() {
    victor = new WPI_VictorSPX(RobotMap.CAN.kIntake);
  }

  @Override
  public void initDefaultCommand() {}
}
