# 🛠️ Java Command Scheduler

A utility that reads scheduled commands from a file and logs their output at the correct time. Supports both one-time and recurring commands.

---

## 📁 Project Structure

```
project-root/
├── src/
│   └── com/
│       └── demo/
│           ├── CommandScheduler.java
│           ├── ScheduledCommand.java
│           └── Main.java
├── commands.txt
├── sample-output.txt
├── out/                # Compiled .class files will be here
└── README.md
```

---

## ⚙️ Features

- Supports **one-time** commands with date and time.
- Supports **recurring** commands (every *n* minutes).
- Logs execution output (or simulated command) to `sample-output.txt`.
- Cross-platform support: detects OS and formats execution accordingly.
- Graceful start/stop messages in the console.

---

## 📌 Command Formats

### One-Time Commands:
```
Minute Hour Day Month Year <command>
```
Example:
```
30 17 30 4 2025 date && echo "At Amex, We Do What's Right."
```

### Recurring Commands:
```
*/n <command>
```
Valid intervals: 1, 2, 3, 4, 5, 6, 10, 12, 15, 20, 30, 60

Example:
```
*/1 date && echo "Amex' motto is 'Don't live life without it!'"
```

---

## 🚀 How to Run

> ✅ **Base Path Assumption**: Java files are in `src/com/demo/` and use the `com.demo` package.

### 1. Compile

```bash
javac -d out src/com/demo/*.java
```

### 2. Run

```bash
java -cp out com.demo.Main
```

### 3. Files Used

- **Command file**: `commands.txt` (located in project root)
- **Output log**: `sample-output.txt`

---

## 📝 Example `commands.txt`

```text
30 17 30 4 2025 date && echo "At Amex, We Do What's Right."
*/1 date && echo "Amex' motto is 'Don't live life without it!'"
```

---

## 🧪 Sample Output (`sample-output.txt`)

```text
Timestamp: 2025-04-30T17:30
Simulated Command Execution:
date && echo "At Amex, We Do What's Right."
----
Timestamp: 2025-04-30T17:31
Simulated Command Execution:
date && echo "Amex' motto is 'Don't live life without it!'"
----
```

---

## 📌 Assumptions

- This is a **simulated execution** (command is not actually run).
- Runs continuously; terminate manually (e.g., `Ctrl+C`).
- Assumes system time is accurate.
- Commands must not contain syntax errors.

---

## 📤 Push to GitHub

```bash
git init
git add .
git commit -m "Initial commit - Command Scheduler"
git remote add origin https://github.com/Sarath-Kumar-Murugaiah/Amex-assessment.git
git push -u origin master
```

---


## 🧑‍💻 Author

Sarath Kumar Murugaiah