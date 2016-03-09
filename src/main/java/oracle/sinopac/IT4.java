package oracle.sinopac;
import com.sun.jna.win32.*;
import com.sun.jna.*;
import com.sun.jna.platform.win32.*;

public interface IT4 extends StdCallLibrary {
    public abstract Pointer init_t4(Pointer id, Pointer password, Pointer path);
    public abstract Pointer show_list();
    public abstract Pointer show_ip();
    public abstract Pointer show_version();
    public abstract Pointer fo_unsettled_qry(Pointer flag, Pointer leng, Pointer next, Pointer prev,
        Pointer gubn, Pointer group_name, Pointer branch, Pointer account, Pointer type_1, Pointer type_2, Pointer time_out);
    public abstract Pointer get_response_log();
    public abstract int check_response_buffer();
    public abstract Pointer add_acc_ca(Pointer branch, Pointer account, Pointer id, Pointer caPath, Pointer password);
    public abstract Pointer verify_ca_pass(Pointer branch, Pointer account);
    public abstract Pointer future_order(Pointer buyOrSell, Pointer branch, Pointer account, Pointer future_id,
        Pointer price, Pointer amount, Pointer  priceType, Pointer ordType, Pointer octType);
    public abstract Pointer future_cancel(Pointer branch, Pointer account, Pointer future_id, Pointer ord_seq,
        Pointer ord_no, Pointer octtype, Pointer pre_order);
    public abstract Pointer future_change (Pointer org_seqno, Pointer org_ordno, Pointer branch, Pointer account,
        Pointer commodity, Pointer new_price, Pointer pre_order);
    public abstract Pointer fo_order_qry2(Pointer branch, Pointer account, Pointer code, Pointer ord_match_flag,
        Pointer ord_type, Pointer oct_type, Pointer daily, Pointer start_date, Pointer end_date, Pointer preorder, Pointer source);

}
