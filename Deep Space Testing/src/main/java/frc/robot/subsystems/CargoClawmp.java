package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoClawmp extends Subsystem {

  public DoubleSolenoid doubleSolenoid;

  public CargoClawmp() {
    doubleSolenoid = new DoubleSolenoid(RobotMap.PCM.kCargoClawmpExtend,RobotMap.PCM.kCargoClawmpRetract);
  }

  @Override
  public void initDefaultCommand() {}
}
