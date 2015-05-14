package br.com.reindex;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.reindex.domain.Dim_Tempo;
import br.com.reindex.util.Util;

public class CriaRegistrosTempo {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dmVenda");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Calendar agora = Calendar.getInstance();
		agora.set(Calendar.DAY_OF_MONTH, agora.get(Calendar.DAY_OF_MONTH) - 1); // começa
																				// ontem

		agora.set(Calendar.MINUTE, 0);
		Calendar dataLimite = Calendar.getInstance();
		dataLimite.set(Calendar.YEAR, dataLimite.get(Calendar.YEAR) + 2); // vai
																			// ate
																			// daqui
																			// dois
																			// anos

		int i = 0;
		while (agora.before(dataLimite)) {
			i++;
			agora.set(Calendar.SECOND, 0);
			int dia = agora.get(Calendar.DAY_OF_MONTH);
			int mes = agora.get(Calendar.MONTH) + 1;
			int ano = agora.get(Calendar.YEAR);
			int hora = agora.get(Calendar.HOUR_OF_DAY);
			int minutos = Util.getMinutos(agora.get(Calendar.MINUTE));
			String descricaoMes = getDescricaoMes(agora);
			String diaSemana = getDiaDaSemana(agora);
			String ehFds = ehFds(agora);
			String faixaHoraDia = getFaixaHoraDia(agora);

			Calendar dataASerSalva = Calendar.getInstance();
			dataASerSalva.set(Calendar.DAY_OF_MONTH, dia);
			dataASerSalva.set(Calendar.MONTH, mes - 1);
			dataASerSalva.set(Calendar.YEAR, ano);
			dataASerSalva.set(Calendar.HOUR_OF_DAY, hora);
			dataASerSalva.set(Calendar.MINUTE, minutos);
			dataASerSalva.set(Calendar.SECOND, 0);

			Dim_Tempo data = new Dim_Tempo(dataASerSalva.getTime(), dia, mes, ano,
					descricaoMes, diaSemana, ehFds, faixaHoraDia);
			manager.persist(data);

			agora.add(Calendar.MINUTE, 15);

		}
		System.out.println("datas: "+ i);
		trx.commit();
		manager.close();

	}

	private static String getFaixaHoraDia(Calendar agora) {
		int horaDia = agora.get(Calendar.HOUR_OF_DAY);
		if (horaDia >= 0 && horaDia < 6)
			return "00:00 - 06:00";
		else if (horaDia >= 6 && horaDia < 12)
			return "06:00 - 12:00";
		else if (horaDia >= 12 && horaDia < 18)
			return "12:00 - 18:00";
		else if (horaDia >= 18 && horaDia <= 24)
			return "18:00 - 23:59";
		return "";
	}

	private static String ehFds(Calendar agora) {
		String diaDaSemana = getDiaDaSemana(agora);
		if (diaDaSemana.equalsIgnoreCase("Sábado")
				|| diaDaSemana.equalsIgnoreCase("Domingo"))
			return "Sim";
		else
			return "Não";
	}

	private static String getDiaDaSemana(Calendar agora) {
		int diaSemana = agora.get(Calendar.DAY_OF_WEEK);

		switch (diaSemana) {
		case 1:
			return "Domingo";
		case 2:
			return "Segunda-Feira";
		case 3:
			return "Terça-Feira";
		case 4:
			return "Quarta-Feira";
		case 5:
			return "Quinta-Feira";
		case 6:
			return "Sexta-Feira";
		case 7:
			return "Sábado";

		default:
			break;
		}

		return null;
	}

	private static String getDescricaoMes(Calendar agora) {
		int mes = agora.get(Calendar.MONTH);

		switch (mes) {
		case 0:
			return "Janeiro";
		case 1:
			return "Fevereiro";
		case 2:
			return "Março";
		case 3:
			return "Abril";
		case 4:
			return "Maio";
		case 5:
			return "Junho";
		case 6:
			return "Julho";
		case 7:
			return "Agosto";
		case 8:
			return "Setembro";
		case 9:
			return "Outubro";
		case 10:
			return "Novembro";
		case 11:
			return "Dezembro";

		default:
			break;
		}
		return null;
	}

}
