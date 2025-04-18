public class main {

	public static void main(String[] args) {
		funcionEmpirica f = new funcionEmpirica(6);
		
		f.cargarListaPtos();
		if(f.controlarPtos()) {
			f.mostrarListaPtos();
			f.linealizacion("Potencial");
			System.out.println("\nLuego de linealizar:");
			//f.puntosSeleccionados(1,3);
			f.desvios();
			//f.minimosCuadrados();
			System.out.println("\nParámetros alfa: "+f.getA()); // Valor linealidazos
			System.out.println("\nParámetros beta: "+f.getB()); // Valor Linealizados
			// se requiere convertirlos para encontrar a y b correctos
		}else System.out.println("fallo");
	}

}
