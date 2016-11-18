package Matching;

import Database.*;
import Fingerprint.*;
import ReadFile.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by 冬至节 on 2016/11/12.
 */
public class GetFingerprint {
    //get a music file here
    Read read=new Read();
    Fingerprint fp=new Fingerprint();

    /*public ArrayList<ShazamHash> getFingerprint(String path) throws IOException {
        return fp.combineHash(fp.divide(read.getDoubles(path)));
    }*/

    /*public int[][] Hashes(String path) throws IOException{
        ArrayList<ShazamHash> hashes =getFingerprint(path);
        int[][] h=new int[hashes.size()][3];
        for (int i=0;i<hashes.size();i++){
            h[i][1]= (hashes.get(i).dt<<18)|(hashes.get(i).f1<<9)|(hashes.get(i).f2);
            h[i][2]=hashes.get(i).id;
            h[i][3]=hashes.get(i).offset;
        }
        return h;
    }*/
}
