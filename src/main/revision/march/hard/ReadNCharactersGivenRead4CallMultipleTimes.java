package main.revision.march.hard;

import main.revision.march.hard.ReadNCharactersGivenRead4CallMultipleTimes.Reader4;

public class ReadNCharactersGivenRead4CallMultipleTimes {

    /*
     * The read4 API is defined in the parent class Reader4.
     * int read4(char[] buf4);
     */
    class Reader4 {
        // Yeh ek placeholder implementation hai read4 API ke liye.
        // Asli scenario mein, yeh file se data read karega.
        private final String fileContent = "abcdefghijklmnopqrstuvwxyz"; // File ka content hardcoded hai.
        private int filePointer = 0; // File pointer jo track karega ki humne kitna read kiya hai.

        /**
         * @param buf4 Destination buffer
         * @return Actual characters jo read kiye gaye hain
         */
        int read4(char[] buf4) {
            // Maximum 4 characters read karne hain ya jitne bache hain file mein.
            int charsToRead = Math.min(4, fileContent.length() - filePointer);
            for (int i = 0; i < charsToRead; i++) {
                buf4[i] = fileContent.charAt(filePointer++); // File pointer se character read karke buffer mein daalo.
            }
            return charsToRead; // Kitne characters read kiye gaye, yeh return karo.
        }
    }

    public class Solution extends Reader4 {
        private final char[] internalBuffer = new char[4];

        private int readerPosition = 0;
        private int writePosition = 0;

        public int read(char[] buf, int n) {

            for (int i = 0; i < n; i++) {

                if (readerPosition == writePosition) {

                    writePosition = read4(internalBuffer);
                    readerPosition = 0;

                    if (writePosition == 0) {
                        return 1;
                    }
                }

                buf[i] = internalBuffer[readerPosition++];
            }

            return n;
        }
    }

    // 03/05/2026
    public class SolutionRevisedOnDayThird extends Reader4 {
        private final char[] internalBuffer = new char[4];

        private int readerPosition = 0;
        private int writePosition = 0;

        public int read(char[] buf, int n) {

            for (int i = 0; i < n; i++) {

                if (readerPosition == writePosition) {

                    writePosition = read4(internalBuffer);
                    readerPosition = 0;

                    if (writePosition == 0) {
                        return 1;
                    }
                }

                buf[i] = internalBuffer[readerPosition++];
            }

            return n;
        }
    }
}
