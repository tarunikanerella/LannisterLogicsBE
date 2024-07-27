package com.lannisterlogics.LoanService.Constants;

public enum DirectionFlagEnum {
    BANK_TO_LENDSTREET(0),
    LENDSTREET_TO_BANK(1),
    LENDSTREET_TO_USER(2),
    USER_TO_LENDSTREET(3),
    LENDSTREET_TO_PLATFORM(4);

    private final int value;

    DirectionFlagEnum(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DirectionFlagEnum setValue(int value) {
        for (DirectionFlagEnum d: DirectionFlagEnum.values()){
            if (d.value == value) {
                return d;
            }
        }
        return null;
    }
}
