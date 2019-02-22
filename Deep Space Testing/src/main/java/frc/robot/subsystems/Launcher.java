package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Launcher extends Subsystem {
  public WPI_VictorSPX victor;

  public Launcher() {
    victor = new WPI_VictorSPX(RobotMap.CAN.kLauncher);
  }

  @Override
  public void initDefaultCommand() {}
}