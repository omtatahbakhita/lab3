package strathmore.com.sqlitelaba;

import static android.R.attr.id;

/**
 * Created by HP250 on 24/10/2017.
 */

public class Drinks {
    int _id;
    String _name;

    //EMPTY CONSTRUCTOR
    public Drinks(){

    }

    //first constructor
    public Drinks (int id, String name) {

        this._id = id;
        this._name = name;
        }

    //second constructor
    public Drinks(String name){
        this. _name = name;
    }

    public int getDId(){
        return this._id;
    }

    public void setDId(int _id ){this._id= id;}

    public String getDname() {return _name;}

    public void setDname(String _name) {this._name = _name;}
}

