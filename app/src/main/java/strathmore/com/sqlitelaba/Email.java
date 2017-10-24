package strathmore.com.sqlitelaba;

import static android.R.attr.id;

/**
 * Created by HP250 on 24/10/2017.
 */

public class Email {
    int _id;
    String _name;
    String _email_address;

    //empty constructor
    public Email(){}

    //First constructor
    public Email (int id, String name, String _email_address) {

        this._id = id;
        this._name = name;
        this._email_address = _email_address;
    }

    //second constructor
    public Email(String name, String _email_address){
        this. _name = name;
        this. _email_address= _email_address;
    }


    public int getEid() {return _id;}

    public void setEid(int _id) {this._id = _id;}

    public String getEname() {return _name;}

    public void setEname(String _name) {this._name = _name;}

    public String getEmailaddress() {return _email_address;}

    public void setEmailaddress(String _email_address) {this._email_address = _email_address;}
}
