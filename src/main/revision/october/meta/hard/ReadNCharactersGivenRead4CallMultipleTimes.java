package main.revision.october.meta.hard;

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

    /**
     * The read4 API is defined in the parent class Reader4.
     * int read4(char[] buf4);
     */
    public class Solution extends Reader4 {
        private final char[] internalBuffer = new char[4]; // Ek internal buffer jo read4 se data temporarily store
                                                           // karega.
        private int readPosition = 0; // Internal buffer ka read pointer.
        private int writePosition = 0; // Internal buffer ka write pointer.

        public int read(char[] buf, int n) {
            // Step 1: Loop through jitne characters read karne hain (n).
            for (int i = 0; i < n; i++) {
                // Step 2: Agar internal buffer empty ho gaya hai, toh read4 se naye characters
                // read karo.
                if (readPosition == writePosition) {
                    writePosition = read4(internalBuffer); // read4 se data internalBuffer mein daalo.
                    readPosition = 0; // Read pointer reset karo.
                    // Step 3: Agar read4 ne kuch bhi nahi return kiya (EOF), toh jitna ab tak read
                    // kiya hai, return karo.
                    if (writePosition == 0) {
                        return i;
                    }
                }
                // Step 4: Internal buffer se ek character buf mein copy karo aur read pointer
                // increment karo.
                buf[i] = internalBuffer[readPosition++];
            }
            // Step 5: Agar n characters successfully read ho gaye, toh n return karo.
            return n;
        }
    }

    // revised on 12/15/2025
    public class SolutionRevisedOnThirdDay extends Reader4 {
        private final char[] internalBuffer = new char[4];
        private int readPosition = 0;
        private int writePosition = 0;

        public int read(char[] buf, int n) {

            for (int i = 0; i < n; i++) {
                if (readPosition == writePosition) {
                    writePosition = read4(internalBuffer);
                    readPosition = 0;
                    if (writePosition == 0) {
                        return i;
                    }
                }

                buf[i] = internalBuffer[readPosition++];
            }
            return n;
        }
    }

    // revised on 12/21/2025
    public class SolutionRevisedOnSeventhDay extends Reader4 {
        private final char[] internalBuffer = new char[4];
        private int writePosition = 0;
        private int readPosition = 0;

        public int read(char[] buf, int n) {

            for (int i = 0; i < n; i++) {

                if (writePosition == readPosition) {
                    writePosition = read4(internalBuffer);
                    readPosition = 0;

                    if (writePosition == 0) {
                        return i;
                    }
                }

                buf[i] = internalBuffer[readPosition++];
            }
            return n;
        }

    }

    // revised on 1/4/2026
    public class SolutionRevisedOnFourteenDay extends Reader4 {
        private final char[] internalBuffer = new char[4];
        private int readPosition = 0;
        private int writePosition = 0;

        public int read(char[] buf, int n) {
            for (int i = 0; i < n; i++) {

                if (readPosition == writePosition) {
                    writePosition = read4(internalBuffer);
                    readPosition = 0;

                    if (writePosition == 0) {
                        return i;
                    }
                }

                buf[i] = internalBuffer[readPosition++];
            }

            return n;
        }
    }
}
