package com.example.unitconverter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class UnitConverter {
    public static double convert(double value, String from, String to) {
        switch (from) {
            case "inch":
                if (to.equals("cm")) return value * 2.54;
                break;
            case "foot":
                if (to.equals("cm")) return value * 30.48;
                break;
            case "yard":
                if (to.equals("cm")) return value * 91.44;
                break;
            case "mile":
                if (to.equals("km")) return value * 1.60934;
                break;
            case "cm":
                if (to.equals("inch")) return value / 2.54;
                if (to.equals("foot")) return value / 30.48;
                if (to.equals("yard")) return value / 91.44;
                break;
            case "km":
                if (to.equals("mile")) return value / 1.60934;
                break;
        }
        switch (from) {
            case "pound":
                if (to.equals("kg")) return value * 0.453592;
                break;
            case "ounce":
                if (to.equals("g")) return value * 28.3495;
                break;
            case "ton":
                if (to.equals("kg")) return value * 907.185;
                break;
            case "kg":
                if (to.equals("pound")) return value / 0.453592;
                break;
            case "g":
                if (to.equals("ounce")) return value / 28.3495;
                break;
        }
        switch (from) {
            case "°C":
                if (to.equals("°F")) return (value * 1.8) + 32;
                if (to.equals("K")) return value + 273.15;
                break;
            case "°F":
                if (to.equals("°C")) return (value - 32) / 1.8;
                if (to.equals("K")) return (value - 32) / 1.8 + 273.15;
                break;
            case "K":
                if (to.equals("°C")) return value - 273.15;
                if (to.equals("°F")) return (value - 273.15) * 1.8 + 32;
                break;
        }

        return value;
    }
}