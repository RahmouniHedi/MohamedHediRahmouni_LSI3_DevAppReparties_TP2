# Java Socket-Based Calculator (Object Mode)

## 📌 Overview
This project demonstrates a **client-server application** in Java using **sockets** and **object serialization**. The client sends mathematical operations to the server as serialized objects, and the server processes the operation and returns the result.

---

## ✅ Features
- **Client-Server Communication** using TCP sockets.
- **Object Serialization** for sending operations.
- Supports basic arithmetic operations:
  - Addition (`+`)
  - Subtraction (`-`)
  - Multiplication (`*`)
  - Division (`/`) with zero-check.
- Handles invalid input and operators gracefully.

---

## 🛠️ Technologies Used
- **Java SE**
- **Socket Programming**
- **Object Streams (ObjectInputStream & ObjectOutputStream)**

---

## 📂 Project Structure
```
├── clientPackage
│   └── client.java        # Client-side code
├── serverPackage
│   └── server.java        # Server-side code
└── objectPackage
    └── Operation.java     # Serializable class for operations
```

---

## 🚀 How to Run
### 1. Compile the project
```bash
javac clientPackage/client.java serverPackage/server.java objectPackage/Operation.java
```

### 2. Start the server
```bash
java serverPackage.server
```

### 3. Start the client
```bash
java clientPackage.client
```

---

## 🖥️ Usage
- After starting both server and client:
  - Enter an operation in the format:
    ```
    number operator number
    ```
    Example:
    ```
    5 * 2
    ```
  - Type `exit` to close the client.

---

## ⚠️ Error Handling
- Invalid format → Displays: `Format incorrect. Utilisez : nombre opérateur nombre`
- Non-numeric operands → Displays: `Les opérandes doivent être des nombres.`
- Division by zero → Displays: `Erreur : Division par zéro !`
- Invalid operator → Displays: `Erreur : Opérateur non valide !`

---

## 📌 Example Output
**Client:**
```
Entrez une opération (ex: 5 * 2) ou 'exit' pour quitter :
5 * 2
Serveur : Résultat = 10.0
```

**Server:**
```
Client connecté depuis : /127.0.0.1
Calcul : 5.0 * 2.0 = 10.0
```

---

