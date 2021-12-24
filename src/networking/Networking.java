package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Networking {
	
	
	public static void main (String [] args) throws IOException {
		
		int port= 8000;
		DataInputStream in;
		DataOutputStream out;
		ServerSocket serverSocket;
		Socket socket;
		
		System.out.println("Hola perro creando la lógica del lado del servidor");
		
		
		ServerSocket serverSo = new ServerSocket(port);
		
		Socket sServer= serverSo.accept();  // 
		
		in = new DataInputStream(sServer.getInputStream());
		
		out = new DataOutputStream(sServer.getOutputStream());
		
		
		
		//Socket socket = new Socket("130.254.204.33",8000);  // Crea un nuevo socket del lado del cliente y lo conecta al socket del servido con IP
		// ingresada en el primer argumento y puerto igual al ingresado en el segundo
		
		// Para ello es entonces necesario crear el socket del lado del servidor previamente y asignarle un puerto
		
		
	}

}
