package util;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioPlayer {

    private final int BUFFER_SIZE = 128000;

    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;


    public AudioPlayer(String fileName) {
        load(fileName);
    }

    private void load(String fileName) {

        try {
            audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(fileName));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            return;
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

    }

    public void play() {
        new Thread(() -> {
            if (sourceLine != null) {
                sourceLine.start();

                int bytesRead = 0;
                byte[] byteData = new byte[BUFFER_SIZE];

                while (bytesRead != -1) {
                    try {
                        bytesRead = audioStream.read(byteData, 0, BUFFER_SIZE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (bytesRead >= 0) {
                        @SuppressWarnings("unused")
                        int bytesWritten = sourceLine.write(byteData, 0, bytesRead);
                    }
                }
                sourceLine.drain();
                sourceLine.close();
            }
        }).start();
    }
}
