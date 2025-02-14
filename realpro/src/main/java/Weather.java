
public class Weather {
	
	public enum Forecast{
		SUNNY, CLOUDY, RAINY;
		@Override
		public String toString() {
			return "SNOWY";
		}
	}

	public static void main(String[] args) {
		System.out.println(Forecast.SUNNY.ordinal() + " ");
		System.out.println(Forecast.valueOf("RAINY".toUpperCase()));

	}
}
