package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;

public class Pdropper extends Subsystem {
  
  public DoubleSolenoid doubleSolenoid;

  public Pdropper() {
    doubleSolenoid = new DoubleSolenoid(RobotMap.PCM.kPdropperExtend,RobotMap.PCM.kPdropperRetract);
  }

  @Override
  public void initDefaultCommand() {}
}
