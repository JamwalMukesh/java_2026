class Second{
	public static void main(String[] args){
		int n1 = 10;
		int n2 = 43;
		System.out.println(" n1 = " + n1 + " n2 = " + n2);
		n1 = n1 ^ n2;
		n2 = n1 ^ n2;
		n1 = n1 ^ n2;
		System.out.println("After swap, n1 = " + n1 + " n2 = " + n2);
	}
}