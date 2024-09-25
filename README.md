# Player Communication Program-360t Java Assessment

**Note:** The program is using Linux pipes to communicate between different processes, so it is tested only on Linux and probably won't work on Windows.

## Steps to Run the Program

Follow these steps to set up and run the program:

1. **Create Named Pipes**
   ```bash
   mkfifo Sender2Receiver
   mkfifo Receiver2Sender
2. **Compile Java files 
   ```bash
   javac -d . src/main/java/org/src/*.java  # Compiles Java files in the specified directory
3. **Run the main class
    ```bash
   java org.src.Main
