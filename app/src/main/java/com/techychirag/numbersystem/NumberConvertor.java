package com.techychirag.numbersystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.techychirag.numbersystem.R.id.btnClear;

public class NumberConvertor extends AppCompatActivity
{
    private EditText editTextDecimal;
    private EditText editTextBinary;
    private EditText editTextOctal;
    private EditText editTextHexa;
    private Button buttonclear;
    private String value;
    private String Empty="";

    private TextWatcher textWatcher;

    private View.OnClickListener copyClickListener;

    private View.OnFocusChangeListener onFocusChangeListener;
    private int focusViewID;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_convertor);



     editTextDecimal=findViewById(R.id.edtDec);
     editTextBinary=findViewById(R.id.edtBin);
     editTextOctal=findViewById(R.id.edtOct);
     editTextHexa=findViewById(R.id.edtHex);
     buttonclear=findViewById(R.id.btnClear);

   buttonclear.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          clearFields();
                                      }
                                  }
   );

     textWatcher=new TextWatcher()
     {
         @Override
         public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
         }

         @Override
         public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
         {
             value= ((EditText) findViewById(focusViewID)).getText().toString().trim();

             if(value.length()>0)
             {
                 convertNumber();
             }
         }
         @Override
         public void afterTextChanged(Editable editable) {
         }
     };


     onFocusChangeListener =new View.OnFocusChangeListener() {
         @Override
         public void onFocusChange(View view, boolean b) {

             if(b)
             {
                 focusViewID = view.getId();

                 ((EditText) findViewById(focusViewID)).addTextChangedListener(textWatcher);


                 GradientDrawable gradientDrawable =new GradientDrawable(
                         GradientDrawable.Orientation.TR_BL,
                         new int[] {Color.parseColor("#03DAC5"),Color.parseColor("#C2185B")}

                 );

                 gradientDrawable.setShape(GradientDrawable.RECTANGLE);

                gradientDrawable.setCornerRadius(10);

                 if(focusViewID==R.id.edtDec){
                     gradientDrawable.setStroke(8, ContextCompat.getColor(getApplicationContext(), android.R.color.holo_blue_bright));
                 }
                 else
                 {
                     gradientDrawable.setStroke(8, ContextCompat.getColor(getApplicationContext(), android.R.color.holo_blue_bright));
                 }

                 view.setBackground(gradientDrawable);
             }
             else
             {
                 ((EditText) findViewById(focusViewID)).removeTextChangedListener(textWatcher);
                 if(focusViewID==R.id.edtDec){
                     view.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.viewdesign));
                 }
                 else
                 {
                     view.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.viewdesign));
                 }
             }

         }
     };

     editTextDecimal.setOnFocusChangeListener(onFocusChangeListener);
     editTextBinary.setOnFocusChangeListener(onFocusChangeListener);
     editTextOctal.setOnFocusChangeListener(onFocusChangeListener);
     editTextHexa.setOnFocusChangeListener(onFocusChangeListener);


     copyClickListener = new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             ClipboardManager clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
             ClipData clipData=null;

             switch (view.getId())
             {
                 case R.id.copyDecimal:

                     clipData = ClipData.newPlainText("Copied Data",editTextDecimal.getText().toString());
                     if(Empty.equals(editTextDecimal.getText().toString()))
                         Toast.makeText(NumberConvertor.this, "Please Enter Data!", Toast.LENGTH_SHORT).show();
                     else
                         Toast.makeText(NumberConvertor.this, "Data Copied...", Toast.LENGTH_SHORT).show();

                     break;

                 case R.id.copyBin:
                     clipData = ClipData.newPlainText("Copied Data",editTextBinary.getText().toString());
                     if(Empty.equals(editTextBinary.getText().toString()))
                         Toast.makeText(NumberConvertor.this, "Please Enter Data!", Toast.LENGTH_SHORT).show();
                     else
                         Toast.makeText(NumberConvertor.this, "Data Copied...", Toast.LENGTH_SHORT).show();
                     break;

                 case R.id.copyOct:
                     clipData = ClipData.newPlainText("Copied Data",editTextOctal.getText().toString());
                     if(Empty.equals(editTextOctal.getText().toString()))
                         Toast.makeText(NumberConvertor.this, "Please Enter Data!", Toast.LENGTH_SHORT).show();
                     else
                         Toast.makeText(NumberConvertor.this, "Data Copied...", Toast.LENGTH_SHORT).show();
                     break;

                 case R.id.copyHex:
                     clipData = ClipData.newPlainText("Copied Data",editTextHexa.getText().toString());
                     if(Empty.equals(editTextHexa.getText().toString()))
                         Toast.makeText(NumberConvertor.this, "Please Enter Data!", Toast.LENGTH_SHORT).show();
                     else
                         Toast.makeText(NumberConvertor.this, "Data Copied...", Toast.LENGTH_SHORT).show();
                     break;
             }

             clipboardManager.setPrimaryClip(clipData);

         }
     };

     findViewById(R.id.copyDecimal).setOnClickListener(copyClickListener);
     findViewById(R.id.copyBin).setOnClickListener(copyClickListener);

     findViewById(R.id.copyOct).setOnClickListener(copyClickListener);
     findViewById(R.id.copyHex).setOnClickListener(copyClickListener);

    }


    private void clearFields()
    {
        editTextDecimal.setText("");
        editTextBinary.setText("");
        editTextHexa.setText("");
        editTextOctal.setText("");
    }

    private void convertNumber()
    {
        try {
            long num =0;



            switch(focusViewID)
            {
                case R.id.edtDec:
                    num =Long.parseLong(value);
                    editTextBinary.setText(String.valueOf(Long.toBinaryString(num)));
                    editTextOctal.setText(String.valueOf(Long.toOctalString(num)));
                    editTextHexa.setText(String.valueOf(Long.toHexString(num)));
                    break;

                case R.id.edtBin:
                    num =Long.parseLong(value,2);
                    editTextDecimal.setText(String.valueOf(num));
                    editTextOctal.setText(String.valueOf(Long.toOctalString(num)));
                    editTextHexa.setText(String.valueOf(Long.toHexString(num)));
                    break;
                case R.id.edtOct:
                    num =Long.parseLong(value,8);
                    editTextDecimal.setText(String.valueOf(num));
                    editTextBinary.setText(String.valueOf(Long.toBinaryString(num)));
                    editTextHexa.setText(String.valueOf(Long.toHexString(num)));
                    break;
                case R.id.edtHex:
                    num =Long.parseLong(value,16);
                    editTextDecimal.setText(String.valueOf(num));
                    editTextBinary.setText(String.valueOf(Long.toBinaryString(num)));
                    editTextOctal.setText(String.valueOf(Long.toOctalString(num)));
                    break;

            }

        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}