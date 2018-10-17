package com.setting.combobox;

public class ComboBoxSet {

	public static String[] getAdministratorpost() {
		String[] administratorPost = { "Adminisztrátor", "Technikus" };
		return administratorPost;
	}

	public static String[] getAdministratorAuthority() {
		String[] administratorAuthority = { "Guest", "User", "SuperUser", "Admin" };
		return administratorAuthority;
	}

	public static String[] setErrorStatusCombobox() {
		final String CMBERRORSTATUSZ[] = { "Kész", "Folyamatba", "Nem lehet" };
		return CMBERRORSTATUSZ;
	}
	
	public static String[] setDeviceStaCombobox() {
		final String CMBDEVICESTATUSS[] = { "Bevételezve", "Kiadva" };
		return CMBDEVICESTATUSS;
	}
	
	public static String[] setDeviceStatusCombobox() {
		final String CMBDEVICESTATUSZ[] = { "Bevizsgálás alatt", "Alkatrészre vár", "Garanciális", "Továbbküldve",
				"Bevizsgálva" };
		return CMBDEVICESTATUSZ;
	}
}
