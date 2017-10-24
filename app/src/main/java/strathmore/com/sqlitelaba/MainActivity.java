package strathmore.com.sqlitelaba;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        //DATA FOR THE CONTACTS TABLE
        Log.d("Insert:", "Inserting...");
        db.addContacts(new Contacts("Ravi", "9100000000"));
        db.addContacts(new Contacts("Srinivas", "9199999999"));
        db.addContacts(new Contacts("Tommy", "9522222222"));
        db.addContacts(new Contacts("Karthik", "9533333333"));

        //DATA FOR THE EMAILS TABLE
        Log.d("Insert: ", "Inserting...");
        db.addEmail(new Email("abc", "abc"));
        db.addEmail(new Email("efg", "efg"));
        db.addEmail(new Email("hij", "hij"));

        //DATA FOR DRINKS
        Log.d("Insert: ", "Inserting...");
        db.addDrinks(new Drinks("water"));
        db.addDrinks(new Drinks("Juice"));
        db.addDrinks(new Drinks("Pombe"));

        //for the contact
        Log.d("Reading: ", "Reading all contacts.. ");
        List<Contacts> contacts = db.getAllContacts();
        for (Contacts en : contacts) {
            String log = "Id: " + en.getId() + " ,Name: " + en.getName() + " ,Phone: " + en.getPhoneNumber();
            Log.d("Name:", log);
        }

        //for the emails
        Log.d("Reading: ", "Reading all contacts.. ");
        List<Email> email = db.getAllEmail();
        for (Email en : email) {
            String log = "Id: " + en.getEid() + " ,EName: " + en.getEname() + " ,Address: " + en.getEmailaddress();
            Log.d("EName:", log);
        }

        //for the drinks
        Log.d("Reading: ", "Reading all contacts.. ");
        List<Drinks> drinks = db.getAllDrinks();
        for (Drinks en : drinks) {
            String log = "Id: " + en.getDId() + " ,EName: " + en.getDname() ;
            Log.d("DName:", log);
        }

    }
}
