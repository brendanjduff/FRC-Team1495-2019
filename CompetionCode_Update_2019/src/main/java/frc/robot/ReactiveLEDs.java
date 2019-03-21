package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Notifier;

public class ReactiveLEDs {

	private I2C i2;

	public ArrayList<String> cmdQueue = new ArrayList<String>();
	DigitalInput limit = new DigitalInput(0);
	DigitalInput limit2 = new DigitalInput(1);

	public class CommandCenter implements java.lang.Runnable {
		boolean hasArduinoLink = false;
		boolean lastInnerLimitState = false;
		boolean lastOuterLimitState = false;

		@Override
		public void run() {
			sendToArduino("Z");
			updateLimitSwitches();
			if (cmdQueue.size() == 0) {

			} else {
				sendToArduino(cmdQueue.get(0));
				cmdQueue.remove(0);

			}
		}

		public void updateLimitSwitches() {
			if (limit.get() != lastInnerLimitState && limit.get()) {
				addCmd("A");
				lastInnerLimitState = !limit.get();
			} else if (limit2.get() != lastInnerLimitState && limit2.get()) {
				addCmd("B");
			} else {
				addCmd("C");
			}
		}
		
	}

	Notifier notify = new Notifier(new CommandCenter());

	public ReactiveLEDs() {
		i2 = new I2C(Port.kOnboard, 4);
		// s2 = new SerialPort(0, Port.);
		notify.startPeriodic(0.05);
	}

	public void sendToArduino(String input) {// writes to the arduino
		char[] CharArray = input.toCharArray();// creates a char array from the
												// input string
		byte[] WriteData = new byte[CharArray.length];// creates a byte array
														// from the char array
		for (int i = 0; i < CharArray.length; i++) {// writes each byte to the
													// arduino
			WriteData[i] = (byte) CharArray[i];// adds the char elements to the
												// byte array
		}
		// System.out.println("Data to Arduino" + WriteData);
		// i2.transaction(WriteData, WriteData.length, null, 0);//sends each
		// byte to arduino
		// System.out.println("Sending...");
		i2.writeBulk(WriteData);
	}

	public void addCmd(String cmd) {
		cmdQueue.add(cmd);
	}

}