package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;

public class PanelPgripper extends Subsystem {
  
  public DoubleSolenoid doubleSolenoid;

  public PanelPgripper() {
    doubleSolenoid = new DoubleSolenoid(RobotMap.PCM.kPanelPgripperExtend,RobotMap.PCM.kPanelPgripperRetract);
  }

  @Override
  public void initDefaultCommand() {}
}
