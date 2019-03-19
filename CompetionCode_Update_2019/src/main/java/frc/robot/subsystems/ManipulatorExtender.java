package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ManipulatorExtender extends Subsystem {

  DoubleSolenoid piston;
  Value latest = Value.kReverse;
  boolean locked = false;

  public ManipulatorExtender() {
    piston = new DoubleSolenoid(RobotMap.PCM.kMExtenderForward, RobotMap.PCM.kMExtenderReverse);
  }

  public void setPiston(Value v) {
    if (!locked) {
      piston.set(v);
      latest = v;
    }
  }

  public void TogglePiston() {
    if (!locked) {
      if (latest == Value.kForward) {
        piston.set(Value.kReverse);
        latest = Value.kReverse;
      } else if (latest == Value.kReverse) {
        piston.set(Value.kForward);
        latest = Value.kForward;
      }
    }
  }

  public void Lock() {
    locked = true;
  }

  public void Unlock() {
    locked = false;
  }

  @Override
  public void initDefaultCommand() {
  }
}
