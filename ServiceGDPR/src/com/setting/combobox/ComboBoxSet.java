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
}
