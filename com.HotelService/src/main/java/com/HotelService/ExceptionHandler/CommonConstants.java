package com.HotelService.ExceptionHandler;

public class CommonConstants {

    public static final String DOT_DELIM = ".";
    public static final String LOCALHOST_IP = "127.0.0.1";
    public static int AD_SERVER_TYPE = 3;
    public static int ConfigRuleType = 1;
    public static int PinPositionRuleType = 2;
    public static int FilteringRuleType = 3;
    public static int AlgoPinRuleType = 4;

    public static int getAdServerType() {
        return AD_SERVER_TYPE;
    }

    public static void setAdServerType(int adServerType) {
        AD_SERVER_TYPE = adServerType;
    }

    public static int getConfigRuleType() {
        return ConfigRuleType;
    }

    public static void setConfigRuleType(int configRuleType) {
        ConfigRuleType = configRuleType;
    }

    public static int getPinPositionRuleType() {
        return PinPositionRuleType;
    }

    public static void setPinPositionRuleType(int pinPositionRuleType) {
        PinPositionRuleType = pinPositionRuleType;
    }

    public static int getFilteringRuleType() {
        return FilteringRuleType;
    }

    public static void setFilteringRuleType(int filteringRuleType) {
        FilteringRuleType = filteringRuleType;
    }

    public static int getAlgoPinRuleType() {
        return AlgoPinRuleType;
    }

    public static void setAlgoPinRuleType(int algoPinRuleType) {
        AlgoPinRuleType = algoPinRuleType;
    }

    public CommonConstants() {
    }

}
