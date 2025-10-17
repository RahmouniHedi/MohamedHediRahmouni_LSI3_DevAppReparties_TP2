package serverPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Le serveur est en attente de connexion...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté depuis l'adresse : " + clientSocket.getRemoteSocketAddress());

            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true); // 'true' pour autoFlush

            String op1Str, operator, op2Str;

            while ((op1Str = reader.readLine()) != null &&
                    (operator = reader.readLine()) != null &&
                    (op2Str = reader.readLine()) != null) {

                try {
                    double op1 = Double.parseDouble(op1Str);
                    double op2 = Double.parseDouble(op2Str);
                    double result = 0;
                    boolean error = false;

                    switch (operator) {
                        case "+":
                            result = op1 + op2;
                            break;
                        case "-":
                            result = op1 - op2;
                            break;
                        case "*":
                            result = op1 * op2;
                            break;
                        case "/":
                            if (op2 != 0) {
                                result = op1 / op2;
                            } else {
                                writer.println("Erreur : Division par zéro !");
                                error = true;
                            }
                            break;
                        default:
                            writer.println("Erreur : Opérateur non valide !");
                            error = true;
                            break;
                    }

                    if (!error) {
                        System.out.println("Calcul : " + op1 + " " + operator + " " + op2 + " = " + result);
                        writer.println("Résultat = " + result);
                    }

                } catch (NumberFormatException e) {
                    writer.println("Erreur : Les opérandes doivent être des nombres.");
                }
            }

            System.out.println("Le client s'est déconnecté. Fermeture du serveur.");
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Erreur sur le serveur : " + e.getMessage());
        }
    }
}