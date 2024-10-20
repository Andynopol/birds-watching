package com.spirent.birds_watching.entities;

import java.util.function.Consumer;

public interface UpdateIfValid {
    public static <T> void updateIfValid(Consumer<T> setter, T value) {
        if(value != null) {
            setter.accept(value);
        }
    }
}
