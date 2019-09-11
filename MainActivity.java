package com.example.android.qr;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
public class MainActivity extends AppCompatActivity {
    Button generate;
    EditText  input;
    ImageView qr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input=findViewById(R.id.input);
        generate=findViewById(R.id.generate);
        qr=findViewById(R.id.qr);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=input.getText().toString();
                WindowManager wm=(WindowManager)getSystemService(WINDOW_SERVICE);
                Display display=wm.getDefaultDisplay();
                Point p=new Point();
                display.getSize(p);
                int x=p.x;
                int y=p.y;
                int icon=x<y? x:y;
                icon=icon*3/4;
                QRCodeEncoder qrCodeEncoder=new QRCodeEncoder(email,null,Contents.Type.TEXT,BarcodeFormat.QR_CODE.toString(),icon);
                try {
                    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                    qr.setImageBitmap(bitmap);
                }
                catch(WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
