package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

  public WPI_TalonSRX talon;

  public Climber(int id) {
    talon = new WPI_TalonSRX(id);
  }

  @Override
  public void initDefaultCommand() {}
}