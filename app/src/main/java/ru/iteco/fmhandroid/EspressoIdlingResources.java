package ru.iteco.fmhandroid;

import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResources {
    private static final String RESOURCE = "GLOBAL"; //tag
    public static CountingIdlingResource idlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment(){
        idlingResource.increment();
    }

    public static void decrement(){
        if(!idlingResource.isIdleNow()){
            idlingResource.decrement();
        }
    }
}
