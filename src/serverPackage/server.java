package serverPackage;

import objectPackage.Operation;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Serveur en attente de connexion...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté depuis : " + clientSocket.getRemoteSocketAddress());

            // Streams objet
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            Operation operation;

            while ((operation = (Operation) objectInputStream.readObject()) != null) {
                double op1 = operation.getOperand1();
                String operator = operation.getOperator();
                double op2 = operation.getOperand2();

                double result = 0;
                boolean error = false;
                String response;

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
                            response = "Erreur : Division par zéro !";
                            objectOutputStream.writeObject(response);
                            objectOutputStream.flush();
                            error = true;
                        }
                        break;
                    default:
                        response = "Erreur : Opérateur non valide !";
                        objectOutputStream.writeObject(response);
                        objectOutputStream.flush();
                        error = true;
                        break;
                }

                if (!error) {
                    System.out.println("Calcul : " + op1 + " " + operator + " " + op2 + " = " + result);
                    response = "Résultat = " + result;
                    objectOutputStream.writeObject(response);
                    objectOutputStream.flush();
                }
            }

            System.out.println("Client déconnecté. Fermeture serveur.");
            clientSocket.close();
            serverSocket.close();

        } catch (EOFException e) {
            System.out.println("Le client a fermé la connexion.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }
}
