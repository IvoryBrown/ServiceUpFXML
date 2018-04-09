package com.service.setting.combobox;

public class Combobox {
	public static String[] setDeviceNameCombobox() {
		final String CMBDEVICENAME[] = { "Asztali PC", "Notebook", "Nyomtató", "Monitor", "Projektor", "Pendrive",
				"Szünetmentes tápegység", "Egyéb" };
		return CMBDEVICENAME;
	}

	public static String[] setLocationCombobox() {
		final String CMBDEVICEREPAIRLOCATION[] = { "Szervíz Szeged", "Szervíz Soltvadkert", "Exicom Székház",
				"Helyszíni", "Külsős Cég" };
		return CMBDEVICEREPAIRLOCATION;
	}

	public static String[] setStatusCombobox() {
		final String CMBDEVICESTATUS[] = { "Bevételezve" };
		return CMBDEVICESTATUS;
	}

	public static String[] setYesNoCombobox() {
		final String CMBDEVICNEWMACHINE[] = { "Igen", "Nem" };
		return CMBDEVICNEWMACHINE;
	}

	public static String[] setNewPrioritCombobox() {
		final String CMDDEVICEPRIORIT[] = { "Alap", "Szerződött", "Sűrgős" };
		return CMDDEVICEPRIORIT;
	}

	public static String[] setOperatingSystemCombobox() {
		final String CMDDEVICEOPERATINGSYSTEM[] = { "--------------Linux--------------", "blackPanther OS", "SuliX",
				"UHU-Linux", "Arch Linux", "Debian", "ElementaryOS", "Fedora", "Red Hat", "CentOS", "Linux Mint",
				"Gentoo ", "Mandriva", "Slackware", "SuSE", "Ubuntu", "-----------Windows Server-----------",
				"Windows NT 3.1 Advanced Server", "Windows NT Server 3.5", "Windows NT Server 3.51",
				"Windows NT Server 4.0", "Windows 2000 Server", "Windows Server 2003", "Windows Home Server",
				"Windows Server 2008", "Windows Server 2008 R2", "Windows Home Server 2011", "Windows Server 2012 ",
				"Windows Server 2012 R2", "Windows Server 2016", "-------------Windows------------",
				"Windows 7 Starter 32Bit Hungarian", "Windows 7 Starter 64Bit Hungarian",
				"Windows 7 Home Basic 32Bit Hungarian", "Windows 7 Home Basic 64Bit Hungarian",
				"Windows 7 Home Premium 32Bit Hungarian", "Windows 7 Home Premium 64Bit Hungarian",
				"Windows 7 Professional 32Bit Hungarian", "Windows 7 Professional 64Bit Hungarian",
				"Windows 7 Enterprise 32Bit Hungarian", "Windows 7 Enterprise 64Bit Hungarian",
				"Windows 7 Ultimate 32Bit Hungarian", "Windows 7 Ultimate 64Bit Hungarian",
				"Windows 7 N 32Bit Hungarian", "Windows 7 N 64Bit Hungarian",
				"Windows 8.1 Professional 32Bit Hungarian", "Windows 8.1 Professional 64Bit Hungarian",
				"Windows 8.1 Enterprise 32Bit Hungarian", "Windows 8.1 Enterprise 64Bit Hungarian",
				"Windows 8.1 Core 32Bit Hungarian", "Windows 8.1 Core 64Bit Hungarian",
				"Windows 10 Enterprise 32Bit Hungarian", "Windows 10 Enterprise 64Bit Hungarian",
				"Windows 10 Education 32Bit Hungarian", "Windows 10 Education 64Bit Hungarian",
				"Windows 10 Mobile 32Bit Hungarian", "Windows 10 Mobile 64Bit Hungarian",
				"Windows 10 Home 32Bit Hungarian", "Windows 10 Home 64Bit Hungarian",
				"Windows 10 Professional 32Bit Hungarian", "Windows 10 Professional 64Bit Hungarian" };
		return CMDDEVICEOPERATINGSYSTEM;
	}

	public static String[] setDeviceStaCombobox() {
		final String CMBDEVICESTATUSS[] = { "Bevételezve", "Kiadva" };
		return CMBDEVICESTATUSS;
	}

	public static String[] setDeviceStatusCombobox() {
		final String CMBDEVICESTATUSZ[] = { "Bevizsgálás alatt", "Akkatrészre vár", "Garanciális", "Továbbküldve",
				"Bevizsgálva" };
		return CMBDEVICESTATUSZ;
	}

	public static String[] setErrorStatusCombobox() {
		final String CMBERRORSTATUSZ[] = { "Kész", "Mozi?", "Nem lehet" };
		return CMBERRORSTATUSZ;
	}

	public static String[] setLoginCombobox() {
		final String CMBLOGIN[] = { "Irisz", "Exicom" };
		return CMBLOGIN;
	}

}
