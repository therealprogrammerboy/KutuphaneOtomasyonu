package Kutuphane;

public class Yazar {
	
	private int Yazar_No;
	private String Yazar_Adi;
	private String Yazar_Soyadi;
	
	public Yazar() {
		
	}

	public int getYazar_No() {
		return Yazar_No;
	}

	public void setYazar_No(int yazar_No) {
		Yazar_No = yazar_No;
	}

	public String getYazar_Adi() {
		return Yazar_Adi;
	}

	public void setYazar_Adi(String yazar_Adi) {
		Yazar_Adi = yazar_Adi;
	}

	public String getYazar_Soyadi() {
		return Yazar_Soyadi;
	}

	public void setYazar_Soyadi(String yazar_Soyadi) {
		Yazar_Soyadi = yazar_Soyadi;
	}

	@Override
	public String toString() {
		return  Yazar_Adi +" "+  Yazar_Soyadi ;
	}
	
	
	
	

}
