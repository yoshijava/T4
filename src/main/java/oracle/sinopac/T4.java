package oracle.sinopac;
import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import java.io.*;
import java.util.*;
import oracle.common.*;
import java.text.*;
import py4j.GatewayServer;

public class T4 {
    private final String workingDirectory = System.getProperty("user.dir");
    private final String libPath = workingDirectory +"\\libs\\Sinopac\\t4.dll";
    private final IT4 t4 = (IT4) Native.loadLibrary(libPath, IT4.class);;
    private String password;
    private String id;
    private String branch;
    private String account;
    private static final T4 instance = new T4();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    private Date today = new Date();

    public T4() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("account.ini"));
            id = br.readLine();
            password = br.readLine();
            branch = br.readLine();
            account = br.readLine();
            br.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        Pointer pId = Util.toNativeAscii(id);
        Pointer pPassword = Util.toNativeAscii(password);
        Pointer pEmpty = Util.toNativeAscii("");
        Pointer pAccount = Util.toNativeAscii(account);
        Pointer pLibPath = Util.toNativeAscii(libPath);
        Pointer pBranch = Util.toNativeAscii(branch);
        Pointer pWorkingDirectory = Util.toNativeAscii(workingDirectory);
        String ret = Util.toJString(t4.init_t4(pId, pPassword, pEmpty));
        System.out.println(ret);
    }

    public String showList() {
        return Util.toJString(t4.show_list());
    }

    public String showVersion() {
        return Util.toJString(t4.show_version());
    }

    public String verifyCAPass() {
        String ret = Util.toJString(t4.verify_ca_pass(Util.toNativeAscii(branch), Util.toNativeAscii(account)));
        return ret;
    }

    public String addAccCA() {
        return Util.toJString(t4.add_acc_ca(
            Util.toNativeAscii(branch),
            Util.toNativeAscii(account),
            Util.toNativeAscii(id),
            Util.toNativeAscii(workingDirectory),
            Util.toNativeAscii(id)
            )
        );
    }

    public String queryUnsettled() {
        Pointer pBranch = Util.toNativeAscii(branch);
        Pointer pAccount = Util.toNativeAscii(account);
        return Util.toJString(
            t4.fo_unsettled_qry(
                Util.toNativeAscii("0000"),
                Util.toNativeAscii("0004"),
                Util.toNativeAscii("0000"),
                Util.toNativeAscii("0000"),
                Util.toNativeAscii("0"),
                Util.toNativeAscii(""),
                pBranch,
                pAccount,
                Util.toNativeAscii("1"),
                Util.toNativeAscii("0"),
                Util.toNativeAscii("1")
            )
        );
    }

    public boolean sell(String price, String amount) {
        return buyOrSell("S", price, amount);
    }

    public boolean buy(String price, String amount) {
        return buyOrSell("B", price, amount);
    }

    private boolean buyOrSell(String buyOrSell, String price, String amount) {
        String ret = Util.toJString(
            t4.future_order(
                Util.toNativeAscii(buyOrSell),
                Util.toNativeAscii(branch),
                Util.toNativeAscii(account),
                Util.toNativeAscii(ConfigurableParameters.COMMODITY),
                Util.toNativeAscii("00" + price),
                Util.toNativeAscii("00" + amount),
                Util.toNativeAscii("LMT"),
                Util.toNativeAscii("IOC"),
                Util.toNativeAscii(" ") // 0 for new lot; 1 for offseting lot; space for auto; 6 for only today
            )
        );
        System.out.println(ret);
        if(ret.contains(ConfigurableParameters.COMMODITY) && ret.contains(buyOrSell+"00"+price)) {
            return true;
        }
        else {
            return false;
        }
    }

    public T4 getInstance() {
        return this;
    }

    public static void main(String[] args) throws Exception {
        // makeMTXFutureTicket("B", "8473", "1");
        // makeMTXFutureTicket("S", "8300", "1");
        // System.out.println(">" + queryQueuingOrder() + "<");
        // String ret3 = queryUnsettled();
        // System.out.println(">" + ret3 + "<");
        // System.out.println("----");
        // String ret = T4.queryQueuingOrder();
        // System.out.println(ret);

        System.out.println(instance.addAccCA());
        System.out.println(instance.verifyCAPass()); // put your Sinopac.pfx in the working directory
        GatewayServer gatewayServer = new GatewayServer(instance);

        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }
}
