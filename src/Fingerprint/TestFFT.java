package Fingerprint;

import ReadFile.Read;

import java.io.IOException;

/**
 * Created by MYC on 2016/11/17.
 */
public class TestFFT {
    public static void main(String[] args) throws IOException {
        Read reader = new Read();
        double[] data = reader.getDoubles("MusicDatabase/林俊杰 - 美人鱼.wav");
        double[] slice = new double[4096];
        for (int i = 409600; i<413696; ++i) {
            slice[i-409600]=data[i]/32768;
        }
        double[] freq = FFT.fft(slice);

        System.out.print("[");
        for (int i=0; i<4096; ++i) {
            System.out.printf("%.2f, ", slice[i]);
        }
        System.out.println("];");

        System.out.print("[");
        for (int i=0; i<4096; ++i) {
            System.out.printf("%.2f, ", freq[i]);
        }
        System.out.println("];");
    }
}
