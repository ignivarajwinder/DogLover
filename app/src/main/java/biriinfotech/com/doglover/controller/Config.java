package biriinfotech.com.doglover.controller;


public class Config {

    static String BASE_URL = "";
    static AppMode appMode = AppMode.LIVE;
    static public String getBaseURL() {
        init(appMode);
        return BASE_URL;
    }

    /**
     * Initialize all the variable in this method
     *
     * @param appMode
     */
    public static void init(AppMode appMode) {

        switch (appMode) {
            case DEV:
                BASE_URL = "http://cloudwebsolutions.in/anotherapi/project/webservices/";
                break;

            case TEST:
                BASE_URL = "http://cloudwebsolutions.in/anotherapi/project/webservices/";
                break;

            case LIVE:
                BASE_URL = "http://cloudwebsolutions.in/anotherapi/project/webservices";
                break;
        }
    }
    public enum AppMode {
        DEV, TEST, LIVE
    }

}
