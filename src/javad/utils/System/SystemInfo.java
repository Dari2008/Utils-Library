package javad.utils.System;

import java.lang.management.ManagementFactory;

import com.github.sarxos.webcam.Webcam;
import com.sun.management.OperatingSystemMXBean;

import oshi.software.os.OperatingSystem.OSVersionInfo;

public class SystemInfo{

	public static oshi.SystemInfo s = null;
	
	static {
		s = new oshi.SystemInfo();
	}
	
	public static int getAvailableMemoryAsInt() {
		if(s == null) {
			s = new oshi.SystemInfo();
		}
		return (int)s.getHardware().getMemory().getAvailable() / 1000000000;
	}
	
	public static int getTotalMemoryAsInt() {
		if(s == null) {
			s = new oshi.SystemInfo();
		}
		return (int)s.getHardware().getMemory().getTotal() / 1000000000;
	}
	
	public static long getAvailableMemoryAsLong() {
		if(s == null) {
		s = new oshi.SystemInfo();
	}
		return s.getHardware().getMemory().getAvailable();
	}
	
	public static long getTotalMemoryAsLong() {
		if(s == null) {
		s = new oshi.SystemInfo();
	}
		return s.getHardware().getMemory().getTotal();
	}
	
	public static boolean hasWebcam() {
		return Webcam.getDefault() != null;
	}
	
	public static boolean isWebcamConected() {
		if(hasWebcam()) {
			return !(Webcam.getWebcams().size() >= 2);
		}else {
		return !Webcam.getWebcams().isEmpty();
		}
	}
	
	public static String getOperatingSystemFamily() {
		if(s == null) {
		s = new oshi.SystemInfo();
	}
		return s.getOperatingSystem().getFamily();
	}

	public static String getOperatingSystemManufacturer() {
		if(s == null) {
		s = new oshi.SystemInfo();
	}
		return  s.getOperatingSystem().getManufacturer();
	}

	public static OSVersionInfo getOperatingSystemVersion() {
		if(s == null) {
		s = new oshi.SystemInfo();
	}
		return  s.getOperatingSystem().getVersionInfo();
	}

	
}
