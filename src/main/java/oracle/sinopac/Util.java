package oracle.sinopac;
import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import java.util.Vector;
import java.io.*;

public class Util {

    public static Pointer toNativeAscii(String myString) {
        Pointer m = new Memory(myString.length() + 1); // WARNING: assumes ascii-only string
        m.setString(0, myString);
        return m;
    }


    public static String toJString(Pointer pointer) {
        Vector<Byte> byteVector = new Vector<Byte>();
        byte b = '\0';
        int i = 0;
        while( (b = pointer.getByte(i)) != '\0' ) {
            byteVector.add(b);
            i++;
        }
        String s = null;
        byte[] bytes = new byte[byteVector.size()];
        i = 0;
        for(Byte by : byteVector) {
            bytes[i] = by.byteValue();
            i++;
        }
        try {
            s = new String(bytes, "Big5");
        }
        catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
