package MileLine.pokus;

import MileLine.pokus.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class MileLineActivity extends Activity {
    /** Called when the activity is first created. */
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    
//        final Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
//        final ImageView newImg = (ImageView)findViewById(R.id.imageView1);
//        newImg.setImageBitmap(bm);
//        newImg.setVisibility(View.INVISIBLE);
//        
//        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox1);
//        checkbox.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on clicks, depending on whether it's now checked
//                if (((CheckBox) v).isChecked()) {
//                    Toast.makeText(MileLineActivity.this, "Selected", Toast.LENGTH_SHORT).show();
//                    //newImg.setImageBitmap(bm);
//                    newImg.setVisibility(View.VISIBLE);
//                } else {
//                    Toast.makeText(MileLineActivity.this, "Not selected", Toast.LENGTH_SHORT).show();
//                    newImg.setVisibility(View.INVISIBLE);
//                }
//            }
//        });   
       
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionText:    // Toast.makeText(this, "You pressed Options!", Toast.LENGTH_LONG).show();
            					setContentView(R.layout.options);
//            					Button button2 = (Button) findViewById(R.id.addSemestrButton);
//            			        Button button3 = (Button) findViewById(R.id.addSubjectButton);
//            			        button2.setOnClickListener(new OnClickListener() {
//            						public void onClick(View v) {
//            							// TODO Auto-generated method stub
//            							if(((Button) v).isPressed()){
//            							setContentView(R.layout.main);	
//            							}
//            						}
//            			        	
//            			        });
//            			        button3.setOnClickListener(new OnClickListener() {
//            						public void onClick(View v) {
//            							// TODO Auto-generated method stub
//            							if(((Button) v).isPressed()){
//            							setContentView(R.layout.main);	
//            							}
//            						}
//            			        	
//            			        });
            					break;
            case R.id.aboutText:    //Toast.makeText(this, "You pressed About!", Toast.LENGTH_LONG).show();
				setContentView(R.layout.about);
//				Button button = (Button) findViewById(R.id.backAbout);
//				button.setOnClickListener(new OnClickListener() {
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						if(((Button) v).isPressed()){
//						setContentView(R.layout.main);	
//						}
//					}
//		        	
//		        });
				break;
            case R.id.exitText: 	// Toast.makeText(this, "You pressed Exit!", Toast.LENGTH_LONG).show();
				moveTaskToBack(true);
				break;
        }
        return true;
    }
    
    
    public void mainClickHandler(View v){
    	if(((Button) v).isPressed()){
			setContentView(R.layout.main);	
			}
    }

}