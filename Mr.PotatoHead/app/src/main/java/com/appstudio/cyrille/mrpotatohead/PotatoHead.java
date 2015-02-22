package com.appstudio.cyrille.mrpotatohead;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;



public class PotatoHead extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potato_head);
    }

    public void OnCheckboxClicked(View view) {

        ImageView hat = (ImageView) findViewById(R.id.hat);
        ImageView ears = (ImageView) findViewById(R.id.ears);
        ImageView arms = (ImageView) findViewById(R.id.arms);
        ImageView eyebrows = (ImageView) findViewById(R.id.eyebrows);
        ImageView glasses = (ImageView) findViewById(R.id.glasses);
        ImageView mouth = (ImageView) findViewById(R.id.mouth);
        ImageView mustache = (ImageView) findViewById(R.id.mustache);
        ImageView nose = (ImageView) findViewById(R.id.nose);
        ImageView shoes = (ImageView) findViewById(R.id.shoes);
        ImageView eyes = (ImageView) findViewById(R.id.eyes);

        CheckBox checkbox = ((CheckBox) view);

        switch (view.getId()) {
            case R.id.checkHat:
                if (checkbox.isChecked()) {
                    hat.setVisibility(ImageView.VISIBLE);
                }
                else {
                    hat.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkEars:
                if (checkbox.isChecked()) {
                    ears.setVisibility(ImageView.VISIBLE);
                }
                else {
                    ears.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkArms:
                if (checkbox.isChecked()) {
                    arms.setVisibility(ImageView.VISIBLE);
                }
                else {
                    arms.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkEyebrow:
                if (checkbox.isChecked()) {
                    eyebrows.setVisibility(ImageView.VISIBLE);
                }
                else {
                    eyebrows.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkGlasses:
                if (checkbox.isChecked()) {
                    glasses.setVisibility(ImageView.VISIBLE);
                }
                else {
                    glasses.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkMouth:
                if (checkbox.isChecked()) {
                    mouth.setVisibility(ImageView.VISIBLE);
                }
                else {
                    mouth.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkMustache:
                if (checkbox.isChecked()) {
                    mustache.setVisibility(ImageView.VISIBLE);
                }
                else {
                    mustache.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkNose:
                if (checkbox.isChecked()) {
                    nose.setVisibility(ImageView.VISIBLE);
                }
                else {
                    nose.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkShoes:
                if (checkbox.isChecked()) {
                    shoes.setVisibility(ImageView.VISIBLE);
                }
                else {
                    shoes.setVisibility(ImageView.INVISIBLE);
                }
                break;
            case R.id.checkEyes:
                if (checkbox.isChecked()) {
                    eyes.setVisibility(ImageView.VISIBLE);
                }
                else {
                    eyes.setVisibility(ImageView.INVISIBLE);
                }
                break;
        }
    }
}
