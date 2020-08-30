package com.example.lironautomotivepvtltd;

public class UtilityValidation {


    private static String TAG = UtilityValidation.class.getSimpleName();
    public static boolean FullName(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")));
    }

    public static boolean Designation(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")));
    }


    public static boolean MobileNo(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[0-9]{10}$")));
//        ^[7-9][0-9]{9}$
    }


    public static boolean Email(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[a-zA-Z0-9._-]+@[a-z]+.+[a-z]$")));
    }


    public static boolean Date(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$")));
//([1-9]|[12][0-9]|3[01])/([1-9]|1[012])/((19|20)\\d\\d)

    }



    public static boolean OwnerID(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[A-Z]{2}[0-9]{10}$")));
    }
    public static boolean VehicleIdNo(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$")));
    }
    public static boolean VehicleRegNo(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[A-Z]{4}[0-9]{11}$")));
    }
    public static boolean Address(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[a-zA-Z0-9\\s,.'-]{3,}$")));
    }
    public static boolean Model(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")));
    }
    public static boolean Password(String string){

        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[A-Z]{1}[a-z]{5}[0-9]{2}$")));
    }

}
