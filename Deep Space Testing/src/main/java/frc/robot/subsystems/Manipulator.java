package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Manipulator extends Subsystem {

  DoubleSolenoid gripperPiston;
  DoubleSolenoid dropperPiston;

  public Manipulator() {
    gripperPiston = new DoubleSolenoid(RobotMap.PCM.kGripperForward,RobotMap.PCM.kGripperReverse);
    dropperPiston = new DoubleSolenoid(RobotMap.PCM.kDropperForward,RobotMap.PCM.kDropperReverse);
  }

  public void toggleDropperPiston(Value v) {
    dropperPiston.set(v);
  }
  
  public void toggleGripperPiston(Value v) {
    gripperPiston.set(v);
  }

  @Override
  public void initDefaultCommand() {}
}
