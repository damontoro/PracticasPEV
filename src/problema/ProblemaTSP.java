package src.problema;

import java.util.ArrayList;

import src.individuo.Individuo;
import src.individuo.IndividuoEntero;
import src.utils.TipoProblema;

public class ProblemaTSP extends Problema{

	private static final int NUM_CIUDADES = 27;

	public ProblemaTSP() {
		super();
		tipo = TipoProblema.MINIMIZACION;
	}

	@Override
	public Individuo build() {
		return new IndividuoEntero(NUM_CIUDADES);
	}

	@Override
	public Individuo build(Individuo i) {
		return new IndividuoEntero((IndividuoEntero)i);
	}

	@Override
	public <T> Individuo build(ArrayList<T> valores) {
		return new IndividuoEntero((ArrayList<Integer>)valores);
	}

	@Override
	public <T> double evaluar(ArrayList<T> fenotipo) {
		int distancia = 0;
		try{
			distancia += calcDistancia((Integer)fenotipo.get(0), Ciudad.MADRID.getValue());
			for (int i = 0; i < fenotipo.size()-1; i++) {
				distancia += calcDistancia((Integer)fenotipo.get(i), (Integer)fenotipo.get(i+1));
			}
			distancia += calcDistancia((Integer)fenotipo.get(fenotipo.size()-1), Ciudad.MADRID.getValue());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return distancia;
	}
	

	private int calcDistancia(int ciudad1, int ciudad2){
			int aux1, aux2;
			aux1 = Math.max(ciudad1, ciudad2);
			aux2 = Math.min(ciudad1, ciudad2);
			return _DIST[aux1][aux2];
	}

	public static enum Ciudad {
		ALBACETE (0), ALICANTE(1), ALMERIA(2), AVILA(3),
		BADAJOZ(4), BARCELONA(5), BILBAO(6), BURGOS(7),
		CACERES(8), CADIZ(9), CASTELLON(10), CIUDAD_REAL(11), 
		CORDOBA(12), CORUÑA(13), CUENCA(14),
		GIRONA(15), GRANADA(16), GUADALAJARA(17),
		HUELVA(18), HUESCA(19),
		JAEN(20), LEON(21), LLEIDA(22), LOGRONO(23), LUGO(24),
		MADRID(25), MALAGA(26), MURCIA(27);

		private final int value;
		private Ciudad (int value){
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	};

	private final static int[][] _DIST = {
		{},
		{171},
		{369,	294},
		{366,	537,	633},
		{525,	696,	604,	318},
		{540,	515,	809,	717,	1022},
		{646,	817,	958,	401,	694,	620},
		{488,	659,	800,	243,	536,	583,	158},
		{504,	675,	651,	229,	89,		918,	605,	447},
		{617,	688,	484,	618,	342,	1284,	1058,	900,	369},
		{256,	231,	525,	532,	805,	284,	607,	524,	701,	873},
		{207,	378,	407,	256,	318,	811,	585,	427,	324,	464,	463},
		{354,	525,	332,	457,	272,	908,	795,	637,	319,	263,	610,	201},
		{860,	1031,	1172,	538,	772,	1118,	644,	535,	683,	1072,	1026,	799,	995},
		{142,	313,	511,	282,	555,	562,	562,	404,	451,	708,	305,	244,	445,	776},
		{640,	615,	909,	817,	1122,	100,	720,	683,	1018,	1384,	384,	911,	1008,	1218,	662},
		{363,	353,	166,	534,	438,	868,	829,	671,	485,	335,	584,	278,	166,	1043,	479,	968},
		{309,	480,	621,	173,	459,	563,	396,	238,	355,	721,	396,	248,	458,	667,	486,	663,	492},
		{506,	703,	516,	552,	251,	1140,	939,	781,	323,	219,	856,	433,	232,	1006,	677,	1240,	350,	690},
		{495,	570,	830,	490,	798,	274,	322,	359,	694,	1060,	355,	587,	797,	905,	406,	374,	831,	339,	1029},
		{264,	415,	228,	435,	376,	804,	730,	572,	423,	367,	520,	179,	104,	944,	380,	904,	99,		393,	336,	732},
		{584,	855,	896,	255,	496,	784,	359,	201,	407,	796,	725,	511,	733,	334,	500,	884,	761,	391,	730,	560,	668},
		{515,	490,	802,	558,	866,	156,	464,	427,	762,	1128,	259,	655,	865,	973,	472,	256,	861,	407,	1097,	118,	779,	628},
		{578,	653,	899,	358,	676,	468,	152,	115,	595,	999,	455,	526,	736,	650,	464,	568,	770,	278,	968,	244,	671,	316,	312},
		{762,	933,	1074,	440,	674,	1020,	546,	437,	585,	974,	928,	696,	897,	98,		678,	1120,	945,	569,	908,	807,	846,	236,	875,	352},
		{251,	422,	563,	115,	401,	621,	395,	237,	297,	663,	417,	190,	400,	609,	167,	721,	434,	58,		632,	397,	335,	333,	465,	336,	551},
		{473,	482,	219,	644,	436,	997,	939,	781,	506,	265,	713,	388,	187,	1153,	615,	1097,	129,	602,	313,	941,	209,	877,	1009,	880,	1055,	544},
		{150,	75,		219,	516,	675,	590,	796,	638,	654,	613,	306,	357,	444,	1010,	292,	690,	278,	459,	628,	611,	340,	734,	583,	694,	912,	401,	407}
	};
}
