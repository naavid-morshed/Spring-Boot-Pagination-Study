package com.example.paginationprac.utils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UpdateUtils {

    // not needed, doesn't improve performance by much
    public static <T> void upIfDiff(Supplier<T> oldVal, Supplier<T> newVal, Consumer<T> setter) {
        if (!Objects.equals(newVal.get(), oldVal.get())) {
            setter.accept(newVal.get());
        }
    }
}
