package br.com.reindex.util;


public class Util {

	public static int getMinutos(int minutosASeremAnalisados) {

		if (minutosASeremAnalisados >= 0 && minutosASeremAnalisados < 15) {
			return 0;
		} else if (minutosASeremAnalisados >= 15
				&& minutosASeremAnalisados < 30) {
			return 15;
		} else if (minutosASeremAnalisados >= 30
				&& minutosASeremAnalisados < 45) {
			return 30;
		} else if (minutosASeremAnalisados >= 45
				&& minutosASeremAnalisados < 60) {
			return 45;
		}
		return 0;
	}
}
