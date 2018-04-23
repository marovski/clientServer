import java.net.*;
import java.io.*;

public class presencesServer {
	static int DEFAULT_PORT=2000;
	
	public static void main(String[] args) {
		int port=DEFAULT_PORT;
		Presences presences = new Presences();
		
		// Cria um ServerSocket e associa-o a porta especificada na variavel port
	
		ServerSocket servidor = null; 
	
		try	{ 
			servidor = new ServerSocket(port);
		} catch (Exception e) { 
			System.err.println("erro ao criar socket servidor...");
			e.printStackTrace();
			System.exit(-1);
		}
		
		//escrever mensagem a dizer "Servidor a' espera de ligacoes no porto ..."
	
		System.out.println("Servidor a' espera de ligacoes no porto " + port);
		
		while(true) {
			try {
				//	Aguarda que seja estabelecida alguma ligacao e quando isso acontecer cria um socket
				// chamado ligacao
				Socket ligacao = servidor.accept();
				
				GetPresencesRequestHandler t = new GetPresencesRequestHandler(ligacao, presences);
				t.start();
				
			} catch (IOException e) {
				System.out.println("Erro na execucao do servidor: "+e);
				System.exit(1);
			}
		}
	}
}
