package com.trip.hotel.test.android;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.By;

public class Utils {
    @NotNull
    public static String getIdString(@NotNull By.ById byId) {
        return byId.toString().substring("By.id: ".length());
    }
}
