package com.lambda;

import net.rakugakibox.util.YamlResourceBundle;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigUtil {

    public static void main (String[] args) {
        initConfig();
    }

    public static void initConfig () {

       File file = new File("/Users/rohansuryawanshi/appconfig");

       ClassLoader loader = null;

       try {
           URL[] urls = {file.toURI().toURL()};
            loader = new URLClassLoader(urls);

       } catch (MalformedURLException e) {

       }

       ResourceBundle rBundle = ResourceBundle.getBundle(
               "example", Locale.getDefault(), loader,
               YamlResourceBundle.Control.INSTANCE
       );

        System.out.println(rBundle.getString("fruits.apple"));    // => "Apple"
        System.out.println(rBundle.getString("fruits.orange"));   // => "Orange"
        System.out.println(rBundle.getString("fruits.grape"));    // => "Grape"

        System.out.println(rBundle.getString("fruits.dryFruits.almond"));
        //System.out.println(rBundle.getStringArray("fruits.dryFruits.moreDry"));       // => wont work
        System.out.println(rBundle.getString("fruits.dryFruits.moreDry.pista"));

        // Support the "ResourceBundle#getString(String)" for yaml array.
        System.out.println(rBundle.getString("colors[0]"));       // => "Red"
        System.out.println(rBundle.getString("colors[1]"));       // => "Orange"
        System.out.println(rBundle.getString("colors[2]"));       // => "Purple"

        // Support the "ResourceBundle#getStringArray(String)".
        for (String s : rBundle.getStringArray("colors")) {
            System.out.println(s);
        }       // => "Red", "Orange", "Purple"

        // Support the "ResourceBundle#keySet()".
        for (String s: rBundle.keySet()) {
            System.out.println(s);
        }       // => "fruits.apple", "fruits.orange", "fruits.grape",
        //    "colors[0]", "colors[1]", "colors[2]", "colors" (not sorted)


    }

}
