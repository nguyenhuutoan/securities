package com.realpro;

public class Xc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean canine = true, wolf = true;
		int teeth = 20;
		canine = (teeth != 10) ^ (wolf=false);
		System.out.println(canine+", "+teeth+", "+wolf);

	}

}
