package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Notifier;

public class ReactiveLEDs {

	private I2C i2;

	private ArrayList<String> cmdQueue = new ArrayList<String>();

	public class CommandCenter implements java.lang.Runnable {
		boolean hasArduinoLink = false;

		@Override
		public void run() {
			sendToArduino("Z");
			//updateLimitSwitches();
			if (cmdQueue.size() != 0)
				sendToArduino(cmdQueue.remove(0));
		}

		public void updateLimitSwitches() {
		}
		
	}

	Notifier notify = new Notifier(new CommandCenter());

	public ReactiveLEDs() {
		i2 = new I2C(Port.kOnboard, 4);
		notify.startPeriodic(0.05);
	}

	public void sendToArduino(String input) {
		
		char[] CharArray = input.toCharArray();
		
		byte[] WriteData = new byte[CharArray.length];
		
		for (int i = 0; i < CharArray.length; i++)										// arduino
			WriteData[i] = (byte) CharArray[i];
		i2.writeBulk(WriteData);
	}

	public void addCmd(char cmd) {
		cmdQueue.add(cmd);
	}

}
