package com.example.viewsanduielement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.viewsanduielement.databinding.ActivityExpenseBinding;

public class ActivityExpense extends AppCompatActivity {
ActivityExpenseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Integer[] numbers = new Integer[15];K
        for (int i = 0; i < 15; i++) {
            numbers[i] = i;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.buttonSave.setOnClickListener(this::fnSaveExp);
        binding.imgExp.setOnClickListener(this::fnTakePic);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        binding.imgExp.setImageBitmap(bitmap);
    }

    private void fnTakePic(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }



    private void fnSaveExp(View view){
        int qtyItem = (int) binding.spinner.getSelectedItemId();

        binding.editTexTotal.setText(""+qtyItem * Float.parseFloat(binding.edtExpValue.toString()));
}


}