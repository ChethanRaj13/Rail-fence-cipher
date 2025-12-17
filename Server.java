import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        int port = 4000;

        try {
            DatagramSocket socket = new DatagramSocket(port);
            Scanner scanner = new Scanner(System.in);

            System.out.println("UDP Server started. Waiting for client...");

            // Receive initial packet to get client address
            byte[] buffer = new byte[1024];
            DatagramPacket receivePacket =
                    new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePacket);

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            System.out.println("Client connected.");

            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length,
                                clientAddress, clientPort);

                socket.send(sendPacket);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            scanner.close();
            System.out.println("Server stopped.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
