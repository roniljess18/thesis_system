package dev.rjm.enums;

public enum Month {
    January(1),
    February(2),
    March(3),
    April(4),
    May(5),
    June(6),
    July(7),
    August(8),
    September(9),
    October(10),
    November(11),
    December(12);

    private int code;

    private Month(int code){
        this.code = code;
    }
 
    public int getCode(){
        return code;
    }
    public static Month fromMonth(int code){
        return switch(code){
            
            case 1 -> Month.January;
            case 2 -> Month.February;
            case 3 -> Month.March;
            case 4 -> Month.April;
            case 5 -> Month.May;
            case 6 -> Month.June;
            case 7 -> Month.July;
            case 8 -> Month.August;
            case 9 -> Month.September;
            case 10 -> Month.October;
            case 11 -> Month.November;
            case 12 -> Month.December;
            default -> null;
        };
    }
    
    
}
