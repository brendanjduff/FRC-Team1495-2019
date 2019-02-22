package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Dropper extends Subsystem {

  public DoubleSolenoid doubleSolenoid;

  public Dropper() {
    doubleSolenoid = new DoubleSolenoid(RobotMap.PCM.kDropperExtend, RobotMap.PCM.kDropperRetract);
    }

  @Override
  public void initDefaultCommand() {}
}
