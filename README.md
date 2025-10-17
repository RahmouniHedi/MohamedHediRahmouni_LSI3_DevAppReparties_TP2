# 🧮 Java Client-Server Calculator

This project demonstrates a simple **client-server architecture** in Java where a client sends arithmetic operations to a server, and the server processes and returns the result.

## 📦 Project Structure

```
.
├── serverPackage
│   └── server.java
└── clientPackage
    └── client.java
```

## 🚀 How It Works

- The **server** listens on port `1234` and waits for a client to connect.
- The **client** connects to the server and sends arithmetic operations in the format:  
  ```
  operand1 operator operand2
  ```
  Example: `5 * 3`
- The server parses the input, performs the calculation, and sends the result back to the client.

## ✅ Supported Operations

- Addition (`+`)
- Subtraction (`-`)
- Multiplication (`*`)
- Division (`/`)

## ⚠️ Error Handling

- Division by zero returns an error message.
- Invalid operators or non-numeric operands are handled gracefully.

## 🛠️ How to Run

### 1. Compile the Java files

```bash
javac serverPackage/server.java
javac clientPackage/client.java
```

### 2. Start the server

```bash
java serverPackage.server
```

### 3. Start the client (in a separate terminal)

```bash
java clientPackage.client
```

## 📷 Example Interaction

```
Client: 10 / 2
Server: Résultat = 5.0

Client: 5 ^ 2
Server: Erreur : Opérateur non valide !

Client: 8 / 0
Server: Erreur : Division par zéro !
```

