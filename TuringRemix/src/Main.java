import Model.TuringMachine;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main (String[] args) {
        int totalFramesRead = 0;
        File fileIn = new File("C:\\Users\\alexa\\Downloads\\Nice-intro-music.wav"); //EDIT INPUT FILE HERE
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileIn);
            AudioFormat format = audioInputStream.getFormat();
            long frameLength = audioInputStream.getFrameLength();

            int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
            if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
                bytesPerFrame = 1;
            }
            int numBytes = 1024*bytesPerFrame;
            byte[] audioBytes = new byte[numBytes];
            try {
                int numBytesRead = 0;
                int numFramesRead = 0;
                int[] intBytes = new int[numBytes];
                ArrayList<Byte> totalArray = new ArrayList();
                int k = 0;
                while ((numBytesRead = audioInputStream.read(audioBytes)) != -1) {
                    numFramesRead = numBytesRead / bytesPerFrame;
                    totalFramesRead += numFramesRead;
                    ArrayList<Byte> updatedArray = new ArrayList();

                    // convert audioBytes to integer array
                    for (int i = 0; i < audioBytes.length; i++) {
                        intBytes[i] = (int) audioBytes[i];
                    }


                    //run turing machine on intBytes
                    TuringMachine tm = new TuringMachine(4); // EDIT NUMBER OF STATES HERE!!
                    int[] intBytes2 = tm.run(intBytes);

                    //convert intBytes into our final byte array.
                    for (int j = 0; j < intBytes2.length; j++) {
                        updatedArray.add((byte) intBytes2[j]);
                    }

                    totalArray.addAll(updatedArray);
                    System.out.println(Arrays.toString(audioBytes));
                    System.out.println((updatedArray));
                    System.out.println(compareArrays(audioBytes, intBytes2));

                    k++;
                }
                byte[] finalArray = new byte[totalArray.size()];
                for (int j = 0; j < finalArray.length; j++) {
                    finalArray[j] = totalArray.get(j);
                }
                InputStream targetStream = new ByteArrayInputStream(finalArray);
                AudioInputStream myAudioInputStream = new AudioInputStream(targetStream, format, frameLength);
                File fileOut = new File("C:\\Users\\alexa\\Downloads\\ill-gotten-gains\\screwed-up-file.wav");

                AudioSystem.write(myAudioInputStream, AudioFileFormat.Type.WAVE, fileOut);

            } catch (Exception ex) {
                System.out.println("Error 1");
            }
        } catch (Exception e) {
            System.out.println("Error 2");
        }
    }

    public static int compareArrays(byte[] a1, int[] a2) {
        int count = 0;
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != (byte) a2[i]) {
                count += 1;
            }
        }

        return count;
    }

    public static int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }
}
