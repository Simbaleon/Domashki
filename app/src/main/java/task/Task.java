package task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Task {
      public static void showMessage(Context context, String message)
      {
    	  AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
    	  dlgAlert.setMessage(message);
    	  dlgAlert.setTitle("Игра окончена");
    	  dlgAlert.setPositiveButton("Да",
    			    new DialogInterface.OnClickListener() {
    			        public void onClick(DialogInterface dialog, int which) {
    			         
    			        }
    			    });
		  dlgAlert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int which) {
				  System.exit(0);
			  }
		  });
		  dlgAlert.setCancelable(true);

		  dlgAlert.create().show();
      }
      
}
