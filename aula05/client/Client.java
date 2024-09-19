import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final int PORT = 4567;
        final String serverName = "localhost";
        Scanner s = new Scanner(System.in);
        Socket socket = null;
        Translation request, response;
        Communication communication;

        try {
            while (true) {

                socket = new Socket(serverName, PORT);
                communication = new Communication(socket);

                System.out.println("Digite a palavra que deseja traduzir (Digite 'exit' para sair): ");
                String palavraUsuario = s.nextLine();

                if (palavraUsuario.equals("exit")) {
                    break;
                } else {
                    request = new Translation(palavraUsuario, Language.ING_PORT);
                    communication.send(request);
        
                    response = (Translation) communication.receive();
                    System.out.println("Recebido: " + response.getStatus());
                    System.out.println("Palavra traduzida: " + response.getWord());
    
                }
            }
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage()) ;
        }

        try {
            System.out.println("Encerrando cliente...");
            s.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        

    }
}
