package Fingerprint;
import java.util.ArrayList;


public class Fingerprint {



    public ArrayList<double[]> read_data = new ArrayList<>();
    public static final double scaling = FFT.WINDOW_SIZE / 44100.0;

    public static final int N = 3;

    private ArrayList<int[]> constel_data = new ArrayList<>();
    private int id;

    /**
     * For songs about to add into DB
     * @param id
     */
    public Fingerprint(int id) {
        this.id = id;
    }

    /**
     * For songs about to be searched
     */
    public Fingerprint() {
        this.id = -1;
    }



    //将文件处理传所得到的长double数组转化成长度为4096的多个数组
    public void divide(double[] readData){
        for(int i = 0; i < readData.length/4096; i++){
            double[] a = new double[4096];
            for(int j = 0; j < 4096; j++){
                a[j] = readData[i+i*4096];
            }
            read_data.add(a);
        }

        for(int i = 0; i < read_data.size(); i++){
            this.append(read_data.get(i));
        }
    }

    /**
     * Append a column of frequency peaks to the constellation map.
     * A frequency peak is a frequency value whose amplitude is the highest among
     * all frequencies in a frequency interval.
     *
     * @param freqDomain The frequency domain generated by FFT.
     */

    public void append(double[] freqDomain) {
        int[] freqPeaks = new int[N];
        int freq = 0;
        /**
         * TODO: Either find N frequencies with the highest amplitude(energy),
         * or find the frequency with the max energy within each interval.
         */
        for(int i = 0; i < freqDomain.length; i++){
            freq = (int)freqDomain[i];
            if(freq > freqPeaks[0]){
                freqPeaks[0] = freq;
            }else if(freq > freqPeaks[1]){
                freqPeaks[1] = freq;
            }else if(freq > freqPeaks[2]){
                freqPeaks[2] = freq;
            }
        }

        constel_data.add(freqPeaks);
    }



    /**
     * Generate fingerprints using Combinational Hash.
     * For each frequency peak, generate 6 fingerprints with its 6 successors.
     *
     * @return
     */
    public ArrayList<ShazamHash> combineHash() {
        if (constel_data.size() < 3)
            throw new RuntimeException("Too few frequency peaks");
        ArrayList<ShazamHash> hashes = new ArrayList<>();
        for (int i = 0; i < constel_data.size() - 2; ++i) {
            for (int k = 0; k < N; ++k) {

                // "Combine" with all peak frequencies inside its next two frames.
                for (int j = 1; j <= 2; ++j) {
                    for (int kk = 1; kk < N; ++kk) {
                        ShazamHash hash = new ShazamHash();
                        hash.f1 = (short) constel_data.get(i)[k];
                        hash.f2 = (short) constel_data.get(i + j)[kk];
                        hash.dt = (short) j;
                        hash.offset = i;
                        hash.id = id;
                        hashes.add(hash);
                    }
                }
            }
        }
        return hashes;
    }

}
