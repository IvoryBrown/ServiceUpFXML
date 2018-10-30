package com.device.pojo;

public final class DeviceClient {
	private static String deviceClientID;
	private static String deviceCompanyName;
	private static String deviceClientName;
	
	public static final String getDeviceClientID() {
		return deviceClientID;
	}
	public static final void setDeviceClientID(String deviceClientID) {
		DeviceClient.deviceClientID = deviceClientID;
	}
	public static final String getDeviceCompanyName() {
		return deviceCompanyName;
	}
	public static final void setDeviceCompanyName(String deviceCompanyName) {
		DeviceClient.deviceCompanyName = deviceCompanyName;
	}
	public static final String getDeviceClientName() {
		return deviceClientName;
	}
	public static final void setDeviceClientName(String deviceClientName) {
		DeviceClient.deviceClientName = deviceClientName;
	}
}
