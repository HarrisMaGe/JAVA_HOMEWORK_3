package ReadFile;

import java.io.*;

/**
 * Created by MYC on 2016/11/9.
 */
public class ReadTest {
    public byte list[] = null;
    public double newlist[] = null;

    public static int toInt(byte[] b) {
        return (int) ((b[3] << 24) + (b[2] << 16) + (b[1] << 8) + (b[0] << 0));
    }

    public static short toShort(byte[] b) {
        return (short) ((b[1] << 8) + (b[0] << 0));
    }
    //测试，读取文件
    public static byte[] read(RandomAccessFile rdf, int pos, int length) throws IOException {
        rdf.seek(pos);
        byte result[] = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = rdf.readByte();
        }
        return result;
    }
    //输出文件的头部信息
    public static void out() throws IOException {
        String filePath = "MusicDatabase/林俊杰 - 美人鱼.wav";
        File f = new File(filePath);
        RandomAccessFile rdf = null;

        rdf = new RandomAccessFile(f, "r");

        System.out.println("ChunkID: " + toInt(read(rdf, 0, 4))); // RIFF格式 = "RIFF" RIFF:  0x46464952 = 1179011410 故出现1179011410就是RIFF

        System.out.println("AudioFormat: " + toShort(read(rdf, 20, 2))); // 音频格式 1 = PCM

        System.out.println("NumChannels: " + toShort(read(rdf, 22, 2))); // 1 单声道 2 双声道

        System.out.println("SampleRate: " + toInt(read(rdf, 24, 4)));  // 采样率

        System.out.println("BitsPerSample: " + toShort(read(rdf, 34, 2)));  // 位深度
        rdf.close();


    }

    public void setArray() throws IOException {
        File file = new File("MusicDatabase/周杰伦 - 烟花易冷_10秒.wav");
        BufferedInputStream instream = new BufferedInputStream(new FileInputStream(file));
        int length = (int) file.length();
        this.list = new byte[length];
        instream.read(this.list);
        this.newlist = new double[(length - 46) / 2];
        System.out.println((int) file.length());
        System.out.println(instream.available());
    }

    public static void ByteArray2DoubleArray(double[] doubleArray, byte[] byteArray) {
        for (int i = 0; i < doubleArray.length; i++) {
            byte bl = byteArray[2 * i];
            byte bh = byteArray[2 * i + 1];

            short s = (short) ((bh & 0x00FF) << 8 | bl & 0x00FF);
            /**
             * Java中short是2字节 1字节是8bit 加上“& 0x00FF”是为了把复数前面的“很多个F”去掉
             * 只取后8位的数据 防止相互影响
             */

            //System.out.println("s_" + s);

            doubleArray[i] = s / 32768f; // 32768 = 2^15

        }
    }


    public static void main(String[] args) throws IOException{
        out();
        //read.setArray();
        //read.ByteArray2DoubleArray(read.newlist,read.list);
        //PrintWriter out = new PrintWriter(new FileWriter(new File("test(1).csv")));
        //for (int i=0; i < read.newlist.length; ++i) {
        //    out.printf("%.2f,\n", read.newlist[i]);
        //}
        Read read = new Read();
        read.getDoubles("MusicDatabase/林俊杰 - 美人鱼.wav");
    }
}
