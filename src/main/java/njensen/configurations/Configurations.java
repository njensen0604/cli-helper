package njensen.configurations;

import java.text.SimpleDateFormat;

/**
 * Configure the app with these variables
 *
 * @author Nicholas Jensen on 05/27/2017.
 */
public class Configurations {

    // Application Version
    private static final String APP_VERSION = "0.0.1";

    // Application Variables
    private static final String APPLICATION_NAME = "CLI Helper";
    private static final String APPLICATION_URL = "#";
    private static final String APPLICATION_TITLE = APPLICATION_NAME + "Run CLI easier.";
    private static final String APPLICATION_DEVELOPER_NAME = "Nicholas A. Jensen";
    private static final String APPLICATION_COPYRIGHT = "Copyright © 2017";


    // below are the getters

    public static String getApplicationDeveloperName() {
        return APPLICATION_DEVELOPER_NAME;
    }

    public static String getApplicationCopyright() {

        long timeStamp = System.currentTimeMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(timeStamp);

        //String year = java.time.Year.now().toString();
        String secondYear = "";

        if (!year.equals("2016")) {
            secondYear = " - " + year;
        }

        String text = APPLICATION_COPYRIGHT + " 2016" + secondYear + " " + getApplicationDeveloperName();
        return text;

    }

    public static String getApplicationVersion() {
        return APP_VERSION;
    }

    public static String getApplicationName() {
        return APPLICATION_NAME;
    }

    public static String getApplicationUrl() {
        return APPLICATION_URL;
    }

    public static String getApplicationTitle() {
        return APPLICATION_TITLE;
    }


    /**
     * Print all the variables to the console.
     *
     * @return All the variables as a string.
     */
    public static String printAll() {

        String string = "* Configurations";
        string += "\n - getApplicationFontSize: " + getApplicationName();
        string += "\n - getApplicationCopyright: " + getApplicationCopyright();
        string += "\n - getApplicationDeveloperName: " + getApplicationDeveloperName();
        string += "\n - getApplicationTitle: " + getApplicationTitle();
        string += "\n - getApplicationUrl: " + getApplicationUrl();
        string += "\n - getApplicationVersion: " + getApplicationVersion();

        System.out.println(string);

        return string;

    }

}
